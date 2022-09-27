package com.target.kafka.dto;

import com.target.kafka.entity.Message;
import com.target.kafka.entity.MessageType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {

    @NotBlank
    private Long id;

    @NotBlank
    private String message;

    @NotBlank
    private String messageType;

    public Message toMessage(){
        return Message.builder()
                .id(id)
                .messageReferenceId(UUID.randomUUID().toString())
                .message(message)
                .messageType(MessageType.valueOf(messageType))
                .build();
    }




}
