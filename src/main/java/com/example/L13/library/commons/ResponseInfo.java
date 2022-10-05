package com.example.L13.library.commons;


import com.example.L13.library.dto.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInfo<T> {

    T data;
    String statusCode;
    String message;
    String exceptionCode;

    public ResponseInfo(StatusCode statusCode){
        this.statusCode = statusCode.getStatusCode();
        this.message = statusCode.getExceptionMessage();
    }

}
