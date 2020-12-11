package com.dekelbz.users.persistence.service;

import com.dekelbz.users.persistence.domain.User;
import com.dekelbz.users.persistence.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        Optional.ofNullable(userRepository.getIdByEmail(user.getEmail()))
                .ifPresent(id -> user.setId(id.getId()));
        userRepository.save(user);
    }
}
