package com.ase.ase_box.service.box;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.entity.Box;
import com.ase.ase_box.data.request.box.AddBoxRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.service.delivery.IDeliveryCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ase.ase_box.data.mapper.BoxMapper.BOX_MAPPER;

@Service
@RequiredArgsConstructor
public class BoxCrudService implements IBoxCrudService {

    private final IBoxEntityService boxEntityService;
    private final IDeliveryCrudService deliveryCrudService;

    public BoxDto createBox(AddBoxRequest addBoxRequest) {
        Box box = BOX_MAPPER.createBox(addBoxRequest);
        return BOX_MAPPER.convertToBoxDto(boxEntityService.createBox(box));
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
