package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;
import com.ase.ase_box.data.request.delivery.GetDeliveryListByCustomerIdAndDeliveryState;
import com.ase.ase_box.data.request.delivery.GetDeliveryListByDelivererIdAndDeliveryState;
import com.ase.ase_box.data.response.AddDeliveryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ase.ase_box.data.mapper.DeliveryMapper.DELIVERY_MAPPER;

@Service
@RequiredArgsConstructor
public class DeliveryEntityService implements IDeliveryEntityService {

    private final DeliveryCrudService deliveryCrudService;

    @Override
    public AddDeliveryResponse createDelivery(AddDeliveryRequest addDeliveryRequest) {
        try {
            Delivery delivery = deliveryCrudService.checkDeliveryIsExist(
                    CheckDeliveryIsExistRequest.builder()
                            .delivererId(addDeliveryRequest.getDelivererId())
                            .boxId(addDeliveryRequest.getBoxId())
                            .build()
            );
            return AddDeliveryResponse.
                    builder()
                    .deliveryDto(DELIVERY_MAPPER.convertToDeliveryDto(delivery))
                    .isSuccessful(false)
                    .build();
        } catch (Exception e) {
            Delivery delivery = deliveryCrudService.saveDelivery(addDeliveryRequest);
            return AddDeliveryResponse.
                    builder()
                    .deliveryDto(DELIVERY_MAPPER.convertToDeliveryDto(delivery))
                    .isSuccessful(true)
                    .build();
        }
    }

    @Override
    public DeliveryDto getDelivery(String deliveryId) {
        return DELIVERY_MAPPER.convertToDeliveryDto(deliveryCrudService.getDelivery(deliveryId));
    }

    @Override
    public List<DeliveryDto> getAllDeliveries() {
        return DELIVERY_MAPPER.convertToDeliveryDtoList(deliveryCrudService.getAllDeliveries());
    }

    @Override
    public List<DeliveryDto> getByDelivererIdAndDeliveryState(GetDeliveryListByDelivererIdAndDeliveryState getDeliveryListByDelivererIdAndDeliveryState) {
        return DELIVERY_MAPPER.convertToDeliveryDtoList(deliveryCrudService.getByDelivererIdAndDeliveryState(getDeliveryListByDelivererIdAndDeliveryState));
    }

    @Override
    public List<DeliveryDto> getByUserIdAndDeliveryState(GetDeliveryListByCustomerIdAndDeliveryState getDeliveryListByCustomerIdAndDeliveryState) {
        return DELIVERY_MAPPER.convertToDeliveryDtoList(deliveryCrudService.getByUserIdAndDeliveryState(getDeliveryListByCustomerIdAndDeliveryState));
    }


}
