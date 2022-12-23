package com.ase.ase_box.data.response.box;

import com.ase.ase_box.data.enums.BoxStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoxStatusResponse {

    private BoxStatus boxStatus;
}
