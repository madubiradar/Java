package com.example.L13.library.exceptions;


import com.example.L13.library.dto.StatusCode;
import lombok.Getter;

@Getter
public class BookNotFoundException extends RuntimeException{

    StatusCode statusCode;

    public BookNotFoundException(String statusCode){
        super(statusCode);
    }
}
