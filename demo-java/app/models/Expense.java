
import java.sql.Timestamp;

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

    public Expense(Float amount, Category category,PAYMETHOD payMethod, Timestamp datetime, String description, Location location) {
        this.amount = amount;
        this.category = category;
        this.payMethod = payMethod;
        this.datetime = datetime;
        this.description = description;
        this.location = location;
    }
}
