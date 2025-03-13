package org.example.library.infrastructure.database.repository.jpa;

import org.example.library.domain.LoanRequest;
import org.example.library.infrastructure.database.entity.LoanRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRequestJpaRepository extends JpaRepository<LoanRequestEntity, Integer> {

    @Query("select lr from LoanRequestEntity lr where lr.cart.user.userId = :userId")
    List<LoanRequestEntity> findByUserId(Integer userId);


    @Modifying
    @Query("delete from LoanRequestEntity lr where lr.cart.user.userId = :userId")
    void deleteByCartUserId(Integer userId);

    @Query("select lr from LoanRequestEntity lr where lr.loanRequestNumber = :loanRequestNumber")
    Optional<LoanRequestEntity> findByLoanRequestNumber(String loanRequestNumber);


    @Modifying
    @Query("delete from LoanRequestEntity lr where lr.loanRequestNumber = :loanRequestNumber")
    void deleteByLoanRequestNumber(String loanRequestNumber);

    @Modifying
    @Query("delete from LoanRequestEntity lr where lr.reservation.reservationsId = :reservationId")
    void deleteByReservationId(Integer reservationId);
}
