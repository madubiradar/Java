package com.example.L13.library.dto;


import lombok.AllArgsConstructor;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderDTO {

    @Positive
    double amount;

    @NotBlank
    String orderStatus;

    @NotBlank
    String orderReference;


}
