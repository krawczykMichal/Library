package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Authors;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorsEntityMapper {

    Authors mapFromAuthorsEntity(AuthorsEntity authorsEntity);

    AuthorsEntity mapToAuthorsEntity(Authors authors);
}
