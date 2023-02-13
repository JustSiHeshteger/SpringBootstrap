package ru.kata.spring.boot_security.demo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DatabaseInitializer {
    private final UserService userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public DatabaseInitializer(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void dataBaseInit() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.addNewRole(roleAdmin);
        roleService.addNewRole(roleUser);

        User user1 = new User("admin", "admin", "admin", "admin", Set.of(roleAdmin, roleUser));
        User user2 = new User("user", "user", "user", "user", Set.of(roleUser));

        userService.saveUser(user1);
        userService.saveUser(user2);
    }
}
