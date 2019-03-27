package controllers;

import play.mvc.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import java.util.List;
import java.util.ArrayList;

import javax.inject.Inject;
import models.Expense;

import services.CustomRestService;
/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    @Inject
    private CustomRestService service;

    public Result explore() {
        return ok(views.html.explore.render());
    }

    public Result tutorial() {
        return ok(views.html.tutorial.render());
    }


    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getExpenses() {
        JsonNode res = service.getExpenses();
        List<Expense> expenses = new ArrayList<Expense>();

        for(JsonNode expense : res){
            expenses.add(Json.fromJson(expense, Expense.class));
        }

        if (res != null) {
            return ok(views.html.expenses.render(expenses));
        } else return ok();
    }
}
