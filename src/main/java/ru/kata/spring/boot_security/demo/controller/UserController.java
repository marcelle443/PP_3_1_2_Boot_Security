package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String userPage() {
        return "user";
    }


    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user";

    }

    @GetMapping("/add")
    public String showAddForm() {
        return "add_user_form";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam Long id,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String email,
                          @RequestParam Set<Role> roles) {
        User user = new User(id, username, password, firstName, lastName, email, roles);
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, ModelMap model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit_user_form";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("firstName") String firstName,
                             @RequestParam("lastName") String lastName,
                             @RequestParam("email") String email) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/";
    }


}

