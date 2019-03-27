package models;

import java.sql.Timestamp;
import models.Location;

public class Expense{

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

    private Integer expenseId;
    private Float amount;
    private Category category;
    private PAYMETHOD payMethod;
    private Timestamp datetime;
    private String description;
    private String payee;
    private Location location;

    public Expense() {}

    public Expense(Integer expenseId, Float amount, Category category,PAYMETHOD payMethod, Timestamp datetime, String description, Location location) {
        this.expenseId = expenseId;
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
