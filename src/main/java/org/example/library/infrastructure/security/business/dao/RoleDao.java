package org.example.library.infrastructure.security.business.dao;

import org.example.library.domain.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleDao {

    void saveRole(Role role);

    Set<Role> findRole();

    Optional<Role> findRoleByName(String name);
}
