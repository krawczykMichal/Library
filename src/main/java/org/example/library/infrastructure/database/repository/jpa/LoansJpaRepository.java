package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.LoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansJpaRepository extends JpaRepository<LoansEntity, Integer> {

    @Query("select loan from LoansEntity loan where loan.loanRequest.user.userId = :userId and loan.returned = :returned")
    List<LoansEntity> findAllWithReturned(Integer userId, boolean returned);

}
