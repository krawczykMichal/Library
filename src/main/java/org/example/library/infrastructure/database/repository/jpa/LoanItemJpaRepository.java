package org.example.library.infrastructure.database.repository.jpa;

import io.micrometer.common.KeyValues;
import org.example.library.infrastructure.database.entity.LoanItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanItemJpaRepository extends JpaRepository<LoanItemEntity, Integer> {

    @Query("select li from LoanItemEntity li where li.loan.loanId = :loanId")
    List<LoanItemEntity> findAllByLoanId(Integer loanId);

    @Modifying
    @Query("delete from LoanItemEntity li where li.loan.user.userId = :userId")
    void deleteByLoanUserId(Integer userId);

    @Modifying
    @Query("delete from LoanItemEntity li where li.loan.loanRequest.reservation.reservationsId = :reservationId")
    void deleteByReservationId(Integer reservationId);

    @Query("select li from LoanItemEntity li where li.loan.loanNumber = :loanNumber")
    List<LoanItemEntity> findByLoanNumber(String loanNumber);
}
