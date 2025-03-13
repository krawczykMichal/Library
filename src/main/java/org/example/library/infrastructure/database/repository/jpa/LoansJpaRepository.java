package org.example.library.infrastructure.database.repository.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.library.domain.Loans;
import org.example.library.infrastructure.database.entity.LoansEntity;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoansJpaRepository extends JpaRepository<LoansEntity, Integer> {

    @Query("select l from LoansEntity l order by l.returned asc")
    List<LoansEntity> findAllForEmployee();

    @Query("select loan from LoansEntity loan where loan.loanRequest.cart.user.userId = :userId and loan.returned = :returned")
    List<LoansEntity> findAllWithReturned(Integer userId, boolean returned);

    @Query("select loan from LoansEntity loan where loan.loanNumber = :loanNumber")
    Optional<LoansEntity> findByLoanNumber(String loanNumber);

    List<LoansEntity> user(UsersEntity user);

    @Query("select loan from LoansEntity loan where loan.user.userId = :userId order by loan.returned asc")
    List<LoansEntity> findAllByUserId(Integer userId);

    @Modifying
    @Query("delete from LoansEntity lo where lo.user.userId = :userId")
    void deleteByUserId(Integer userId);

    @Modifying
    @Query("UPDATE LoansEntity l SET l.returned = true WHERE l.loanNumber = :loanNumber")
    int returnLoan(String loanNumber);

    @Modifying
    @Query("delete from LoansEntity l where l.loanRequest.reservation.reservationsId = :reservationId")
    void deleteByReservationId(Integer reservationId);
}
