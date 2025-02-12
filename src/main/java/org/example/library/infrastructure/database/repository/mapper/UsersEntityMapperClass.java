package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.User;
import org.example.library.domain.Users;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersEntityMapperClass {

    public Users mapFromUsersEntity(UsersEntity usersEntity) {
        if (usersEntity == null) {
            return null;
        }

        return Users.builder()
                .userId(usersEntity.getUserId())
                .name(usersEntity.getName())
                .surname(usersEntity.getSurname())
                .username(usersEntity.getUsername())
                .phoneNumber(usersEntity.getPhoneNumber())
                .email(usersEntity.getEmail())
                .build();
    }

    public UsersEntity mapToUsersEntity(Users users) {
        if (users == null) {
            return null;
        }

        return UsersEntity.builder()
                .userId(users.getUserId())
                .name(users.getName())
                .surname(users.getSurname())
                .phoneNumber(users.getPhoneNumber())
                .email(users.getEmail())
                .username(users.getUsername())
                .build();
    }
}
