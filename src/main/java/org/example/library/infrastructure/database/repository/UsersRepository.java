package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.Users;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.example.library.infrastructure.database.repository.jpa.UsersJpaRepository;
import org.example.library.infrastructure.database.repository.mapper.UsersEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class UsersRepository implements UsersDao {

    private final UsersJpaRepository usersJpaRepository;

    private final UsersEntityMapper usersEntityMapper;

    @Override
    public Users saveUser(Users user) {
        UsersEntity toSave = usersEntityMapper.mapToUsersEntity(user);
        UsersEntity saved = usersJpaRepository.save(toSave);
        return usersEntityMapper.mapFromUsersEntity(saved);
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
}
