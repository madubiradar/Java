package com.example.L13.library.dto;

import com.example.L13.library.entity.Orders;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {

    @Positive
    double amount;

    @NotBlank
    String orderStatus;

    public Orders toOrder(){
        return Orders.builder()
                .amount(amount)
                .orderReference(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.valueOf(orderStatus))
                .build();
    }
}