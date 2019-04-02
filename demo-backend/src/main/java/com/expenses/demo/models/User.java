package com.expenses.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull(message = "Email cannot be null")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Username cannot be null")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Password cannot be null")
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
