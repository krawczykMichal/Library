package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.AuthorsDTO;
import org.example.library.business.dao.AuthorsDao;
import org.example.library.domain.Authors;
import org.example.library.domain.exception.NotFoundException;
import org.example.library.infrastructure.database.repository.AuthorsRepository;
import org.example.library.infrastructure.database.repository.mapper.AuthorsEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

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
                .surname(authorsDTO.getSurname())
                .authorCode(createAuthorCode()).build();
    }

    protected String createAuthorCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder employeeNumber = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int digit = random.nextInt(10);
            employeeNumber.append(digit);
        }
        return employeeNumber.toString();
    }

    @Transactional
    public void updateAuthor(Authors author, AuthorsDTO authorsDTO) {

        Authors updateAuthor = author.withName(authorsDTO.getName())
                .withSurname(authorsDTO.getSurname());

        authorsDao.saveAuthor(updateAuthor);
    }

    public List<Authors> findAll() {
        return authorsDao.findAll();
    }

    public Authors findByAuthorCode(String booksAuthorCode) {
        Optional<Authors> author = authorsDao.findByAuthorCode(booksAuthorCode);
        if (author.isEmpty()) {
            throw new NotFoundException("Can't find author with author code " + booksAuthorCode);
        }
        return author.get();
    }
}
