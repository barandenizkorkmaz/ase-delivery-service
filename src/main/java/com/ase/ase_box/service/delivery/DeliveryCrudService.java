package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.enums.DeliveryStatus;
import com.ase.ase_box.data.request.delivery.*;
import com.ase.ase_box.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ase.ase_box.data.mapper.DeliveryMapper.DELIVERY_MAPPER;

@Service
@RequiredArgsConstructor
public class DeliveryCrudService implements IDeliveryCrudService {

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
        return deliveryRepository.findByDelivererIdAndBoxIdAndUserIdAndDeliveryState(
                checkDeliveryIsExistRequest.getDelivererId(),
                checkDeliveryIsExistRequest.getBoxId(),
                checkDeliveryIsExistRequest.getUserId(),
                DeliveryStatus.SHIPPING.name()
        ).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Delivery finishDelivery(FinishDeliveryRequest finishDeliveryRequest) {
        Delivery delivery = deliveryRepository.findByDelivererIdAndBoxIdAndUserIdAndDeliveryState(
                finishDeliveryRequest.getDelivererId(),
                finishDeliveryRequest.getBoxId(),
                finishDeliveryRequest.getUserId(),
                DeliveryStatus.SHIPPING.name()
        ).orElseThrow(IllegalArgumentException::new);
        delivery.setDeliveryState(DeliveryStatus.DELIVERED);
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    @Override
    public List<Delivery> getByDelivererIdAndDeliveryState(GetDeliveryListByDelivererIdAndDeliveryState getDeliveryListByDelivererIdAndDeliveryState) {
        return deliveryRepository.findByDelivererIdAndDeliveryState(
                getDeliveryListByDelivererIdAndDeliveryState.getDelivererId(),
                getDeliveryListByDelivererIdAndDeliveryState.getDeliveryState().toString()
        ).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public List<Delivery> getByUserIdAndDeliveryState(GetDeliveryListByCustomerIdAndDeliveryState getDeliveryListByCustomerIdAndDeliveryState) {
        return deliveryRepository.findByUserIdAndDeliveryState(
                getDeliveryListByCustomerIdAndDeliveryState.getUserId(),
                getDeliveryListByCustomerIdAndDeliveryState.getDeliveryState().toString()
        ).orElseThrow(IllegalAccessError::new);
    }
}
