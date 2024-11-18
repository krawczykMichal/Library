package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.CartItem;
import org.example.library.infrastructure.database.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartItemEntityMapper {

    CartItem mapFromCartItemEntity(CartItemEntity cartItemEntity);

    CartItemEntity mapToCartItemEntity(CartItem cartItem);
}
