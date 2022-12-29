package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.request.box.AddBoxRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.service.box.IBoxCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("box")
public class BoxController {

    private final IBoxCrudService boxCrudService;

    @PostMapping("/create")
    public ResponseEntity<BoxDto> createBox(@RequestBody AddBoxRequest addBoxRequest) {
        return ResponseEntity.ok(boxCrudService.createBox(addBoxRequest));
    }

    @PostMapping("list/all")
    public ResponseEntity<List<BoxDto>> getAllBoxes() {
        return ResponseEntity.ok(boxCrudService.getAllBoxes());
    }

    @PostMapping("update/{id}")
    public ResponseEntity<BoxDto> updateBox(@PathVariable("id") String id, @RequestBody UpdateBoxRequest updateBoxRequest) {
        return ResponseEntity.ok(boxCrudService.updateBox(id, updateBoxRequest));
    }

    @PostMapping("{id}")
    public ResponseEntity<BoxDto> getBoxById(@PathVariable("id") String id) {
        return ResponseEntity.ok(boxCrudService.getBoxById(id));
    }

    @PostMapping("delete/{id}")
    public void deleteBox(@PathVariable("id") String id) {
        boxCrudService.deleteBoxById(id);
    }

}
