package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.enums.DeliveryStatus;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;
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
    public Delivery getDelivery(String deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(IllegalAccessError::new);
    }

    @Override
    public Delivery checkDeliveryIsExist(CheckDeliveryIsExistRequest checkDeliveryIsExistRequest) {
        return deliveryRepository.findByDelivererIdAndAndBoxIdAndDeliveryState(
                checkDeliveryIsExistRequest.getDelivererId(),
                checkDeliveryIsExistRequest.getBoxId(),
                DeliveryStatus.SHIPPING.name()
        ).orElseThrow(IllegalArgumentException::new);
    }
}
