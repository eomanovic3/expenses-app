package models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import play.data.format.Formats;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String Format = format.format(this.date);
        return Format;
    }

    @JsonIgnore
    public String formatCategory(int number){
        var categoryValue = category.values()[number].toString();
        return (categoryValue.substring(0, 1).toUpperCase() + categoryValue.substring(1)).replace("_"," ");
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