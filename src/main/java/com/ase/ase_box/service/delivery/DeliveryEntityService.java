package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.entity.Box;
import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.enums.DeliveryStatus;
import com.ase.ase_box.data.request.delivery.CreateDeliveryRequest;
import com.ase.ase_box.data.request.delivery.IsCreateDeliveryValidRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import com.ase.ase_box.data.response.delivery.CreateDeliveryResponse;
import com.ase.ase_box.data.response.delivery.DeleteDeliveryResponse;
import com.ase.ase_box.data.response.delivery.UpdateDeliveryResponse;
import com.ase.ase_box.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.ase.ase_box.data.mapper.BoxMapper.BOX_MAPPER;
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
        Optional<List<Delivery>> entity = deliveryRepository.findAllByBoxIdAndCustomerIdIsNot(isCreateDeliveryValidRequest.getBoxId(), isCreateDeliveryValidRequest.getCustomerId());
        if(entity.isPresent()){
            List<Delivery> deliveries = entity.get();
            return deliveries.isEmpty();
        }
        return true;
    }

    @Override
    public void deleteDeliveryById(String id) {
        deliveryRepository.deleteById(id);
    }

    @Override
    public List<Delivery> getDeliveriesByDelivererId(String delivererId) {
        return deliveryRepository.findAllByDelivererId(delivererId)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getDeliveriesByCustomerId(String customerId) {
        return deliveryRepository.findAllByCustomerId(customerId)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getActiveDeliveriesByCustomerId(String customerId) {
        String[] deliveryStatus = new String[] { DeliveryStatus.DISPATCHED.name(), DeliveryStatus.SHIPPING.name() };
        return deliveryRepository.findAllByCustomerIdAndDeliveryStatusIn(customerId, Arrays.asList(deliveryStatus))
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getPastDeliveriesByCustomerId(String customerId) {
        String[] deliveryStatus = new String[] { DeliveryStatus.DELIVERED.name() };
        return deliveryRepository.findAllByCustomerIdAndDeliveryStatusIn(customerId, Arrays.asList(deliveryStatus))
                .orElseThrow(IllegalAccessError::new);
    }
}
