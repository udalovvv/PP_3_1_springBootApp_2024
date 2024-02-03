package com.example.springboot.services;

import com.example.springboot.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();

    public void save(User user);

    public User findById(long id);

    public void updateUser(long id, User updatedUser);

    public void deleteUserById(long id);

}
