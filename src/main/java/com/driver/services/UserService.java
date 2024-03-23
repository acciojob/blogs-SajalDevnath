package com.driver.services;

import com.driver.models.User;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String username, String password) {
        User newUser = new User(username, password);
        return userRepository.save(newUser);
    }

    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(Integer id, String password) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setPassword(password);
            return userRepository.save(existingUser);
        }
        return null;
    }
}
