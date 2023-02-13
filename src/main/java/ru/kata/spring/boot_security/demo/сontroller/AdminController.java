package ru.kata.spring.boot_security.demo.сontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping
    public String getAdminPage(@ModelAttribute("user") User user,
                               Model model, Principal principal) {

        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("admin", userService.getUserByUsername(principal.getName()));

        return "admin_page";
    }

    @PostMapping(value = "/new")
    public String addNewUserToDb(User user, @RequestParam("listRoles") Long[] id) {
        user.setRoles(roleService.findAllRolesById(id));
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserFromDb(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/edit")
    public String saveEditedUserToDb(@ModelAttribute("user") User user, @RequestParam("roles") Long[] id) {
        user.setRoles(roleService.findAllRolesById(id));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/info")
    public String ogetInfoAboutAdmin(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "admin_page_info";
    }
}



