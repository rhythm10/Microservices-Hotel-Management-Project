package com.lcwd.user.services;

import com.lcwd.user.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    User deleteUser(String userId);

    User updateUser(User user);

}
