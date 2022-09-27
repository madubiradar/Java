package com.target.kafka.producer.dto;

import com.target.kafka.producer.entity.Message;
import com.target.kafka.producer.entity.MessageType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String message;

    @NotBlank
    private String messageType;

    public Message toMessage(){
        return Message.builder()
                .messageReferenceId(UUID.randomUUID().toString())
                .message(message)
                .messageType(MessageType.valueOf(messageType))
                .build();

    }




}
