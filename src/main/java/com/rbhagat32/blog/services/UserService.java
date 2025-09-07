package com.rbhagat32.blog.services;

import com.rbhagat32.blog.models.UserModel;
import com.rbhagat32.blog.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public Optional<UserModel> getUserById(ObjectId userId) {
        return userRepository.findById(userId);
    }

    public UserModel updateUser(String username, UserModel newUser) {
        UserModel user = userRepository.findByUsername(username);

        if (user != null) {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            userRepository.save(user);
        }

        return user;
    }

    public Optional<UserModel> deleteUserById(ObjectId userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        user.ifPresent(b -> userRepository.deleteById(userId));
        return user;
    }

    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}