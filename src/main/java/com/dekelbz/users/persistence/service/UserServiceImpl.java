package com.dekelbz.users.persistence.service;

import com.dekelbz.users.persistence.domain.User;
import com.dekelbz.users.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void save(User user) {
        Optional.ofNullable(userRepository.getIdByEmail(user.getEmail()))
                .ifPresent(id -> user.setId(id.getId()));
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    @Transactional
    public void deleteByEmail(String email) {
        userRepository.deleteByEmail(email);
    }
}
