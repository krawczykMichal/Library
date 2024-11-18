package org.example.library.business.dao;

import org.example.library.domain.Users;

import java.util.Optional;

public interface UsersDao {

    Users saveUser(Users user);

    Optional<Users> findByUsername(String username);

    Optional<Users> findById(Integer userId);

    void deleteById(Integer userId);
}
