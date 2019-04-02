package controllers;

import models.Login;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import javax.inject.Inject;
import java.util.Optional;

import static play.mvc.Controller.session;

public class Secured extends Security.Authenticator {

    @Inject
    FormFactory formFactory;

    @Override
    public Optional<String> getUsername(Http.Request request) {
        return Optional.ofNullable(Http.Context.current().session().get("username"));
    }

    @Override
    public Result onUnauthorized(Http.Request request) {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(views.html.login.render(loginForm));
    }
}