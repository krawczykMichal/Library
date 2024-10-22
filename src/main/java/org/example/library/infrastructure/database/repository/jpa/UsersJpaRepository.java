package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersJpaRepository extends JpaRepository<UsersEntity, Integer> {
}
