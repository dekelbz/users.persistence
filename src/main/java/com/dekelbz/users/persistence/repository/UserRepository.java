package com.dekelbz.users.persistence.repository;

import com.dekelbz.users.persistence.domain.User;
import com.dekelbz.users.persistence.repository.views.Id;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User getByEmail(String email);

    void deleteByEmail(String email);

    Id getIdByEmail(String email);

}
