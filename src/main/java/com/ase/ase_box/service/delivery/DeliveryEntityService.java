package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import com.ase.ase_box.data.response.delivery.AddDeliveryResponse;
import com.ase.ase_box.data.response.delivery.DeleteDeliveryResponse;
import com.ase.ase_box.data.response.delivery.UpdateDeliveryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ase.ase_box.data.mapper.DeliveryMapper.DELIVERY_MAPPER;

@Service
@RequiredArgsConstructor
public class DeliveryEntityService implements IDeliveryEntityService{

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
        }catch (Exception e){
            Delivery delivery = deliveryCrudService.saveDelivery(addDeliveryRequest);
            return AddDeliveryResponse.
                    builder()
                    .deliveryDto(DELIVERY_MAPPER.convertToDeliveryDto(delivery))
                    .isSuccessful(true)
                    .build();
        }
    }

    @Override
    public DeleteDeliveryResponse deleteDelivery(String deliveryId) {
        boolean isSuccessful = false;
        if(deliveryCrudService.isDeliveryExists(deliveryId)){
            deliveryCrudService.deleteDeliveryById(deliveryId);
            isSuccessful = true;
        }
        return DeleteDeliveryResponse
                .builder()
                .isSuccessful(isSuccessful)
                .build();
    }

    @Override
    public UpdateDeliveryResponse updateDelivery(UpdateDeliveryRequest updateDeliveryRequest) {
        boolean isSuccessful = false;
        if(deliveryCrudService.isDeliveryExists(updateDeliveryRequest.getId())){
            deliveryCrudService.updateDelivery(updateDeliveryRequest);
            isSuccessful = true;
        }
        return UpdateDeliveryResponse
                .builder()
                .isSuccessful(isSuccessful)
                .build();
    }

    @Override
    public DeliveryDto getDelivery(String deliveryId) {
        return DELIVERY_MAPPER.convertToDeliveryDto(deliveryCrudService.getDelivery(deliveryId));
    }
}
