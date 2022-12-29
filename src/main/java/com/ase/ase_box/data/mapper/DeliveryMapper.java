package com.ase.ase_box.data.mapper;

import com.ase.ase_box.data.dto.DeliveryDto;
import com.ase.ase_box.data.entity.Delivery;
import com.ase.ase_box.data.request.delivery.AddDeliveryRequest;
import com.ase.ase_box.data.request.delivery.UpdateDeliveryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

    DeliveryMapper DELIVERY_MAPPER = Mappers.getMapper(DeliveryMapper.class);

    Delivery createDelivery(AddDeliveryRequest addDeliveryRequest);

    Delivery createDelivery(UpdateDeliveryRequest updateDeliveryRequest);

    DeliveryDto convertToDeliveryDto(Delivery delivery);

    List<DeliveryDto> convertToDeliveryDtoList(List<Delivery> deliveries);
}
