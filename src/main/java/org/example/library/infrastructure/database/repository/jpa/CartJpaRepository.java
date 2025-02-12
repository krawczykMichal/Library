package org.example.library.infrastructure.database.repository.jpa;

import aj.org.objectweb.asm.commons.Remapper;
import org.example.library.domain.Cart;
import org.example.library.infrastructure.database.entity.CartEntity;
import org.example.library.infrastructure.database.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, Integer> {

    @Query("select cart from CartEntity cart where cart.user.userId = :userId")
    Optional<CartEntity> findByUserId(Integer userId);


    List<CartEntity> user(UsersEntity user);

    @Modifying
    @Query("delete from CartEntity c where c.user.userId = :userId")
    void deleteByUserId(Integer userId);
}
