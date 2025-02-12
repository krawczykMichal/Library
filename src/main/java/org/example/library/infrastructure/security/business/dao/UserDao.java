package org.example.library.infrastructure.security.business.dao;

import org.example.library.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao {

    void saveUser(User user1);

    User findByUsername(String username);

//
}
