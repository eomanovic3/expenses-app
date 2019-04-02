package com.expenses.demo.controllers;

import com.expenses.demo.models.Login;
import com.expenses.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity getUsers() {
        return userService.getUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginUser(@RequestBody Login login) {
        return userService.loginUser(login.getUsername(), login.getPassword());
    }
}
