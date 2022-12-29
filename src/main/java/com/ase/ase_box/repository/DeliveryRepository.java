package com.ase.ase_box.repository;

import com.ase.ase_box.data.entity.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery,String> {

    Optional<List<Delivery>> findAllByBoxIdAndCustomerIdIsNot(String boxId, String customerId);

    Optional<List<Delivery>> findAllByDelivererId(String delivererId);

    Optional<List<Delivery>> findAllByCustomerId(String customerId);

    Optional<List<Delivery>> findAllByCustomerIdAndDeliveryStatusIn(String customerId, List<String> deliveryStatus);
}
