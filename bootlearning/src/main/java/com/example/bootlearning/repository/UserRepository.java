package com.example.bootlearning.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.bootlearning.exception.UserAlreadyExistException;
import com.example.bootlearning.exception.UserNotFoundException;
import com.example.bootlearning.model.User;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(Integer id) {
        return users.stream().filter(user -> user.id() == id).findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public boolean saveUser(User user) {
        if (user.id() != null) {
            Integer id = user.id();
            var userExist = users.stream().anyMatch(u -> u.id() == id);
            if (userExist) {
                throw new UserAlreadyExistException(id);
            }
        } else {
            Integer newId = users.isEmpty() ? 1 : users.get(users.size() - 1).id() + 1;
            user = new User(newId, user.name(), user.age());
        }
        users.add(user);
        return true;
    }

    public boolean deleteUserById(Integer id) {
        var initialSize = users.size();
        this.users = users.stream().filter(user -> user.id() != id).collect(Collectors.toList());
        return initialSize != users.size();
    }
}
