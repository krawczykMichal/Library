package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.Users;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.example.library.infrastructure.database.repository.jpa.UsersJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.UsersEntityMapper;
import org.example.library.infrastructure.database.repository.mapper.UsersEntityMapperClass;
import org.example.library.infrastructure.security.repository.mapper.UserEntityMapper;
import org.example.library.infrastructure.security.repository.jpa.UserJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class UsersRepository implements UsersDao {

    private final UsersJpaRepository usersJpaRepository;
    private final UserJpaRepository userJpaRepository;

    private final UserEntityMapper userEntityMapper;
    private final UsersEntityMapper usersEntityMapper;
    private final UsersEntityMapperClass usersEntityMapperClass;

    @Override
    public Users saveUser(Users user) {
        UsersEntity toSave = usersEntityMapperClass.mapToUsersEntity(user);
        UsersEntity saved = usersJpaRepository.save(toSave);
//        userEntityMapper.mapToUserEntity(user);
        return usersEntityMapperClass.mapFromUsersEntity(saved);
    }

    @Override
    public Optional<Users> findByUsername(String username) {
        return usersJpaRepository.findByUsername(username).map(usersEntityMapper::mapFromUsersEntity);
    }

    @Override
    public Optional<Users> findById(Integer userId) {
       return usersJpaRepository.findById(userId).map(usersEntityMapper::mapFromUsersEntity);
    }

    @Override
    public void deleteById(Integer userId) {
        usersJpaRepository.deleteById(userId);
    }

    @Override
    public List<Users> findAll() {
        return usersJpaRepository.findAll().stream().map(usersEntityMapper::mapFromUsersEntity).toList();
    }
}
