package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.response.AddDeliveryResponse;
import com.ase.ase_box.service.delivery.DeliveryEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("delivery")
@RequiredArgsConstructor
@RestController
public class DeliveryController {

    private final DeliveryEntityService deliveryEntityService;

    @PostMapping("")
    public ResponseEntity<AddDeliveryResponse> addDelivery(@RequestBody AddDeliveryRequest addDeliveryRequest){
        return ResponseEntity.ok(deliveryEntityService.createDelivery(addDeliveryRequest));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DeliveryDto> getDelivery(@RequestParam("id") String id){
        return ResponseEntity.ok(deliveryEntityService.getDelivery(id));
    }
}
