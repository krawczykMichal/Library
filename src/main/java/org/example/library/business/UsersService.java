package org.example.library.business;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.example.library.api.dto.UsersDTO;
import org.example.library.business.dao.*;
import org.example.library.domain.Role;
import org.example.library.domain.User;
import org.example.library.domain.Users;
import org.example.library.domain.exception.NotFoundException;
import org.example.library.infrastructure.security.business.dao.RoleDao;
import org.example.library.infrastructure.security.business.dao.UserDao;
import org.example.library.infrastructure.security.business.dao.UserRoleDao;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UsersService {

    @PersistenceContext
    private EntityManager em;

    private final UsersDao usersDao;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final CartDao cartDao;
    private final ReservationsDao reservationsDao;
    private final ReservationItemDao reservationItemDao;
    private final LoanRequestDao loanRequestDao;
    private final LoanRequestItemDao loanRequestItemDao;
    private final LoansDao loansDao;
    private final LoanItemDao loanItemDao;


    private final UserRoleDao userRoleDao;

    @Transactional
    public Users saveUser(UsersDTO usersDTO) {
        Users user = registerUser(usersDTO);
        usersDao.saveUser(user);
        User user1 = user.getUser();
        userDao.saveUser(user1);

        return user;
    }

    public void assignRoleToUser(String username) {
        User user = userDao.findByUsername(username);
        System.out.println("user: " + user);
        Role role = roleDao.findRoleByName("USER").get();
        System.out.println("roleToAssign: " + role);

        userRoleDao.saveUserRole(user.getUserId(), role.getRoleId());
    }

    private Users registerUser(UsersDTO usersDTO) {
        Set<Role> roleSet = roleDao.findRole();

        return Users.builder()
                .name(usersDTO.getName())
                .surname(usersDTO.getSurname())
                .username(usersDTO.getUsername())
                .email(usersDTO.getEmail())
                .phoneNumber(usersDTO.getPhoneNumber())
                .user(User.builder()
                        .email(usersDTO.getEmail())
                        .username(usersDTO.getUsername())
                        .password(hashPassword(usersDTO.getUsersUserPassword()))
                        .active(true)
                        .role(roleSet)
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



        Users updatedUser = userToUpdate.withName(usersDTO.getName() != null && !usersDTO.getName().isEmpty() ? usersDTO.getName() : userToUpdate.getName())
                .withSurname(usersDTO.getSurname() != null && !usersDTO.getSurname().isEmpty() ? usersDTO.getSurname() : userToUpdate.getSurname())
                .withEmail(usersDTO.getEmail() != null && !usersDTO.getEmail().isEmpty() ? usersDTO.getEmail() : userToUpdate.getEmail())
                .withUsername(usersDTO.getUsername() != null && !usersDTO.getUsername().isEmpty() ? usersDTO.getUsername() : userToUpdate.getUsername())
                .withPhoneNumber(usersDTO.getPhoneNumber() != null && !usersDTO.getPhoneNumber().isEmpty() ? usersDTO.getPhoneNumber() : userToUpdate.getPhoneNumber());


        usersDao.saveUser(updatedUser);
    }

    @Transactional
    public void deleteById(Integer userId) {
        loanItemDao.deleteByLoanUserId(userId);
        loansDao.deleteByUserId(userId);
        loanRequestItemDao.deleteByLoanRequestCartUserId(userId);
        loanRequestDao.deleteByCartUserId(userId);
        reservationItemDao.deleteByReservationCartUserId(userId);
        reservationsDao.deleteByCartUserId(userId);
        cartDao.deleteByUserId(userId);
        usersDao.deleteById(userId);
    }

    public List<Users> findAll() {
        return usersDao.findAll();
    }
}
