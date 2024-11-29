package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.LoanRequest;
import org.example.library.infrastructure.database.entity.LoanRequestEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRequestEntityMapper {

    LoanRequest mapFromLoanRequestEntity(LoanRequestEntity loanRequestEntity);

    LoanRequestEntity mapToLoanRequestEntity(LoanRequest loanRequest);
}
