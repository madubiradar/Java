package com.target.kafka.consumer.repository;


import com.target.kafka.consumer.Model.MessageConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageConsumerRepository extends JpaRepository<MessageConsumer, Long> {
}
