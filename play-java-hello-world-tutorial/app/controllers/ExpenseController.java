package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.CategoryPerMonth;
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
import views.html.filterData;

import javax.inject.Inject;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ExpenseController extends Controller {
    enum months {January, February, March, April, May, June, July, August, September, October, November, December};
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

        Form<Location> locationForm = formFactory.form(Location.class).bindFromRequest();
        Location location = locationForm.get();

        var serviceResult = service.getExpenseById(expense.getExpenseId());
        Expense expenseFromDatabase = Json.fromJson(serviceResult, Expense.class);

        expenseFromDatabase.setAmount(expense.getAmount());
        expenseFromDatabase.setDescription(expense.getDescription());
        expenseFromDatabase.setPayee(expense.getPayee());
        expenseFromDatabase.setPayMethod(expense.getPayMethod());
        expenseFromDatabase.setCategory(expense.getCategory());
        expenseFromDatabase.setLocation(location);
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

    public static List<CustomList> getCategoryGraphData(List<Expense> expenses) {
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
        return list;
    }

    public static List<CustomList> getMonthGraphData(List<Expense> expenses) {
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
        return monthList;
    }

    public static List<CustomList> getYearGraphData(List<Expense> expenses) {
        List<Integer> listOfYears = new ArrayList<Integer>();
        int yearCounter = 0;

        for (int j = 0; j < expenses.size(); j++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(expenses.get(j).getDate());
            int year = cal.get(Calendar.YEAR);
            listOfYears.add(year);
        }

        Set<Integer> uniqueYears = new HashSet<Integer>(listOfYears);
        var listOfCustomYears = new ArrayList<CustomList>();

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
        return listOfCustomYears;
    }

    public static List<CategoryPerMonth> getListOfExpensesByCategories(List<Expense> expenses) {
        var listOfExpensesByCategories = new ArrayList<CategoryPerMonth>();
        float categoryMoneyCounter = 0;

        for (int i = 0; i < Expense.Category.values().length; i++) {
            var category = Expense.Category.values()[i];
            var expenseId = -1;
            for (int j = 0; j < expenses.size(); j++) {
                if (expenses.get(j).getCategory() == category) {
                    if (expenses.get(j).getExpenseAdded() == true) {
                        categoryMoneyCounter = categoryMoneyCounter - expenses.get(j).getAmount();
                    } else {
                        categoryMoneyCounter = categoryMoneyCounter + expenses.get(j).getAmount();
                    }
                }
            }
            listOfExpensesByCategories.add(new CategoryPerMonth(category.toString(), categoryMoneyCounter));
            categoryMoneyCounter = 0;
        }
        return listOfExpensesByCategories;
    }

    public static Result returnDashboard(CustomRestService expenseService) {
        JsonNode res = expenseService.getExpenses();
        List<Expense> expenses = new ArrayList<Expense>();
        for (JsonNode expense : res) {
            expenses.add(Json.fromJson(expense, Expense.class));
        }

        var getCategoryGraphData = getCategoryGraphData(expenses);
        var getMonthGraphData = getMonthGraphData(expenses);
        var getYearGraphData = getYearGraphData(expenses);

        if (res != null) {
            return ok(views.html.dashboard.render(getCategoryGraphData, getMonthGraphData, getYearGraphData, expenses));
        } else return ok();
    }

    public Date returnDateFromLonVariables(Long dateInput) {
        String startDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(dateInput));
        SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date date = null;
        Date startDate = null;
        try {
            date = sdf1.parse(startDateString);
            startDate = new java.sql.Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public Result filterData(Long start, Long end) {
        // or you already have long value of date, use this instead of milliseconds variable.
        var startDate = returnDateFromLonVariables(start);
        var endDate = returnDateFromLonVariables(end);

        JsonNode res = service.getExpenses();
        List<Expense> expenses = new ArrayList<Expense>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (JsonNode expense : res) {
            Date date = Json.fromJson(expense, Expense.class).getDate();
            if ((date.after(startDate) && date.before(endDate)) ||
                    (dateFormat.format(date).equals(dateFormat.format(endDate)) ||
                            (dateFormat.format(date).equals(dateFormat.format(startDate)) ||
                  ((dateFormat.format(date).equals(dateFormat.format(endDate))) &&
                    (dateFormat.format(date).equals(dateFormat.format(startDate))))))){
                expenses.add(Json.fromJson(expense, Expense.class));
            }
        }
        double incomesAmount = 0.00;
        double expensesAmount = 0;
        for(Expense expense: expenses){
            if(expense.getExpenseAdded() == true){
                expensesAmount = expensesAmount - expense.getAmount();
            } else{
                incomesAmount = incomesAmount + expense.getAmount();
            }
        }
        var total = expensesAmount + incomesAmount;
        var listOfExpensesByCategories = getListOfExpensesByCategories(expenses);
        return ok(filterData.render(listOfExpensesByCategories, incomesAmount, expensesAmount, total));
    }
}
