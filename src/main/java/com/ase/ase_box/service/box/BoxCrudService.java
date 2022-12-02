package com.ase.ase_box.service.box;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.entity.Box;
import com.ase.ase_box.data.enums.BoxStatus;
import com.ase.ase_box.data.request.box.AddBoxRequest;
import com.ase.ase_box.data.request.delivery.AddDeliveryToBoxStatusRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.data.request.delivery.TakeDeliveryFromBoxRequest;
import com.ase.ase_box.data.response.BoxStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ase.ase_box.data.mapper.BoxMapper.BOX_MAPPER;

@Service
@RequiredArgsConstructor
public class BoxCrudService implements IBoxCrudService {

    private final IBoxEntityService boxEntityService;

    public BoxDto createBox(AddBoxRequest addBoxRequest) {
        Box box = BOX_MAPPER.createBox(addBoxRequest);
        box.setStatus(BoxStatus.CLOSED);
        return BOX_MAPPER.convertToBoxDto(boxEntityService.createBox(box));
    }

    public List<BoxDto> getAllBoxes() {
        return BOX_MAPPER.convertToBoxDtoList(boxEntityService.getAllBoxes());
    }

    public BoxStatusResponse addDeliveryToBoxStatus(AddDeliveryToBoxStatusRequest addDeliveryToBoxStatusRequest){
        Box box = boxEntityService.getBoxById(addDeliveryToBoxStatusRequest.getBoxId())
                .orElseThrow(IllegalArgumentException::new);
        if (box.getStatus() == BoxStatus.FULL && !box.getUserId().equals(addDeliveryToBoxStatusRequest.getUserId())){
            return BoxStatusResponse.builder()
                    .boxStatus(BoxStatus.REJECTED)
                    .build();
        }
        box.setStatus(BoxStatus.FULL);
        boxEntityService.updateBox(box);
        return BoxStatusResponse.builder()
                .boxStatus(BoxStatus.FULL)
                .build();
    }

    public BoxStatusResponse takeDeliveryFromBox(TakeDeliveryFromBoxRequest takeDeliveryFromBoxRequest){
        Box box = boxEntityService.getBoxById(takeDeliveryFromBoxRequest.getBoxId())
                .orElseThrow(IllegalArgumentException::new);
        if (box.getStatus() == BoxStatus.EMPTY && !box.getUserId().equals(takeDeliveryFromBoxRequest.getUserId())){
            return BoxStatusResponse.builder()
                    .boxStatus(BoxStatus.REJECTED)
                    .build();
        }
        box.setStatus(BoxStatus.EMPTY);
        boxEntityService.updateBox(box);
        return BoxStatusResponse.builder()
                .boxStatus(BoxStatus.EMPTY)
                .build();
    }

    public BoxDto getBoxById(String boxId) {
        return BOX_MAPPER.convertToBoxDto(
                boxEntityService.getBoxById(boxId)
                        .orElseThrow(IllegalArgumentException::new)
        );
    }

    public void deleteBoxById(String boxId) {
        boxEntityService.deleteBoxById(boxId);
    }

    public BoxDto updateBox(String boxId, UpdateBoxRequest updateBoxRequest) {
        Box box = boxEntityService.getBoxById(boxId)
                .orElseThrow(IllegalArgumentException::new);
        BOX_MAPPER.updateBox(box, updateBoxRequest);
        return BOX_MAPPER.convertToBoxDto(boxEntityService.updateBox(box));
    }
}
