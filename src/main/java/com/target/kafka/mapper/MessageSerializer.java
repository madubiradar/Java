package com.target.kafka.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.kafka.entity.Message;
import com.target.kafka.entity.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Map;

@Slf4j
public class MessageSerializer implements Serializer<Message> {


  private final ObjectMapper objectMapper = new ObjectMapper();


  @Override
  public byte[] serialize(String topic, Headers headers, Message data) {
    return Serializer.super.serialize(topic, headers, data);
  }

  @Override
  public byte[] serialize(String topic, Message data) {
    try {
      if (data == null){
        log.info("Null received at serializing");
        return null;
      }
      log.info("Serializing...");
      return objectMapper.writeValueAsBytes(data);
    } catch (Exception e) {
      throw new SerializationException("Error when serializing MessageDto to byte[]");
    }
  }

  @Override
  public void close() {
  }


  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
  }

}