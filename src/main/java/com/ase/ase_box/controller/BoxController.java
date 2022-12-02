package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.BoxDto;
import com.ase.ase_box.data.request.box.AddBoxRequest;
import com.ase.ase_box.data.request.box.UpdateBoxRequest;
import com.ase.ase_box.data.request.delivery.AddDeliveryToBoxStatusRequest;
import com.ase.ase_box.data.request.delivery.TakeDeliveryFromBoxRequest;
import com.ase.ase_box.data.response.BoxStatusResponse;
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

    @PostMapping()
    public ResponseEntity<BoxDto> createBox(@RequestBody AddBoxRequest addBoxRequest) {
        return ResponseEntity.ok(boxCrudService.createBox(addBoxRequest));
    }

    @PostMapping("all")
    public ResponseEntity<List<BoxDto>> getAllBoxes() {
        return ResponseEntity.ok(boxCrudService.getAllBoxes());
    }

    @PostMapping("edit/{id}")
    public ResponseEntity<BoxDto> updateBox(@PathVariable("id") String id, @RequestBody UpdateBoxRequest updateBoxRequest) {
        return ResponseEntity.ok(boxCrudService.updateBox(id, updateBoxRequest));
    }

    @PostMapping("{id}")
    public ResponseEntity<BoxDto> getBoxById(@PathVariable("id") String id) {
        return ResponseEntity.ok(boxCrudService.getBoxById(id));
    }

    @PostMapping("del/{id}")
    public void deleteBoxById(@PathVariable("id") String id) {
        boxCrudService.deleteBoxById(id);
    }

    @PostMapping("deliver")
    public ResponseEntity<BoxStatusResponse> addDeliveryToBox(@RequestBody AddDeliveryToBoxStatusRequest addDeliveryToBoxStatusRequest){
        return ResponseEntity.ok(boxCrudService.addDeliveryToBoxStatus(addDeliveryToBoxStatusRequest));
    }

    @PostMapping("take/delivery")
    public ResponseEntity<?> takeDelivery(@RequestBody TakeDeliveryFromBoxRequest takeDeliveryFromBoxRequest){
        return ResponseEntity.ok(boxCrudService.takeDeliveryFromBox(takeDeliveryFromBoxRequest));
    }

}
