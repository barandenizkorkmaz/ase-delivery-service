package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import com.ase.ase_box.data.response.delivery.AddDeliveryResponse;
import com.ase.ase_box.data.response.delivery.DeleteDeliveryResponse;
import com.ase.ase_box.data.response.delivery.UpdateDeliveryResponse;
import com.ase.ase_box.service.delivery.DeliveryEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("delivery")
@RequiredArgsConstructor
@RestController
public class DeliveryController {

    private final DeliveryEntityService deliveryEntityService;

    @PostMapping("/create")
    public ResponseEntity<AddDeliveryResponse> addDelivery(@RequestBody AddDeliveryRequest addDeliveryRequest){
        return ResponseEntity.ok(deliveryEntityService.createDelivery(addDeliveryRequest));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<DeleteDeliveryResponse> deleteDelivery(@PathVariable("id") String id){
        return ResponseEntity.ok(deliveryEntityService.deleteDelivery(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateDeliveryResponse> updateDelivery(@RequestBody UpdateDeliveryRequest updateDeliveryRequest){
        return ResponseEntity.ok(deliveryEntityService.updateDelivery(updateDeliveryRequest));
    }


    @GetMapping("/list/{id}")
    public ResponseEntity<DeliveryDto> getDelivery(@PathVariable("id") String id){
        return ResponseEntity.ok(deliveryEntityService.getDelivery(id));
    }

}
