package com.ase.ase_box.repository;

import com.ase.ase_box.data.entity.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery,String> {

    Optional<Delivery> findByDelivererIdAndBoxIdAndUserIdAndDeliveryState(String delivererId, String boxId, String userId, String deliveryState);
}
