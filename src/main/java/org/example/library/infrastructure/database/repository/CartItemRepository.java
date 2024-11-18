package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.CartItemDao;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CartItemRepository implements CartItemDao {
}
