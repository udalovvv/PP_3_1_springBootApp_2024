package com.example.springboot.repositories;

import com.example.springboot.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("From User").getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findById(long id) {
        if (em.find(User.class, id) == null) {
            throw new NullPointerException("User with id=" + id + " is not find");
        }
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        User user = findById(id);
        if (user != null) {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setAge(updatedUser.getAge());
            user.setEmail(updatedUser.getEmail());
            em.merge(user);
        }
    }

    @Override
    public void deleteById(long id) {
        em.remove(findById(id));
    }
}
