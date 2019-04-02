package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Expense;
import models.Location;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.CustomRestService;
import views.html.editExpense;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class ExpenseController extends Controller {
    @Inject
    private CustomRestService service;

    @Inject
    private FormFactory formFactory;

    @Security.Authenticated(Secured.class)
    public Result index() {
        JsonNode res = service.getExpenses();
        return getExpensesList(res);
    }

    @Security.Authenticated(Secured.class)
    public Result dashboard() {
        return ok(views.html.dashboard.render());
    }

    @Security.Authenticated(Secured.class)
    public Result deleteExpense(int id) {
        JsonNode res = service.deleteExpense(id);
        return getExpensesList(res);
    }

    @Security.Authenticated(Secured.class)
    public Result addExpense() {
        Form<Expense> expenseForm = formFactory.form(Expense.class);
        Expense expense = expenseForm.bindFromRequest().get();
        expense.setDate(new Date());
        if(expense.getExpenseAdded() == null){
            expense.setExpenseAdded(false);
        }

        Form<Location> locationForm = formFactory.form(Location.class);
        Location location = locationForm.bindFromRequest().get();

        expense.setLocation(location);

        JsonNode res = service.addExpense(expense);
        return getExpensesList(res);
    }

    @Security.Authenticated(Secured.class)
    public Result editExpense(Integer id) {
        var serviceResult = service.getExpenseById(id);
        Expense expenseFromDatabase = Json.fromJson(serviceResult, Expense.class);

        Form<Expense> expenseForm = formFactory.form(Expense.class).fill(expenseFromDatabase);
        Form<Location> locationForm = formFactory.form(Location.class).fill(expenseFromDatabase.getLocation());

        return ok(editExpense.render(expenseForm, locationForm));
    }

    @Security.Authenticated(Secured.class)
    public Result updateExpense() {
        Form<Expense> expenseForm = formFactory.form(Expense.class).bindFromRequest();
        Expense expense = expenseForm.get();

        var serviceResult = service.getExpenseById(expense.getExpenseId());
        Expense expenseFromDatabase = Json.fromJson(serviceResult, Expense.class);

        expenseFromDatabase.setAmount(expense.getAmount());
        expenseFromDatabase.setDescription(expense.getDescription());
        expenseFromDatabase.setPayee(expense.getPayee());
        expenseFromDatabase.setPayMethod(expense.getPayMethod());
        expenseFromDatabase.setCategory(expense.getCategory());
        expenseFromDatabase.setLocation(new Location());
        JsonNode res = service.editExpenseInService(expenseFromDatabase);
        return getExpensesList(res);
    }

    @Security.Authenticated(Secured.class)
    public Result populateModal(Integer id) {
        return ok();
    }

    @Security.Authenticated(Secured.class)
    public Result getExpensesList(JsonNode res) {
        List<Expense> expenses = new ArrayList<Expense>();
        for (JsonNode expense : res) {
            expenses.add(Json.fromJson(expense, Expense.class));
        }
        if (res != null) {
            return ok(views.html.index.render(expenses));
        } else return ok();
    }
}
