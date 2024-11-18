package org.example.library.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.example.library.business.dao.CartDao;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class CartRepository implements CartDao {
}
