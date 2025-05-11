package ru.kata.spring.boot_security.demo.dao;



import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("from User", User.class).getResultList();
        System.out.println("Loaded users: " + users);
        return users;
    }


    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = entityManager.find(User.class, id);
        if (user !=null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


}
