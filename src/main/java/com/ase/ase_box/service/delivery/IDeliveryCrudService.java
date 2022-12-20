package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;
import com.ase.ase_box.data.request.delivery.FinishDeliveryRequest;

public interface IDeliveryCrudService {

    Delivery saveDelivery(AddDeliveryRequest addDeliveryRequest);

    Delivery getDelivery(String deliveryId);

    Delivery checkDeliveryIsExist(CheckDeliveryIsExistRequest checkDeliveryIsExistRequest);

    Delivery finishDelivery(FinishDeliveryRequest finishDeliveryRequest);

    void deleteDeliveryById(String deliveryId);


}
