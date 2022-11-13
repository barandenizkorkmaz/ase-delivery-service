package com.ase.ase_box.data.dto;

import com.ase.ase_box.data.entity.BoxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class BoxDto {
    private String id;
    private String name;
    private String raspberryId;
    private String address;
    private BoxStatus status;
}
