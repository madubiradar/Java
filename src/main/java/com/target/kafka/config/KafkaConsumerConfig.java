package com.target.kafka.config;

import com.target.kafka.entity.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configs = new HashMap<String, Object>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        configs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        return new DefaultKafkaConsumerFactory(configs);

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();

        kafkaListenerContainerFactory.setConsumerFactory(consumerFactory());
        return kafkaListenerContainerFactory;
    }


}
