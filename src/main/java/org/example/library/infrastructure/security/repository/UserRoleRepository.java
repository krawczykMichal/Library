package org.example.library.infrastructure.security.repository;

import lombok.AllArgsConstructor;
import org.example.library.infrastructure.security.business.dao.UserRoleDao;
import org.example.library.infrastructure.security.entity.UserRoleEntity;
import org.example.library.infrastructure.security.repository.jpa.UserRoleJpaRepository;
import org.example.library.infrastructure.security.repository.mapper.UserRoleEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserRoleRepository implements UserRoleDao {

    private final UserRoleJpaRepository jpaRepository;

    private final UserRoleEntityMapper mapper;

    @Override
    public void saveUserRole(Integer userId, Integer roleId) {
        UserRoleEntity userRoleEntity = mapper.map(userId, roleId);
        jpaRepository.save(userRoleEntity);
    }
}
