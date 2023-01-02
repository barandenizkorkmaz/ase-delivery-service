package com.ase.ase_box.data.request.delivery;

import com.ase.ase_box.data.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetDeliveryListByCustomerIdAndDeliveryState {
    private String userId;
    private DeliveryStatus deliveryState;
}

