package com.expenses.demo.repositories;

import com.expenses.demo.models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    Expense findByExpenseId(Integer id);

}
