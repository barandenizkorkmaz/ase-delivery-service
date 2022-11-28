package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;

public interface IDeliveryCrudService {

    Delivery saveDelivery(AddDeliveryRequest addDeliveryRequest);

    Delivery getDelivery(String deliveryId);

    Delivery checkDeliveryIsExist(CheckDeliveryIsExistRequest checkDeliveryIsExistRequest);

    // TODO: 18.11.2022 Update - Delete delivery should be added.
}
