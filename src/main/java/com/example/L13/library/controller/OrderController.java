package com.example.L13.library.controller;

import com.example.L13.library.dto.CreateOrderDTO;
import com.example.L13.library.dto.UpdateOrderDTO;

import com.example.L13.library.entity.Orders;
import com.example.L13.library.service.OrderService;
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
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> createOrder(@Valid @RequestBody CreateOrderDTO createOrderDTO){
        log.info("Request received {}", createOrderDTO);
        return new ResponseEntity<>(orderService.createOrder(createOrderDTO),HttpStatus.CREATED);
    }

    @GetMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Orders>> fetchOrders(@RequestParam(value = "orderId") String orderReferenceId){
        log.info("Request received {}");
        return new ResponseEntity<>(orderService.fetchOrderById(orderReferenceId),HttpStatus.OK);
    }

    @PutMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Orders> updateOrder(@Valid @RequestBody UpdateOrderDTO updateOrderDTO){
        log.info("Request received {}",updateOrderDTO);
        return new ResponseEntity<>(orderService.updateOrder(updateOrderDTO),HttpStatus.OK);
    }

    @GetMapping(value = "/place-order")
    public ResponseEntity<Orders> IssueBook(@RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "bookId") Integer bookId ){
        log.info("userId {} bookId {}",userId, bookId);
        return new ResponseEntity<>(orderService.placeOrder(userId,bookId),HttpStatus.CREATED);
    }

}
