package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
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
}
