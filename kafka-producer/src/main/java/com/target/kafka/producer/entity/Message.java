package com.target.kafka.producer.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Message implements Serializable {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    
    private String messageReferenceId;

    @Enumerated(value = EnumType.STRING)
    private MessageType messageType;

    @Version
    private long version;

    @CreationTimestamp
    private LocalDateTime created_at;


}
