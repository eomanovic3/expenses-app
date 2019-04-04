package com.expenses.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "expense")
public class Expense {

    public enum Category {
        HEALTH_CARE,
        FOOD,
        AUTOMOBILE,
        PRESENTS
    }

    public enum PAYMETHOD {
        CASH,
        CARD,
        DEBIT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer expenseId;

    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount")
    private Float amount;

    @NotNull(message = "Category cannot be null")
    @Column(name = "category")
    private Category category;

    @NotNull(message = "Method cannot be null")
    @Column(name = "payMethod")
    private PAYMETHOD payMethod;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "payee")
    private String payee;

    @Column(name = "expense")
    private Boolean expenseAdded;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "locationId")
    private Location location;

    public Expense() {}

    public Expense(Float amount, Category category, PAYMETHOD payMethod, Date date,
                   String description, String payee, Location location, Boolean expenseAdded) {
        this.amount = amount;
        this.category = category;
        this.payMethod = payMethod;
        this.date = date;
        this.description = description;
        this.payee = payee;
        this.location = location;
        this.expenseAdded = expenseAdded;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public PAYMETHOD getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PAYMETHOD payMethod) {
        this.payMethod = payMethod;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Boolean getExpenseAdded() {
        return expenseAdded;
    }

    public void setExpenseAdded(Boolean expenseAdded) {
        this.expenseAdded = expenseAdded;
    }
}
