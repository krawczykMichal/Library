package org.example.library.infrastructure.security.repository.mapper;

import org.example.library.domain.Role;
import org.example.library.infrastructure.security.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapper {

    public Role mapFromRoleEntity(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return Role.builder()
                .roleId(roleEntity.getRoleId())
                .role(roleEntity.getRole())
                .build();
    }

    public RoleEntity mapToRoleEntityForUser(Role role) {
        if (role == null) {
            return null;
        }

        return RoleEntity.builder()
                .roleId(role.getRoleId())
                .role(role.getRole())
                .build();
    }
}
