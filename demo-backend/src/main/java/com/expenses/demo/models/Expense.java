package com.expenses.demo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "expense")
public class Expense {

    public enum Category
    {
        HEALTH_CARE,
        FOOD,
        AUTOMOBILE
    }
    public enum PAYMETHOD
    {
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

    @NotNull(message = "Date cannot be null")
    @Column(name = "datetime")
    private Timestamp datetime;

    @Column(name = "description")
    private String description;

    @Column(name = "payee")
    private String payee;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "locationId")
    private Location location;

    public Expense() {}

    public Expense(@NotNull(message = "Amount cannot be null") Float amount, @NotNull(message = "Category cannot be null") Category category, @NotNull(message = "Pay method cannot be null") PAYMETHOD payMethod, @NotNull(message = "Date cannot be null") Timestamp datetime, String description, Location location) {
        this.amount = amount;
        this.category = category;
        this.payMethod = payMethod;
        this.datetime = datetime;
        this.description = description;
        this.location = location;
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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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
}
