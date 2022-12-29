package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.request.delivery.CreateDeliveryRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import com.ase.ase_box.data.response.delivery.CreateDeliveryResponse;
import com.ase.ase_box.data.response.delivery.DeleteDeliveryResponse;
import com.ase.ase_box.data.response.delivery.UpdateDeliveryResponse;
import com.ase.ase_box.service.delivery.DeliveryCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("delivery")
@RequiredArgsConstructor
@RestController
public class DeliveryController {

    private final DeliveryCrudService deliveryCrudService;

    @PostMapping("/create")
    public ResponseEntity<CreateDeliveryResponse> createDelivery(@RequestBody CreateDeliveryRequest createDeliveryRequest){
        return ResponseEntity.ok(deliveryCrudService.createDelivery(createDeliveryRequest));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<DeleteDeliveryResponse> deleteDelivery(@PathVariable("id") String id){
        return ResponseEntity.ok(deliveryCrudService.deleteDelivery(id));
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateDeliveryResponse> updateDelivery(@RequestBody UpdateDeliveryRequest updateDeliveryRequest){
        return ResponseEntity.ok(deliveryCrudService.updateDelivery(updateDeliveryRequest));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<DeliveryDto> getDelivery(@PathVariable("id") String id){
        return ResponseEntity.ok(deliveryCrudService.getDelivery(id));
    }

    @GetMapping("/list/dispatcher/all")
    public ResponseEntity<List<DeliveryDto>> getDeliveries(){
        return ResponseEntity.ok(deliveryCrudService.getDeliveries());
    }

    @GetMapping("list/deliverer/{delivererId}")
    public ResponseEntity<List<DeliveryDto>> getDeliveriesByDeliverer(@PathVariable("delivererId") String delivererId){
        return ResponseEntity.ok(deliveryCrudService.getDeliveriesByDelivererId(delivererId));
    }

    @GetMapping("list/customer/all/{customerId}")
    public ResponseEntity<List<DeliveryDto>> getDeliveriesByCustomer(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(deliveryCrudService.getDeliveriesByCustomerId(customerId));
    }

    @GetMapping("list/customer/active/{customerId}")
    public ResponseEntity<List<DeliveryDto>> getActiveDeliveriesByCustomer(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(deliveryCrudService.getActiveDeliveriesByCustomerId(customerId));
    }

    @GetMapping("list/customer/past/{customerId}")
    public ResponseEntity<List<DeliveryDto>> getPastDeliveriesByCustomer(@PathVariable("customerId") String customerId){
        return ResponseEntity.ok(deliveryCrudService.getPastDeliveriesByCustomerId(customerId));
    }

}
