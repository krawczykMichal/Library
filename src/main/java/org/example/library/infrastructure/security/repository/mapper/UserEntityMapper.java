package org.example.library.infrastructure.security.repository.mapper;

import org.example.library.domain.Role;
import org.example.library.domain.User;
import org.example.library.infrastructure.security.entity.RoleEntity;
import org.example.library.infrastructure.security.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public User mapFromUserEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return User.builder()
                .userId(userEntity.getUserId())
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .email(userEntity.getEmail())
                .active(userEntity.getActive())
                .build();
    }

    private Role mapFromRoleEntity(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return Role.builder()
                .roleId(roleEntity.getRoleId())
                .role(roleEntity.getRole())
                .build();
    }

    public UserEntity mapToUserEntity(User user) {
        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .active(user.getActive())
                .build();
    }

//    public User mapFromUsers(Users users) {
//        if (users == null) {
//            return null;
//        }
//
//        return User.builder()
//                .userId(users.getUserId())
//                .email(users.getEmail())
//                .username(users.getUsername())
//                .password(u1)
//                .active().build();
//    }
}
