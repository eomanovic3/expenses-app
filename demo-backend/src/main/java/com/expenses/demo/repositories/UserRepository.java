package com.expenses.demo.repositories;


import com.expenses.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(Integer id);
    User findByUsername(String username);
}
