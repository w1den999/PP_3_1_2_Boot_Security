package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;


@Component
public class DataInitializer {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void init() {

        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleService.saveRole(role1);
        roleService.saveRole(role2);

        User user = new User();
        user.setName("fsfasf");
        user.setSurname("eqeqeq");
        user.setEmail("Test1@mail.ru");
        user.setPassword("anna");
        user.addRole(role1);


        User user2 = new User();
        user2.setName("Artem");
        user2.setSurname("qeqe");
        user2.setEmail("Test2@mail.ru");
        user2.setPassword("artem");
        user2.addRole(role1);
        user2.addRole(role2);


        User user3 = new User();
        user3.setName("Ivan");
        user3.setSurname("Ivanov");
        user3.setEmail("Test3@mail.ru");
        user3.setPassword("ivan");
        user3.addRole(role2);

        User user4 = new User();
        user4.setName("Petr");
        user4.setSurname("Petrov");
        user4.setEmail("Test4@mail.ru");
        user4.setPassword("petr");
        user4.addRole(role2);

        User user5 = new User();
        user5.setName("Semen");
        user5.setSurname("Kruglov");
        user5.setEmail("Test5@mail.ru");
        user5.setPassword("semen");
        user5.addRole(role1);
        user5.addRole(role2);

        userService.saveUser(user);
        userService.saveUser(user2);
        userService.saveUser(user3);
        userService.saveUser(user4);
        userService.saveUser(user5);
    }
}
