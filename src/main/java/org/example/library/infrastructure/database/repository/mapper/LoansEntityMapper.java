package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Loans;
import org.example.library.infrastructure.database.entity.LoansEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoansEntityMapper {

    Loans mapFromLoansEntity(LoansEntity loansEntity);

    LoansEntity mapToLoansEntity(Loans loans);
}
