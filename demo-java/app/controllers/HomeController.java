package controllers;

import play.mvc.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import javax.inject.Inject;

import services.CustomRestService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller{

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    @Inject
    private CustomRestService service;

    public Result index() {
        return ok(views.html.index.render());
    }

    public Result getExpenses() {
        JsonNode res = service.getExpenses();
        String result = Json.toJson(res).toString();
        if (res != null) {
            return ok(views.html.expenses.render(result));
        } else return ok();
    }


}
