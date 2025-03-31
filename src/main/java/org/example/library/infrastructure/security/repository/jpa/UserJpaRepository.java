package org.example.library.infrastructure.security.repository.jpa;

import org.example.library.infrastructure.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select user from UserEntity user where user.username = :username")
    UserEntity findByUsername(String username);


    UserEntity findByEmail(String email);

    @Modifying
    @Query("update UserEntity u set u.password = :password where u.userId = :userId")
    void updatePassword(String password, Integer userId);
}
