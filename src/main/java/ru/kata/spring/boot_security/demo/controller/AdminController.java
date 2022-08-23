package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class AdminController {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/users")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/admin/users/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "newUser";
    }

    @PostMapping("/admin/users")
    public String creat(@ModelAttribute("user") User user, @RequestParam(value = "index", required = false) Long[] identifiers) {
        if (identifiers != null) {
            for (Long roleId : identifiers) {
                user.addRole(roleService.findRoleById(roleId));
            }
        } else {
            user.addRole(roleService.findRoleById(2L));
        }
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/admin/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id , @RequestParam(value = "index", required = false) Long[] identifiers) {

        if (identifiers != null) {
            for (Long roleId : identifiers) {
                user.addRole(roleService.findRoleById(roleId));
            }
        } else {
            user.addRole(roleService.findRoleById(2L));
        }
        userService.updateUser(user, id);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/admin/users/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
   }
}
