package com.ase.ase_box.service.box;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.request.box.AddBoxRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.data.request.delivery.AddDeliveryToBoxStatusRequest;
import com.ase.ase_box.data.request.delivery.TakeDeliveryFromBoxRequest;
import com.ase.ase_box.data.response.box.BoxStatusResponse;

import java.util.List;

public interface IBoxCrudService {

    BoxDto createBox(AddBoxRequest addBoxRequest);

    BoxDto updateBox(String boxId, UpdateBoxRequest updateBoxRequest);

    BoxStatusResponse addDeliveryToBoxStatus(AddDeliveryToBoxStatusRequest addDeliveryToBoxStatusRequest);

    BoxStatusResponse takeDeliveryFromBox(TakeDeliveryFromBoxRequest takeDeliveryFromBoxRequest);

    void deleteBoxById(String boxId); // TODO: 13.11.2022 User auth has to be checked

    BoxDto getBoxById(String boxId);

    List<BoxDto> getAllBoxes();
}
