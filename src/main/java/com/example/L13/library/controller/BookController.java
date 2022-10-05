package com.example.L13.library.controller;

import com.example.L13.library.dto.BookDTO;
import com.example.L13.library.entity.Book;
import com.example.L13.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(@Valid @RequestBody BookDTO bookDTO){
        log.info("Request received {}",bookDTO);
        return new ResponseEntity<Book>(bookService.createBook(bookDTO), HttpStatus.CREATED);
    }

    @GetMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> fetchBooks(){
        log.info("Request received {}");
        return new ResponseEntity<>(bookService.fetchAllBooks(),HttpStatus.OK);
    }

    @PutMapping(value = "/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(@Valid @RequestBody BookDTO bookDTO){
        log.info("Request received {}");
        return new ResponseEntity<>(bookService.createBook(bookDTO),HttpStatus.OK);
    }
}
