package com.ase.ase_box.service.box;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.request.box.CreateBoxRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.data.response.box.CreateBoxResponse;
import com.ase.ase_box.data.response.box.DeleteBoxResponse;
import com.ase.ase_box.data.response.box.UpdateBoxResponse;

import java.util.List;

public interface IBoxCrudService {

    CreateBoxResponse createBox(CreateBoxRequest createBoxRequest);

    UpdateBoxResponse updateBox(String id, UpdateBoxRequest updateBoxRequest);

    DeleteBoxResponse deleteBox(String id);

    BoxDto getBoxById(String boxId);

    List<BoxDto> getAllBoxes();
}
