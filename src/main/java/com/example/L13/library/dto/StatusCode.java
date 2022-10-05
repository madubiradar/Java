package com.example.L13.library.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {

    CHEGG_01("BNF_001","Book not found");
    String statusCode;

    String exceptionMessage;
}
