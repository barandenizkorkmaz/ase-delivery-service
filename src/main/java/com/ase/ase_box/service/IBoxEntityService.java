package com.ase.ase_box.service;

import com.ase.ase_box.data.entity.Box;

import java.util.List;
import java.util.Optional;

public interface IBoxEntityService {

    Box createBox(Box box);

    void deleteBoxById(String boxId);

    Box updateBox(Box box);

    Optional<Box> getBoxById(String boxId);

    List<Box> getAllBoxes();
}
