package com.example.L13.library.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> generateBadRequestResponse(){
        return new ResponseEntity<>("BAD REQUEST, BOOK NOT FOUND", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectionErrorException.class)
    public ResponseEntity<String> generateConnectionErrorResponse(){
        return new ResponseEntity<>("Connection Error, Data not saved", HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(BookExistsException.class)
    public ResponseEntity<String> BookAlreadyExistException(){
        return new ResponseEntity<>("Book with same isbn already exist", HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> UserNotFoundException(){
        return new ResponseEntity<>("User Not Found Exception", HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(QuotaExceededException.class)
    public ResponseEntity<String> QuotaExceededException(){
        return new ResponseEntity<>("User orders Quota has Exceeded ", HttpStatus.BAD_REQUEST);
    }
}
