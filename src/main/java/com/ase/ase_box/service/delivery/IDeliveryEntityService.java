package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.GetDeliveryListByCustomerIdAndDeliveryState;
import com.ase.ase_box.data.request.delivery.GetDeliveryListByDelivererIdAndDeliveryState;
import com.ase.ase_box.data.response.AddDeliveryResponse;

import java.util.List;

public interface IDeliveryEntityService {

    AddDeliveryResponse createDelivery(AddDeliveryRequest addDeliveryRequest);

    DeliveryDto getDelivery(String deliveryId);

    List<DeliveryDto> getAllDeliveries();

    List<DeliveryDto> getByDelivererIdAndDeliveryState(GetDeliveryListByDelivererIdAndDeliveryState getDeliveryListByDelivererIdAndDeliveryState);

    List<DeliveryDto> getByUserIdAndDeliveryState(GetDeliveryListByCustomerIdAndDeliveryState getDeliveryListByCustomerIdAndDeliveryState);

}
