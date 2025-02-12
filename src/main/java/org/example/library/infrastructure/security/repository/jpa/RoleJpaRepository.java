package org.example.library.infrastructure.security.repository.jpa;

import org.example.library.domain.Role;
import org.example.library.infrastructure.security.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRole(String role);

    @Query("select rol from RoleEntity rol")
    Set<RoleEntity> findRole();

    @Query("select rol from RoleEntity rol where rol.role = :name")
    Optional<RoleEntity> findByRoleName(String name);
}