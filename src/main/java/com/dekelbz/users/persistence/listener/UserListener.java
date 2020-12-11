package com.dekelbz.users.persistence.listener;

import com.dekelbz.users.persistence.domain.User;
import com.dekelbz.users.persistence.service.UserService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaNull;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.Optional;

@Component
public class UserListener {

    private final UserService userService;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String retrievedUserTopic;

    public UserListener(UserService userService, KafkaTemplate<String, Object> kafkaTemplate,
                        @Value("${kafka.topic.retrieved.user}") String retrievedUserTopic) {
        this.userService = userService;
        this.kafkaTemplate = kafkaTemplate;
        this.retrievedUserTopic = retrievedUserTopic;
    }

    @KafkaListener(topics = "${kafka.topic.save.user}")
    public void saveUser(User user){
        userService.save(user);
    }

    @KafkaListener(topics = "${kafka.topic.get.user}")
    public void getUser(String email) {
        kafkaTemplate.send(retrievedUserTopic, email, userService.getByEmail(email));
    }

    @KafkaListener(topics = "${kafka.topic.delete.user}")
    public void deleteUser(String email) {
        userService.deleteByEmail(email);
    }
}
