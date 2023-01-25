package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.enums.DeliveryStatus;
import com.ase.ase_box.data.enums.UserType;
import com.ase.ase_box.data.request.delivery.CreateDeliveryRequest;
import com.ase.ase_box.data.request.delivery.IsCreateDeliveryValidRequest;
import com.ase.ase_box.data.request.delivery.IsUpdateDeliveryValidRequest;
import com.ase.ase_box.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ase.ase_box.data.mapper.DeliveryMapper.DELIVERY_MAPPER;

@Service
@RequiredArgsConstructor
public class DeliveryEntityService implements IDeliveryEntityService{


    private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery saveDelivery(CreateDeliveryRequest createDeliveryRequest) {
        return deliveryRepository.save(DELIVERY_MAPPER.createDelivery(createDeliveryRequest));
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public Optional<Delivery> getDeliveryById(String id) {
        return deliveryRepository.findById(id);
    }

    @Override
    public List<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public boolean isDeliveryExists(String id) {
        return deliveryRepository.findById(id).isPresent();
    }

    @Override
    public boolean isCreateDeliveryValid(IsCreateDeliveryValidRequest isCreateDeliveryValidRequest) {
        return deliveryRepository.findAllByBoxIdAndCustomerEmailIsNot(isCreateDeliveryValidRequest.getBoxId(), isCreateDeliveryValidRequest.getCustomerEmail())
                .isEmpty();
    }

    @Override
    public boolean isUpdateDeliveryValid(String id, IsUpdateDeliveryValidRequest isUpdateDeliveryValidRequest) {
        return deliveryRepository.findAllByIdIsNotAndBoxIdAndCustomerEmailIsNot(
                id,
                isUpdateDeliveryValidRequest.getBoxId(),
                isUpdateDeliveryValidRequest.getCustomerEmail()
                )
                .isEmpty();
    }

    @Override
    public void deleteDeliveryById(String id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public List<Delivery> getDeliveriesByDelivererId(String delivererId) {
        return deliveryRepository.findAllByDelivererEmail(delivererId);
    }

    @Override
    public List<Delivery> getDeliveriesByCustomerId(String customerId) {
        return deliveryRepository.findAllByCustomerEmail(customerId);
    }

    @Override
    public List<Delivery> getActiveDeliveriesByCustomerId(String customerId) {
        String[] deliveryStatus = new String[] { DeliveryStatus.DISPATCHED.name(), DeliveryStatus.SHIPPING.name(), DeliveryStatus.DELIVERED.name() };
        return deliveryRepository.findAllByCustomerEmailAndDeliveryStatusIn(customerId, Arrays.asList(deliveryStatus));
    }

    @Override
    public List<Delivery> getPastDeliveriesByCustomerId(String customerId) {
        String[] deliveryStatus = new String[] { DeliveryStatus.COLLECTED.name() };
        return deliveryRepository.findAllByCustomerEmailAndDeliveryStatusIn(customerId, Arrays.asList(deliveryStatus));
    }

    @Override
    public boolean isBoxUnlockAuthorized(String boxId, String rfId) {
        UserType userType = getUserTypeByRfid(rfId);
        if(userType != null){
            if(userType.equals(UserType.CUSTOMER)){
                // If customer is assigned to the given box and at least one delivery is delivered, then return true.
                return !deliveryRepository.findAllByBoxIdAndCustomerEmailAndDeliveryStatus(
                        boxId,
                        rfId,
                        DeliveryStatus.DELIVERED
                ).isEmpty();
            }
            else if(userType.equals(UserType.DELIVERER)){
                // If deliverer is assigned to the given box and at least one delivery is shipping, then return true.
                return !deliveryRepository.findAllByBoxIdAndDelivererEmailAndDeliveryStatus(
                        boxId,
                        rfId,
                        DeliveryStatus.SHIPPING
                ).isEmpty();
            }
            return false;
        }
        return false;
    }

    @Override
    public UserType getUserTypeByRfid(String rfId) {
        if(!deliveryRepository.findAllByCustomerEmail(rfId).isEmpty()){
            return UserType.CUSTOMER;
        }
        else if(!deliveryRepository.findAllByDelivererEmail(rfId).isEmpty()){
            return UserType.DELIVERER;
        }
        return null;
    }

    @Override
    public void updateDeliveriesByLockRequest(String boxId, String rfId) throws IllegalAccessException {
        UserType userType = getUserTypeByRfid(rfId);
        if(userType.equals(UserType.CUSTOMER)){
            List<Delivery> deliveries = deliveryRepository.findAllByBoxIdAndCustomerEmailAndDeliveryStatus(
                    boxId,
                    rfId,
                    DeliveryStatus.DELIVERED
            );
            // Update delivery statuses to COLLECTED
            for (Delivery delivery:deliveries) {
                delivery.setDeliveryStatus(DeliveryStatus.COLLECTED);
                updateDelivery(delivery);
            }
        }
        else if(userType.equals(UserType.DELIVERER)){
            List<Delivery> deliveries = deliveryRepository.findAllByBoxIdAndDelivererEmailAndDeliveryStatus(
                    boxId,
                    rfId,
                    DeliveryStatus.SHIPPING
            );
            // Update delivery statuses to DELIVERED
            for (Delivery delivery:deliveries) {
                delivery.setDeliveryStatus(DeliveryStatus.DELIVERED);
                updateDelivery(delivery);
            }
        }
        else{
            throw new IllegalAccessException();
        }
    }
}
