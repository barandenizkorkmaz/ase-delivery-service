package com.ase.ase_box.repository;

import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.enums.DeliveryStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DeliveryRepository extends MongoRepository<Delivery,String> {

    List<Delivery> findAllByBoxIdAndCustomerEmailIsNot(String boxId, String customerId);

    List<Delivery> findAllByIdIsNotAndBoxIdAndCustomerEmailIsNot(String id, String boxId, String customerId);

    List<Delivery> findAllByDelivererEmail(String delivererId);

    List<Delivery> findAllByCustomerEmail(String customerId);

    List<Delivery> findAllByBoxIdAndCustomerEmailAndDeliveryStatus(String boxId, String customerId, DeliveryStatus deliveryStatus);

    List<Delivery> findAllByBoxIdAndDelivererEmailAndDeliveryStatus(String boxId, String delivererId, DeliveryStatus deliveryStatus);

    List<Delivery> findAllByCustomerEmailAndDeliveryStatusIn(String customerId, List<String> deliveryStatus);
}
