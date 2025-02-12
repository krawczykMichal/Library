package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.LoanRequestItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRequestItemJpaRepository extends JpaRepository<LoanRequestItemEntity, Integer> {

    @Modifying
    @Query("delete from LoanRequestItemEntity lri where lri.loanRequest.cart.user.userId = :userId")
    void deleteByLoanRequestCartUserId(Integer userId);
}
