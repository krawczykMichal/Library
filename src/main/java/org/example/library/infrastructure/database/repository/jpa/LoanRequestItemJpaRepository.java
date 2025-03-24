package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.infrastructure.database.entity.LoanRequestItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRequestItemJpaRepository extends JpaRepository<LoanRequestItemEntity, Integer> {

    @Modifying
    @Query("delete from LoanRequestItemEntity lri where lri.loanRequest.user.userId = :userId")
    void deleteByLoanRequestCartUserId(Integer userId);

    @Modifying
    @Query("delete from LoanRequestItemEntity lri where lri.loanRequest.loanRequestId = :loanRequestId")
    void deleteByLoanRequestId(Integer loanRequestId);

    @Query("select lri from LoanRequestItemEntity lri where lri.loanRequest.loanRequestNumber = :loanRequestNumber")
    List<LoanRequestItemEntity> findByLoanRequestNumber(String loanRequestNumber);

}
