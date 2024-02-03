package com.example.springboot.services;

import com.example.springboot.entity.User;
import com.example.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(long id, User updatedUser) {
        userRepository.updateUser(id, updatedUser);
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }
}
