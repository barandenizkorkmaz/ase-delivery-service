package com.ase.ase_box.data.request.delivery;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TakeDeliveryFromBoxRequest {
    private String userId;
    private String boxId;
}
