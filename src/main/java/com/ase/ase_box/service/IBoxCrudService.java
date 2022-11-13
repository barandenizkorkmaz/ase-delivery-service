package com.ase.ase_box.service;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.request.AddBoxRequest;
import com.ase.ase_box.data.request.UpdateBoxRequest;

import java.util.List;

public interface IBoxCrudService {

    BoxDto createBox(AddBoxRequest addBoxRequest);

    BoxDto updateBox(String boxId, UpdateBoxRequest updateBoxRequest);

    void deleteBoxById(String boxId); // TODO: 13.11.2022 User auth has to be checked

    BoxDto getBoxById(String boxId);

    List<BoxDto> getAllBoxes();
}
