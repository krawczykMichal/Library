package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Cart;
import org.example.library.infrastructure.database.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartEntityMapper {

    Cart mapFromCartEntity(CartEntity cartEntity);

    CartEntity mapToCartEntity(Cart cart);
}
