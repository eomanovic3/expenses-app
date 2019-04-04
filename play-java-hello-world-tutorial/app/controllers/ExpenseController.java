package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.CustomList;
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
import java.util.*;


public class ExpenseController extends Controller {
    enum months {January, February, March, April, May, June, July, August, September, October, November, December};
    @Inject
    private CustomRestService service;

    @Inject
    private FormFactory formFactory;

    public Result location(){
        return ok(views.html.location.render());
    }

    @Security.Authenticated(Secured.class)
    public Result index() {
        JsonNode res = service.getExpenses();
        return getExpensesList(res);
    }

    @Security.Authenticated(Secured.class)
    public Result dashboard() {
        return returnDashboard(service);
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
        Calendar c = Calendar.getInstance();
        expense.setDate(new java.sql.Date(c.getTimeInMillis()));
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

    public static Result returnDashboard(CustomRestService expenseService) {
        JsonNode res = expenseService.getExpenses();
        List<Expense> expenses = new ArrayList<Expense>();
        for (JsonNode expense : res) {
            expenses.add(Json.fromJson(expense, Expense.class));
        }

        var list = new ArrayList<CustomList>();

        int count = 0;
        for (int i = 0; i < Expense.Category.values().length; i++) {
            var category = Expense.Category.values()[i];
            for (int j = 0; j < expenses.size(); j++) {
                if (expenses.get(j).getCategory() == category) {
                    count = count + 1;
                }
            }
            list.add(new CustomList(category.toString(), count));
            count = 0;
        }
        var monthList = new ArrayList<CustomList>();
        int monthCounter = 0;

        for (int i = 0; i < months.values().length; i++) {
            for (int j = 0; j < expenses.size(); j++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(expenses.get(j).getDate());
                int month = cal.get(Calendar.MONTH);
                if (month == i) {
                    monthCounter = monthCounter + 1;
                }
            }
            monthList.add(new CustomList(months.values()[i].toString(), monthCounter));
            monthCounter = 0;
        }

        List<Integer> listOfYears = new ArrayList<Integer>();
        int yearCounter = 0;

        for (int j = 0; j < expenses.size(); j++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(expenses.get(j).getDate());
            int year = cal.get(Calendar.YEAR);
            listOfYears.add(year);
        }

        Set<Integer> uniqueYears = new HashSet<Integer>(listOfYears);
        var listOfCustomYears =  new ArrayList<CustomList>();

        for (Integer uniqueYear : uniqueYears) {
            for (int j = 0; j < expenses.size(); j++) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(expenses.get(j).getDate());
                int year = cal.get(Calendar.YEAR);
                if (year == uniqueYear) {
                    yearCounter = yearCounter + 1;
                }
            }
            listOfCustomYears.add(new CustomList(uniqueYear.toString(), yearCounter));
            yearCounter = 0;
        }

        if (res != null) {
            return ok(views.html.dashboard.render(list, monthList, listOfCustomYears));
        } else return ok();
    }
}
