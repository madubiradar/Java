package com.target.kafka.producer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.target.kafka.producer.dto.MessageDTO;
import com.target.kafka.producer.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.target.kafka.producer.service.KafkaProducerService;

@RestController
@Slf4j
public class KafkaController {


    @Autowired
    KafkaProducerService kafkaProducerService;

    private static final String MESSAGE_CREATION= "message-creation";

    @PostMapping(value = "/publish",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO messageDTO) throws JsonProcessingException {

        log.info("sent message {}", messageDTO.toString());
        return new ResponseEntity<Message>(kafkaProducerService.publish(MESSAGE_CREATION, messageDTO), HttpStatus.CREATED);
    }

}
