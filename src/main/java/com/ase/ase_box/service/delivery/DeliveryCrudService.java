package com.ase.ase_box.service.delivery;

import java.util.Arrays;
import java.util.List;
import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.enums.DeliveryStatus;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;
import com.ase.ase_box.data.request.delivery.FinishDeliveryRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import com.ase.ase_box.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.ase.ase_box.data.mapper.DeliveryMapper.DELIVERY_MAPPER;

@Service
@RequiredArgsConstructor
public class DeliveryCrudService implements IDeliveryCrudService{

    private final DeliveryRepository deliveryRepository;

    @Override
    public Delivery saveDelivery(AddDeliveryRequest addDeliveryRequest) {
        return deliveryRepository.save(DELIVERY_MAPPER.createDelivery(addDeliveryRequest));
    }

    @Override
    public Delivery updateDelivery(UpdateDeliveryRequest updateDeliveryRequest) {
        return deliveryRepository.save(DELIVERY_MAPPER.createDelivery(updateDeliveryRequest));
    }

    @Override
    public Delivery getDelivery(String deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public boolean isDeliveryExists(String deliveryId) {
        return deliveryRepository.findById(deliveryId).isPresent();
    }

    @Override
    public Delivery checkDeliveryIsExist(CheckDeliveryIsExistRequest checkDeliveryIsExistRequest) {
        return deliveryRepository.findByDelivererIdAndBoxIdAndUserIdAndDeliveryStatus(
                checkDeliveryIsExistRequest.getDelivererId(),
                checkDeliveryIsExistRequest.getBoxId(),
                checkDeliveryIsExistRequest.getUserId(),
                DeliveryStatus.SHIPPING.name()
        ).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Delivery finishDelivery(FinishDeliveryRequest finishDeliveryRequest) {
        Delivery delivery = deliveryRepository.findByDelivererIdAndBoxIdAndUserIdAndDeliveryStatus(
                finishDeliveryRequest.getDelivererId(),
                finishDeliveryRequest.getBoxId(),
                finishDeliveryRequest.getUserId(),
                DeliveryStatus.SHIPPING.name()
        ).orElseThrow(IllegalArgumentException::new);
        delivery.setDeliveryStatus(DeliveryStatus.DELIVERED);
        return deliveryRepository.save(delivery);
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
        return deliveryRepository.findAllByUserId(customerId)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getActiveDeliveriesByCustomerId(String customerId) {
        String[] deliveryStatus = new String[] { DeliveryStatus.DISPATCHED.name(), DeliveryStatus.SHIPPING.name() };
        return deliveryRepository.findAllByUserIdAndDeliveryStatusIn(customerId, Arrays.asList(deliveryStatus))
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getPastDeliveriesByCustomerId(String customerId) {
        String[] deliveryStatus = new String[] { DeliveryStatus.DELIVERED.name() };
        return deliveryRepository.findAllByUserIdAndDeliveryStatusIn(customerId, Arrays.asList(deliveryStatus))
                .orElseThrow(IllegalAccessError::new);
    }
}
