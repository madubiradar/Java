package com.target.kafka.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.target.kafka.config.TopicConfig;
import com.target.kafka.dto.MessageDTO;
import com.target.kafka.entity.Message;
import com.target.kafka.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class KafkaController {


    @Autowired
    KafkaProducerService kafkaProducerService;

    @Autowired
    TopicConfig topicConfig;

    @PostMapping(value = "/kafka/publish")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDTO messageDTO) throws JsonProcessingException {

        log.info("sent message {}", messageDTO.toString());
        return new ResponseEntity<Message>(kafkaProducerService.publish(topicConfig.topic().name(), messageDTO), HttpStatus.OK);
    }


//    @PostMapping(value = "/kafka/publish")
//    public String sendMessage(@RequestParam("topic") String topic, @RequestBody Message message){
//
//        kafkaTemplate.send(topic,kafkaProducerService.SerializeData(message));
//        return "published successfully";
//
//    }
}
