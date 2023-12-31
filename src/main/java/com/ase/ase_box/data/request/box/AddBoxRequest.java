package com.ase.ase_box.data.request.box;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddBoxRequest {
    private String name;
    private String raspberryId;
    private String address;
}
