package com.ase.ase_box.repository;

import com.ase.ase_box.data.entity.Box;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BoxRepository extends MongoRepository<Box, String> {

}
