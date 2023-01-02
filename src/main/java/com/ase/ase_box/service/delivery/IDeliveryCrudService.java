package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.*;

import java.util.List;

public interface IDeliveryCrudService {

    Delivery saveDelivery(AddDeliveryRequest addDeliveryRequest);

    Delivery getDelivery(String deliveryId);

    Delivery checkDeliveryIsExist(CheckDeliveryIsExistRequest checkDeliveryIsExistRequest);

    Delivery finishDelivery(FinishDeliveryRequest finishDeliveryRequest);

    List<Delivery> getAllDeliveries();

    List<Delivery> getByDelivererIdAndDeliveryState(GetDeliveryListByDelivererIdAndDeliveryState getDeliveryListByDelivererIdAndDeliveryState);

    List<Delivery> getByUserIdAndDeliveryState(GetDeliveryListByCustomerIdAndDeliveryState getDeliveryListByCustomerIdAndDeliveryState);

}
