package com.ase.ase_box.service.box;

import com.ase.ase_box.data.entity.Box;
import com.ase.ase_box.data.request.box.IsCreateBoxValidRequest;
import com.ase.ase_box.repository.BoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoxEntityService implements IBoxEntityService {

    private final BoxRepository boxRepository;

    public Box createBox(Box box) {
        return boxRepository.save(box);
    }

    public void deleteBoxById(String boxId) {
        boxRepository.deleteById(boxId);
    }

    public Box updateBox(Box box) {
        return boxRepository.save(box);
    }

    public Optional<Box> getBoxById(String boxId) {
        return boxRepository.findById(boxId);
    }

    public List<Box> getAllBoxes() {
        return boxRepository.findAll();
    }

    public boolean isBoxExists(String id) {
        return boxRepository.findById(id).isPresent();
    }

    @Override
    public boolean isCreateBoxValid(IsCreateBoxValidRequest isCreateBoxValidRequest) {
        Optional<List<Box>> entity = boxRepository.findAllByNameOrRaspberryId(isCreateBoxValidRequest.getName(), isCreateBoxValidRequest.getRaspberryId());
        if(entity.isPresent()){
            List<Box> boxes = entity.get();
            return boxes.isEmpty();
        }
        return true;
    }

}
