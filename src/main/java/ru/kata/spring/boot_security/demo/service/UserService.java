package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void saveUserForDatabaseInitializer(User user);

    void saveUserWithRoles(User user, Long[] rolesId);

    void updateUserWithRoles(User user, Long[] rolesId);

    void deleteUserById(Long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    User getUserById(Long id);

    List<Role> getAllRoles();
}

