package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.domain.Authors;
import org.example.library.infrastructure.database.repository.AuthorsRepository;
import org.example.library.infrastructure.database.repository.mapper.AuthorsEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthorsService {

    private final AuthorsDao authorsDao;

    @Transactional
    public void saveAuthor(AuthorsDTO authorsDTO) {
        Authors author = registerAuthor(authorsDTO);
        authorsDao.saveAuthor(author);
    }

    private Authors registerAuthor(AuthorsDTO authorsDTO) {
        return Authors.builder()
                .name(authorsDTO.getName())
                .surname(authorsDTO.getSurname()).build();
    }
}
