package services;

import com.fasterxml.jackson.databind.JsonNode;
import models.Expense;
import models.Login;
import play.Logger;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class CustomRestService {

    @Inject
    WSClient ws;

    public JsonNode getExpenses() {
        WSRequest request = ws.url("http://localhost:8094/expenses");
        return get(request);
    }

    public JsonNode getExpenseById(int id) {
        WSRequest request = ws.url("http://localhost:8094/expenses/getExpense/" + id);
        return get(request);
    }

    public JsonNode addExpense(Expense expense) {
        WSRequest request = ws.url("http://localhost:8094/expenses/addExpense");
        JsonNode obj = Json.toJson(expense);
        return add(obj, request);
    }


    public JsonNode editExpenseInService(Expense expense) {
        WSRequest request = ws.url("http://localhost:8094/expenses/updateExpense/" + expense.getExpenseId());
        JsonNode obj = Json.toJson(expense);
        return edit(obj, request);
    }

    public JsonNode deleteExpense(Integer id) {
        WSRequest request = ws.url("http://localhost:8094/expenses/deleteExpense/" + id);
        return delete(request);
    }

    public JsonNode getUsers() {
        WSRequest request = ws.url("http://localhost:8094/users");
        return get(request);
    }

    public JsonNode login(Login login) {
        WSRequest request = ws.url("http://localhost:8094/users/login");
        JsonNode object = Json.toJson(login);
        return add(object, request);
    }

    private JsonNode checkNullablePromise(CompletionStage<JsonNode> jsonPromise) {
        JsonNode promise = null;
        try {
            promise = jsonPromise.toCompletableFuture().get();
        } catch (Exception e) {
            Logger.error("Failed parsing json promise.", e);
        }
        return promise;
    }


    public JsonNode get(WSRequest request) {
        CompletionStage<JsonNode> jsonPromise = request.get().thenApply(WSResponse::asJson);
        JsonNode jsonNode = this.checkNullablePromise(jsonPromise);
        return jsonNode;
    }


    public JsonNode add(JsonNode object, WSRequest request) {
        CompletionStage<JsonNode> jsonPromise = request
                .setContentType("application/json")
                .post(object.toString()).thenApply(WSResponse::asJson);
        JsonNode jsonNode = this.checkNullablePromise(jsonPromise);
        return jsonNode;
    }

    public JsonNode edit(JsonNode object, WSRequest request) {
        CompletionStage<JsonNode> jsonPromise = request
                .setContentType("application/json")
                .put(object.toString()).thenApply(WSResponse::asJson);
        JsonNode jsonNode = this.checkNullablePromise(jsonPromise);
        return jsonNode;
    }

    public JsonNode delete(WSRequest request) {
        CompletionStage<JsonNode> jsonPromise = request.delete().thenApply(WSResponse::asJson);
        JsonNode jsonNode = this.checkNullablePromise(jsonPromise);

        return jsonNode;
    }
}
