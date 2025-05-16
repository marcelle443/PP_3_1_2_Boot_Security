package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImp implements UserService {


    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImp(UserDao userDao, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void createUser(String username, String password, String firstName, String lastName,
                           Integer age, String email, List<String> roles) {
        Set<Role> roleSet = roles.stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(null, username, encodedPassword, firstName, lastName, age, email, roleSet);
        userDao.add(user);
    }

    @Override
    public void updateUser(Long id, String username, String password, String firstName, String lastName,
                           Integer age, String email, List<String> roles) {
        Set<Role> roleSet = roles.stream()
                .map(roleRepository::findByName)
                .collect(Collectors.toSet());

        User existingUser = userDao.getUserById(id);
        String finalPassword = (password == null || password.isBlank())
                ? existingUser.getPassword()
                : passwordEncoder.encode(password);

        User updatedUser = new User(id, username, finalPassword, firstName, lastName, age, email, roleSet);
        userDao.update(updatedUser);
    }
}

