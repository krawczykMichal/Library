package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.domain.LoansHistory;
import org.example.library.infrastructure.database.entity.LoansHistoryEntity;
import org.example.library.infrastructure.security.repository.mapper.UserRoleEntityMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoansHistoryJpaRepository extends JpaRepository<LoansHistoryEntity, Integer> {

    @Query("select lh from LoansHistoryEntity lh where lh.user.userId = :userId")
    List<LoansHistoryEntity> findAllByUserId(Integer userId);

    @Query("select lh from LoansHistoryEntity lh where lh.loanNumber = :loanNumber")
    Optional<LoansHistoryEntity> findByLoanNumber(String loanNumber);
}
