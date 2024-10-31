package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Employees;
import org.example.library.infrastructure.database.entity.EmployeesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeesEntityMapper {

    EmployeesEntity mapToEmployeesEntity(Employees employees);

    Employees mapFromEmployeesEntity(EmployeesEntity employeesEntity);
}
