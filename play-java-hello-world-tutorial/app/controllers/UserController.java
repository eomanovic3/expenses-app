package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Login;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.CustomRestService;

import javax.inject.Inject;

public class UserController extends Controller {
    @Inject
    private CustomRestService service;

    @Inject
    private FormFactory formFactory;

    public Result login() {
        if (session().containsKey("username")) {
            return ExpenseController.returnDashboard(service);
        } else {
            Form<Login> loginForm = formFactory.form(Login.class);
            return ok(views.html.login.render(loginForm));
        }
    }

    public Result showErrorPage() {
        return ok(views.html.error.render());
    }

    public Result authenticate() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();
        Login login = loginForm.bindFromRequest().get();
        JsonNode res = service.login(login);

        if (loginForm.hasErrors() || res == null) {
            return redirect(
                    routes.UserController.showErrorPage()
            );
        } else {
            session().clear();
            session("username", loginForm.get().username);
            session("alert", "TRUE");
            Http.Context.current().session().put("username", loginForm.get().username);
            return redirect(
                    routes.ExpenseController.dashboard()
            );
        }
    }

    public Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                routes.UserController.login()
        );
    }
}
