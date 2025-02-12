package org.example.library.infrastructure.security.business.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface UserRoleDao {

    void saveUserRole(Integer userId, Integer roleId);
}
