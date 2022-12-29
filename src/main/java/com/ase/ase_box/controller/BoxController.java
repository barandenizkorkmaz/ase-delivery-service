package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.request.box.CreateBoxRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.data.response.box.CreateBoxResponse;
import com.ase.ase_box.data.response.box.DeleteBoxResponse;
import com.ase.ase_box.data.response.box.UpdateBoxResponse;
import com.ase.ase_box.service.box.IBoxCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("box")
public class BoxController {
    // TODO: 30.12.2022 The functions can only be called by dispatcher.

    private final IBoxCrudService boxCrudService;

    @PostMapping("/create")
    public ResponseEntity<CreateBoxResponse> createBox(@RequestBody CreateBoxRequest createBoxRequest) {
        return ResponseEntity.ok(boxCrudService.createBox(createBoxRequest));
    }

    @GetMapping("list/{id}")
    public ResponseEntity<BoxDto> getBoxById(@PathVariable("id") String id) {
        return ResponseEntity.ok(boxCrudService.getBoxById(id));
    }

    @GetMapping("list/all")
    public ResponseEntity<List<BoxDto>> getAllBoxes() {
        return ResponseEntity.ok(boxCrudService.getAllBoxes());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UpdateBoxResponse> updateBox(@PathVariable("id") String id, @RequestBody UpdateBoxRequest updateBoxRequest) {
        return ResponseEntity.ok(boxCrudService.updateBox(id, updateBoxRequest));
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<DeleteBoxResponse> deleteBox(@PathVariable("id") String id) {
        return ResponseEntity.ok(boxCrudService.deleteBox(id));
    }

}
