package org.example.library.infrastructure.security.business;

import lombok.AllArgsConstructor;
import org.example.library.domain.User;
import org.example.library.infrastructure.security.business.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDao userDao;

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
