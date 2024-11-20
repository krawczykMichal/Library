package org.example.library.business;

import lombok.AllArgsConstructor;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.dao.UsersDao;
import org.example.library.domain.Role;
import org.example.library.domain.User;
import org.example.library.domain.Users;
import org.example.library.domain.exception.NotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {

    private final UsersDao usersDao;
    private final CartService cartService;

    @Transactional
    public void saveUser(UsersDTO usersDTO) {
        Users user = registerUser(usersDTO);
        usersDao.saveUser(user);
    }

    private Users registerUser(UsersDTO usersDTO) {
        return Users.builder()
                .name(usersDTO.getName())
                .surname(usersDTO.getSurname())
                .username(usersDTO.getUsername())
                .email(usersDTO.getEmail())
                .phoneNumber(usersDTO.getPhoneNumber())
                .user(User.builder()
                        .username(usersDTO.getUsername())
                        .password(hashPassword(usersDTO.getUsersUserPassword()))
                        .active(true)
                        .role(Role.builder()
                                .role("USER")
                                .build())
                        .build())
                .build();
    }

    private String hashPassword(String usersUserPassword) {
        return BCrypt.hashpw(usersUserPassword, BCrypt.gensalt(12));
    }

    @Transactional
    public Users findByUsername(String username) {
        Optional<Users> user = usersDao.findByUsername(username);
        if (user.isEmpty()) {
            throw new NotFoundException("Could not find user with username: " + username);
        }
        return user.get();
    }

    @Transactional
    public Users findById(Integer userId) {
        Optional<Users> user = usersDao.findById(userId);

        if (user.isEmpty()) {
            throw new NotFoundException("Could not find user with id: " + userId);
        }

        return user.get();
    }

    @Transactional
    public void updateUsers(Integer userId, UsersDTO usersDTO) {
        Users userToUpdate = findById(userId);

        Users updatedUser = userToUpdate.withName(usersDTO.getName())
                .withSurname(usersDTO.getSurname())
                .withUsername(usersDTO.getUsername())
                .withEmail(usersDTO.getEmail())
                .withPhoneNumber(usersDTO.getPhoneNumber());

        usersDao.saveUser(updatedUser);
    }

    @Transactional
    public void deleteById(Integer userId) {
        usersDao.deleteById(userId);
    }
}
