package com.ase.ase_box.service;

import com.ase.ase_box.data.entity.Box;
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
}
