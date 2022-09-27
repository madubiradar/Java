package com.target.kafka.producer.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.target.kafka.producer.dto.MessageDTO;
import com.target.kafka.producer.entity.Message;
import lombok.extern.slf4j.Slf4j;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {


    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;

    //KafkaProducer preferred

    @Autowired
    ObjectMapper objectMapper;

    public Message publish(String topic, MessageDTO messageDTO) throws JsonProcessingException {

        Message message = messageDTO.toMessage();
        log.info("Topic {} Message {} is sent from producer service", topic, message);

        kafkaTemplate.send(topic,objectMapper.writeValueAsString(message));



        return message;

    }


}
