package com.ase.ase_box.service.delivery;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.CheckDeliveryIsExistRequest;
import com.ase.ase_box.data.request.delivery.FinishDeliveryRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;

import java.util.List;
import java.util.Optional;

public interface IDeliveryCrudService {

    Delivery saveDelivery(AddDeliveryRequest addDeliveryRequest);

    Delivery updateDelivery(UpdateDeliveryRequest updateDeliveryRequest);

    Delivery getDelivery(String deliveryId);

    List<Delivery> getDeliveries();

    List<Delivery> getDeliveriesByDelivererId(String delivererId);

    List<Delivery> getDeliveriesByCustomerId(String customerId);

    List<Delivery> getActiveDeliveriesByCustomerId(String customerId);

    List<Delivery> getPastDeliveriesByCustomerId(String customerId);

    boolean isDeliveryExists(String deliveryId);

    Delivery checkDeliveryIsExist(CheckDeliveryIsExistRequest checkDeliveryIsExistRequest);

    Delivery finishDelivery(FinishDeliveryRequest finishDeliveryRequest);

    // TODO: 18.11.2022 Update - Delete delivery should be added.
    void deleteDeliveryById(String id);


}
