package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.LoanRequestItem;
import org.example.library.infrastructure.database.entity.LoanRequestItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanRequestItemEntityMapper {
    LoanRequestItem mapFromLoanRequestItemEntity(LoanRequestItemEntity loanRequestItemEntity);
}
