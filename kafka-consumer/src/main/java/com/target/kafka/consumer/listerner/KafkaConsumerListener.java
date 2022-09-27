package com.target.kafka.consumer.listerner;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.kafka.consumer.Model.MessageConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.target.kafka.consumer.repository.MessageConsumerRepository;
import com.target.kafka.consumer.requestEntity.Message;

import java.util.UUID;

@Service
@Slf4j
public class KafkaConsumerListener {

    @Autowired
    MessageConsumerRepository messageConsumerRepository;

    @Autowired
    ObjectMapper objectMapper;

    private static final String MESSAGE_CREATION= "message-creation";

    @KafkaListener(topics = {MESSAGE_CREATION}, groupId = "consumer-group")
    public void receiveMessageFromDemoTopic(ConsumerRecord<String,String> record) throws JsonProcessingException {
        log.info("Message {} received from topic", record.topic());

        log.info("Message {} saved ", record.value());
        Message message = objectMapper.readValue(record.value(), Message.class);

        MessageConsumer messageConsumer = new MessageConsumer();
        messageConsumer.setMessage(message.getMessage());
        messageConsumer.setMessageType(message.getMessageType());
        messageConsumer.setMessageReferenceId(UUID.randomUUID().toString());

        messageConsumerRepository.save(messageConsumer);

        //failed to consume from broker, auto ack = all
    }

}
