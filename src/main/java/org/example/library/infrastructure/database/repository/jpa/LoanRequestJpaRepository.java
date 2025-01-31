package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRequestJpaRepository extends JpaRepository<LoanRequestEntity, Integer> {

    @Query("select lr from LoanRequestEntity lr where lr.user.userId = :userId")
    List<LoanRequestEntity> findByUserId(Integer userId);
}
