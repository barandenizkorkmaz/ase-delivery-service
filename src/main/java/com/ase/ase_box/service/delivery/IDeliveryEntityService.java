package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import com.ase.ase_box.data.response.delivery.AddDeliveryResponse;
import com.ase.ase_box.data.response.delivery.DeleteDeliveryResponse;
import com.ase.ase_box.data.response.delivery.UpdateDeliveryResponse;

public interface IDeliveryEntityService {

    AddDeliveryResponse createDelivery(AddDeliveryRequest addDeliveryRequest);

    UpdateDeliveryResponse updateDelivery(UpdateDeliveryRequest updateDeliveryRequest);

    DeleteDeliveryResponse deleteDelivery(String deliveryId);

    DeliveryDto getDelivery(String deliveryId);
}
