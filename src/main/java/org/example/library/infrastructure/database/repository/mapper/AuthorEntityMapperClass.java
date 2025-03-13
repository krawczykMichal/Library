package org.example.library.infrastructure.database.repository.mapper;

import org.example.library.domain.Authors;
import org.example.library.infrastructure.database.entity.AuthorsEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorEntityMapperClass {

    public Authors mapFromAuthorsEntity(AuthorsEntity authorsEntity) {
        if (authorsEntity == null)
        {
            return null;
        }

        return Authors.builder()
                .authorId(authorsEntity.getAuthorId())
                .name(authorsEntity.getName())
                .surname(authorsEntity.getSurname())
                .authorCode(authorsEntity.getAuthorCode())
                .build();
    }
}
