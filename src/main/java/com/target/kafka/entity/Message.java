package com.target.kafka.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.apache.kafka.common.serialization.Serializer;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Message implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    
    private String messageReferenceId;

    @Enumerated(value = EnumType.STRING)
    private MessageType messageType;

    @Version
    private long version;


}
