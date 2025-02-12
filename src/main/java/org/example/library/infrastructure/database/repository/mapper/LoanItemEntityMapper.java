package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.LoanItem;
import org.example.library.infrastructure.database.entity.LoanItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanItemEntityMapper {

    LoanItem mapFromLoanItemEntity(LoanItemEntity loanItemEntity);

    LoanItemEntity mapToLoanItemEntity(LoanItem loanItem);
}
