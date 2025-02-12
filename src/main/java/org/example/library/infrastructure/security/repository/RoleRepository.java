package org.example.library.infrastructure.security.repository;

import lombok.AllArgsConstructor;
import org.example.library.domain.Role;
import org.example.library.infrastructure.security.business.dao.RoleDao;
import org.example.library.infrastructure.security.entity.RoleEntity;
import org.example.library.infrastructure.security.repository.jpa.RoleJpaRepository;
import org.example.library.infrastructure.security.repository.mapper.RoleEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class RoleRepository implements RoleDao {

    private final RoleJpaRepository roleJpaRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Override
    public void saveRole(Role role) {
        RoleEntity toSave = roleEntityMapper.mapToRoleEntityForUser(role);
        RoleEntity saved = roleJpaRepository.save(toSave);
    }

    @Override
    public Set<Role> findRole() {
        return roleJpaRepository.findRole().stream().map(roleEntityMapper::mapFromRoleEntity).collect(Collectors.toSet());
    }

    @Override
    public Optional<Role> findRoleByName(String name) {
        return roleJpaRepository.findByRoleName(name).map(roleEntityMapper::mapFromRoleEntity);
    }
}
