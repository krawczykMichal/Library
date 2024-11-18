package org.example.library.infrastructure.database.repository.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersJpaRepository extends JpaRepository<UsersEntity, Integer> {


    Optional<UsersEntity> findByUsername(String username);
}
