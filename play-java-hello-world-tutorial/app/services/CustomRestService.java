package services;
import java.util.concurrent.CompletionStage;
import javax.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import play.Logger;
import play.libs.ws.WSClient;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;
public class CustomRestService {

    @Inject
    WSClient ws;

    public JsonNode getExpenses() {
        WSRequest request = ws.url("http://localhost:8093/expenses");

        CompletionStage<JsonNode> jsonPromise = request.get().thenApply(WSResponse::asJson);
        JsonNode jsonNode = this.checkNullablePromise(jsonPromise);

        return jsonNode;
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

}
