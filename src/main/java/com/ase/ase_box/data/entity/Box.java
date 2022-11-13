package com.ase.ase_box.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document("boxes")
public class Box extends BaseEntity {

    @Id
    private String id = UUID.randomUUID().toString();

    private String address;

    private String raspberryId;

    @Enumerated(EnumType.STRING)
    private BoxStatus status;

    private String name;
}
