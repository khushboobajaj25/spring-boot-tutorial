package com.example.bootlearning.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.bootlearning.model.User;
import com.example.bootlearning.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    public boolean saveUser(User user) {
        return userRepository.saveUser(user);
    }

    public boolean deleteUserById(Integer id) {
        return userRepository.deleteUserById(id);
    }
}
