package com.dekelbz.users.persistence;

import com.dekelbz.users.persistence.domain.User;
import com.dekelbz.users.persistence.repository.UserRepository;
import com.dekelbz.users.persistence.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SaveUserListener {

    private final UserService userService;

    public SaveUserListener(UserRepository userRepository, UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user-save", groupId = "user-persistence")
    public void saveUser(User user){
        userService.save(user);
    }
}
