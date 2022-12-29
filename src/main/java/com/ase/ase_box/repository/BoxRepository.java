package com.ase.ase_box.repository;

import com.ase.ase_box.data.entity.Box;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BoxRepository extends MongoRepository<Box, String> {

    Optional<List<Box>> findAllByNameOrRaspberryId(String name, String raspberryId);


}
