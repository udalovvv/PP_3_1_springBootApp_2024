package com.example.springBootApp.service;

import com.example.springBootApp.entity.User;
import java.util.List;

public interface UserService {
    List<User> showAll();

    public void save(User user);

    public User showUser(long id);

    public void update(long id, User updatedUser);

    public void delete(long id);

}
