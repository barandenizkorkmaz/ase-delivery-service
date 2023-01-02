package com.ase.ase_box.controller;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.enums.DeliveryStatus;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.GetDeliveryListByCustomerIdAndDeliveryState;
import com.ase.ase_box.data.request.delivery.GetDeliveryListByDelivererIdAndDeliveryState;
import com.ase.ase_box.data.response.AddDeliveryResponse;
import com.ase.ase_box.service.delivery.DeliveryEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("delivery")
@RequiredArgsConstructor
@RestController
public class DeliveryController {

    private final DeliveryEntityService deliveryEntityService;

    @PostMapping("")
    public ResponseEntity<AddDeliveryResponse> addDelivery(@RequestBody AddDeliveryRequest addDeliveryRequest) {
        return ResponseEntity.ok(deliveryEntityService.createDelivery(addDeliveryRequest));
    }

    @PostMapping("/{id}")
    public ResponseEntity<DeliveryDto> getDelivery(@RequestParam("id") String id) {
        return ResponseEntity.ok(deliveryEntityService.getDelivery(id));
    }


    @GetMapping("/list/dispatcher/all")
    public ResponseEntity<List<DeliveryDto>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryEntityService.getAllDeliveries());
    }

    @GetMapping("list/customer/active/{customerId}")
    public ResponseEntity<List<DeliveryDto>> getActiveDeliveriesByCustomerId(@PathVariable("customerId") String customerId) {
        GetDeliveryListByCustomerIdAndDeliveryState getDeliveryListByCustomerIdAndDeliveryState = new GetDeliveryListByCustomerIdAndDeliveryState();
        getDeliveryListByCustomerIdAndDeliveryState.setUserId(customerId);
        getDeliveryListByCustomerIdAndDeliveryState.setDeliveryState(DeliveryStatus.SHIPPING);
        return ResponseEntity.ok(deliveryEntityService.getByUserIdAndDeliveryState(getDeliveryListByCustomerIdAndDeliveryState));
    }

    @GetMapping("list/customer/past/{customerId}")
    public ResponseEntity<List<DeliveryDto>> getPastDeliveriesByCustomerId(@PathVariable("customerId") String customerId) {
        GetDeliveryListByCustomerIdAndDeliveryState getDeliveryListByCustomerIdAndDeliveryState = new GetDeliveryListByCustomerIdAndDeliveryState();
        getDeliveryListByCustomerIdAndDeliveryState.setUserId(customerId);
        getDeliveryListByCustomerIdAndDeliveryState.setDeliveryState(DeliveryStatus.DELIVERED);
        return ResponseEntity.ok(deliveryEntityService.getByUserIdAndDeliveryState(getDeliveryListByCustomerIdAndDeliveryState));
    }

    @GetMapping("list/deliverer/{delivererId}")
    public ResponseEntity<List<DeliveryDto>> getDeliveriesByDelivererId(@PathVariable("delivererId") String delivererId) {
        GetDeliveryListByDelivererIdAndDeliveryState getDeliveryListByDelivererIdAndDeliveryState = new GetDeliveryListByDelivererIdAndDeliveryState();
        getDeliveryListByDelivererIdAndDeliveryState.setDelivererId(delivererId);
        getDeliveryListByDelivererIdAndDeliveryState.setDeliveryState(DeliveryStatus.SHIPPING);
        return ResponseEntity.ok(deliveryEntityService.getByDelivererIdAndDeliveryState(getDeliveryListByDelivererIdAndDeliveryState));
    }


}
