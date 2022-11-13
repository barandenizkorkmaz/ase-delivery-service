package com.ase.ase_box;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AseBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AseBoxApplication.class, args);
    }

}
