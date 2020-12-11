package com.dekelbz.users.persistence.service;

import com.dekelbz.users.persistence.domain.User;

public interface UserService {
    void save(User user);

    User getByEmail(String email);

    void deleteByEmail(String email);
}
