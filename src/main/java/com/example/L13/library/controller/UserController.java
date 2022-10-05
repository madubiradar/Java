package com.example.L13.library.controller;

import com.example.L13.library.dto.UserDTO;
import com.example.L13.library.entity.User;
import com.example.L13.library.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class UserController {


    /**
     * CRUD
     * {POST} /user
     * {GET} /user
     * {PUT} /user
     * {DELETE} /user
     */

    @Autowired
    UserService userService;


    @PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createAUser(@Valid @RequestBody UserDTO userInfo){
        log.info("Request Received {} " , userInfo);
        return new ResponseEntity<>(userService.createUser(userInfo), HttpStatus.CREATED);
    }


    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> fetchAllUsers(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber){
        log.info("Request Received for fetching all users  ");
        return new ResponseEntity<>(userService.fetchAllUsers(pageNumber), HttpStatus.OK);
    }


    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<User>> fetchUserById(
            @RequestParam( name = "userId", defaultValue = "0") Integer id){
        log.info("Request Received for fetching all users  ");
        return new ResponseEntity<>(userService.fetchOneById(id), HttpStatus.OK);
    }

}