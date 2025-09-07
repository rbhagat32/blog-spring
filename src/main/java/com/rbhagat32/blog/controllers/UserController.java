package com.rbhagat32.blog.controllers;

import com.rbhagat32.blog.models.BlogModel;
import com.rbhagat32.blog.models.UserModel;
import com.rbhagat32.blog.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        List<UserModel> allUsers = userService.getAllUsers();

        if (allUsers == null || allUsers.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel body) {
        try {
            UserModel newUser = userService.createUser(body);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable ObjectId userId) {
        Optional<UserModel> user = userService.getUserById(userId);

        if (user.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.get(),  HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserModel> updateUser(@PathVariable String username, @RequestBody UserModel body) {
        try {
            UserModel updatedUser = userService.updateUser(username, body);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}