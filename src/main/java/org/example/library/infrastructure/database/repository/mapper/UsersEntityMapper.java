package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Users;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersEntityMapper {

    Users mapFromUsersEntity(UsersEntity usersEntity);

    UsersEntity mapToUsersEntity(Users users);
}
