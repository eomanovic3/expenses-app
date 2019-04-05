package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Expense {

    public enum Category {
        HEALTH_CARE,
        FOOD,
        AUTOMOBILE,
        PRESENTS,
        OTHER
    }

    public enum PAYMETHOD {
        CASH,
        CARD,
        DEBIT,
        OTHER
    }

    private Integer expenseId;
    private Float amount;
    private Category category;
    private PAYMETHOD payMethod;
    private Date date;
    private String description;
    private String payee;
    private Location location;
    private Boolean expenseAdded;

    public Expense() {
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
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

    @JsonIgnore
    public String getDateFormatted() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM");
        String Format = format.format(this.date);
        return Format;
    }
    public Expense(Float amount, Category category, PAYMETHOD payMethod, Date date, String description, String payee,
                   Location location, Boolean expenseAdded) {
        this.amount = amount;
        this.category = category;
        this.payMethod = payMethod;
        this.date = date;
        this.description = description;
        this.payee = payee;
        this.location = location;
        this.expenseAdded = expenseAdded;
    }
}