package com.lcwd.user.services.impl;

import com.lcwd.user.Exceptions.ResourceNotFoundException;
import com.lcwd.user.entities.User;
import com.lcwd.user.repositories.UserRepository;
import com.lcwd.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User ID is not found on Server : " + userId));
    }

    @Override
    public User deleteUser(String userId) {
        User user = getUser(userId);
        userRepository.deleteById(userId);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
