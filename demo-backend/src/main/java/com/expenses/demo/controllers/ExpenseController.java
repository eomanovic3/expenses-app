package com.expenses.demo.controllers;

import com.expenses.demo.models.Expense;
import com.expenses.demo.services.ExpensesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/expenses")
public class ExpenseController {

    private final ExpensesService expensesService;
    public ExpenseController(ExpensesService expensesService){
        this.expensesService = expensesService;
    }

    @GetMapping()
    public ResponseEntity getExpenses() {
        return expensesService.getExpenses();
    }

    @GetMapping(value = "/getExpense/{id}")
    public ResponseEntity getExpense(@PathVariable Integer id) {
        return expensesService.getExpense(id);
    }

    public ResponseEntity addExpense(@RequestBody Expense expense){
        return  expensesService.addExpense(expense);
    }

    @PutMapping(value = "/updateExpense/{id}")
    public ResponseEntity updateExpense(@PathVariable Integer id, @RequestBody Expense expense){
        return  expensesService.updateExpense(id, expense);
    }

    @DeleteMapping(value = "/deleteExpense/{id}")
    public ResponseEntity deleteExpense(@PathVariable Integer id){
        return  expensesService.deleteExpense(id);
    }
}
