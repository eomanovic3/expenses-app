package com.expenses.demo.services;


import com.expenses.demo.models.User;
import com.expenses.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getUsers() {

        List<User> usersFromDatabase = userRepository.findAll();

        if (usersFromDatabase == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List>(usersFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity<?> loginUser(String username, String password) {
        try {
            User user = this.userRepository.findByUsername(username);

            if (user.getPassword().equals(password)) {
                return new ResponseEntity<User>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Username or password is incorrect", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> logoutUser() {
        return new ResponseEntity("User is logged out.", HttpStatus.OK);
    }
}


