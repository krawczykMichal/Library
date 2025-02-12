package org.example.library.infrastructure.security.repository.mapper;

import org.example.library.domain.Role;
import org.example.library.domain.User;
import org.example.library.domain.UserRole;
import org.example.library.infrastructure.security.entity.RoleEntity;
import org.example.library.infrastructure.security.entity.UserEntity;
import org.example.library.infrastructure.security.entity.UserRoleEntity;
import org.springframework.stereotype.Component;

@Component
public class UserRoleEntityMapper {

    public UserRoleEntity map(Integer userId, Integer roleId) {
        return UserRoleEntity.builder()
                .user(mapUser(userId))
                .role(mapRole(roleId))
                .build();
    }

//    public UserRole mapFromUserRoleEntity(UserRoleEntity userRoleEntity) {
//        if (userRoleEntity == null){
//            return null;
//        }
//
//        return UserRole.builder()
//                .id(userRoleEntity.getId())
//                .user(mapFromUserEntity(userRoleEntity.getUser()))
//                .role(mapFromRoleEntity(userRoleEntity.getRole()))
//                .build();
//    }
//

    private UserEntity mapUser(Integer userId) {
        return UserEntity.builder()
                .userId(userId).build();
    }

    private RoleEntity mapRole(Integer roleId) {
        return RoleEntity.builder()
                .roleId(roleId).build();
    }

    private User mapFromUserEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        return User.builder()
                .userId(userEntity.getUserId())
                .build();
    }

    private Role mapFromRoleEntity(RoleEntity roleEntity) {
        if (roleEntity == null) {
            return null;
        }

        return Role.builder()
                .roleId(roleEntity.getRoleId())
                .build();
    }
//
//    public UserRoleEntity mapToUserRoleEntity(Integer userId, Integer roleId) {
//        if (userRole == null){
//            return null;
//        }
//
//        return UserRoleEntity.builder()
//                .user(userId)
//                .role(mapToRoleEntity(userRole.getRole()))
//                .build();
//    }
//
//    private UserEntity mapToUserEntity(User user) {
//        if (user == null){
//            return null;
//        }
//
//        return UserEntity.builder()
//                .userId(user.getUserId())
//                .build();
//    }
//
//    private RoleEntity mapToRoleEntity(Role role) {
//        if (role == null){
//            return null;
//        }
//
//        return RoleEntity.builder()
//                .roleId(role.getRoleId())
//                .build();
//    }
}
