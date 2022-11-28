package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.response.AddDeliveryResponse;

public interface IDeliveryEntityService {

    AddDeliveryResponse createDelivery(AddDeliveryRequest addDeliveryRequest);

    DeliveryDto getDelivery(String deliveryId);
}
