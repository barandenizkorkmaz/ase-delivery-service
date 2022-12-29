package com.ase.ase_box.service.box;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.entity.Box;
import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.box.CreateBoxRequest;
import com.ase.ase_box.data.request.box.IsCreateBoxValidRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.data.request.delivery.IsCreateDeliveryValidRequest;
import com.ase.ase_box.data.response.box.CreateBoxResponse;
import com.ase.ase_box.data.response.box.DeleteBoxResponse;
import com.ase.ase_box.data.response.box.UpdateBoxResponse;
import com.ase.ase_box.data.response.delivery.CreateDeliveryResponse;
import com.ase.ase_box.data.response.delivery.DeleteDeliveryResponse;
import com.ase.ase_box.data.response.delivery.UpdateDeliveryResponse;
import com.ase.ase_box.service.delivery.IDeliveryCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ase.ase_box.data.mapper.BoxMapper.BOX_MAPPER;
import static com.ase.ase_box.data.mapper.DeliveryMapper.DELIVERY_MAPPER;

@Service
@RequiredArgsConstructor
public class BoxCrudService implements IBoxCrudService {

    private final IBoxEntityService boxEntityService;

    @Override
    public CreateBoxResponse createBox(CreateBoxRequest createBoxRequest) {
        boolean isValid = boxEntityService.isCreateBoxValid(
                IsCreateBoxValidRequest.builder()
                        .name(createBoxRequest.getName())
                        .raspberryId(createBoxRequest.getRaspberryId())
                        .build()
        );
        if(isValid){
            boxEntityService.createBox(BOX_MAPPER.createBox(createBoxRequest));
        }
        return CreateBoxResponse
                .builder()
                .isSuccessful(isValid)
                .build();
    }

    public List<BoxDto> getAllBoxes() {
        return BOX_MAPPER.convertToBoxDtoList(boxEntityService.getAllBoxes());
    }

    public BoxDto getBoxById(String boxId) {
        return BOX_MAPPER.convertToBoxDto(
                boxEntityService.getBoxById(boxId)
                        .orElseThrow(IllegalArgumentException::new)
        );
    }

    @Override
    public DeleteBoxResponse deleteBox(String id) {
        boolean isSuccessful = false;
        if(boxEntityService.isBoxExists(id)){
            boxEntityService.deleteBoxById(id);
            isSuccessful = true;
        }
        return DeleteBoxResponse
                .builder()
                .isSuccessful(isSuccessful)
                .build();
    }

    @Override
    public UpdateBoxResponse updateBox(String id, UpdateBoxRequest updateBoxRequest) {
        boolean isValid = boxEntityService.isCreateBoxValid(
                IsCreateBoxValidRequest.builder()
                        .name(updateBoxRequest.getName())
                        .raspberryId(updateBoxRequest.getRaspberryId())
                        .build()
        );
        if(isValid){
            Box box = boxEntityService.getBoxById(id)
                    .orElseThrow(IllegalArgumentException::new);
            BOX_MAPPER.updateBox(box, updateBoxRequest);
            boxEntityService.updateBox(box);
        }
        return UpdateBoxResponse
                .builder()
                .isSuccessful(isValid)
                .build();
    }
}
