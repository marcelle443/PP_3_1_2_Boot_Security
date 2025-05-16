package ru.kata.spring.boot_security.demo.service;





import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void update(User user);

    void delete(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    void createUser(String username, String password, String firstName, String lastName, Integer age, String email, List<String> roles);

    void updateUser(Long id, String username, String password, String firstName, String lastName, Integer age, String email, List<String> roles);

}
