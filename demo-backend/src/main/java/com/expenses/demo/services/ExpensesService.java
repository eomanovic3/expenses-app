package com.expenses.demo.services;

import com.expenses.demo.models.Expense;
import com.expenses.demo.repositories.ExpensesRepository;
import com.expenses.demo.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExpensesService {

    private final ExpensesRepository expensesRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public ExpensesService(ExpensesRepository expensesRepository, LocationRepository locationRepository) {
        this.expensesRepository = expensesRepository;
        this.locationRepository = locationRepository;
    }

    public ResponseEntity<?> getExpenses() {

        List<Expense> expensesFromDatabase = expensesRepository.findAll();

        if (expensesFromDatabase == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<List>(expensesFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity<?> getExpense(Integer id) {

        Expense expenseFromDatabase = expensesRepository.findByExpenseId(id);

        if (expenseFromDatabase == null)
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        return new ResponseEntity(expenseFromDatabase, HttpStatus.OK);
    }

    public ResponseEntity addExpense(Expense expense) {
        expensesRepository.save(expense);
        return new ResponseEntity(expense, HttpStatus.OK);
    }

    public ResponseEntity updateExpense(Integer id, Expense expense) {
        Optional<Expense> expenseInDatabase = expensesRepository.findById(id);

        if (!expenseInDatabase.isPresent()) {
            return new ResponseEntity("Expense with id:" + id + " was not found.", HttpStatus.NOT_FOUND);
        }
//        expenseInDatabase.get().setLocation(expense.getLocation());
        expenseInDatabase.get().setPayMethod(expense.getPayMethod());
        expenseInDatabase.get().setDescription(expense.getDescription());
        expenseInDatabase.get().setDatetime(expense.getDatetime());
        expenseInDatabase.get().setCategory(expense.getCategory());
        expenseInDatabase.get().setAmount(expense.getAmount());
        expensesRepository.save(expenseInDatabase.get());
        return new ResponseEntity(expensesRepository.getOne(id), HttpStatus.OK);
    }

    public ResponseEntity deleteExpense(Integer id) {
        expensesRepository.deleteById(id);
        List<Expense> expenses = expensesRepository.findAll();
        return new ResponseEntity(expenses, HttpStatus.OK);
    }

}
