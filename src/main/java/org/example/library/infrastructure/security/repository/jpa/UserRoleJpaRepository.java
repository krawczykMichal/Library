package org.example.library.infrastructure.security.repository.jpa;

import org.example.library.infrastructure.security.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleJpaRepository extends JpaRepository<UserRoleEntity, Integer> {


//    @Modifying
//    @Query("INSERT INTO UserRoleEntity (user, role) VALUES (:userId, :roleId)")
//    void saveUserRole(Integer userId, Integer roleId);
}
