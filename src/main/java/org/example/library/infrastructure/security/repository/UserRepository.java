package org.example.library.infrastructure.security.repository;

import lombok.AllArgsConstructor;
import org.example.library.domain.User;
import org.example.library.infrastructure.security.business.dao.UserDao;
import org.example.library.infrastructure.security.entity.UserEntity;
import org.example.library.infrastructure.security.repository.mapper.UserEntityMapper;
import org.example.library.infrastructure.security.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRepository implements UserDao {

    private final UserJpaRepository userJpaRepository;

    private final UserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user1) {
        UserEntity toSave = userEntityMapper.mapToUserEntity(user1);
        UserEntity saved = userJpaRepository.save(toSave);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity byUsername = userJpaRepository.findByUsername(username);
        return userEntityMapper.mapFromUserEntity(byUsername);
    }

}
