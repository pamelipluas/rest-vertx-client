package com.tw;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import io.vertx.ext.web.codec.BodyCodec;

import java.util.function.Function;

public class CreateUpdateConfigCommand {
    private HttpRequest<JsonObject> request;
    private Connector connector;

    public CreateUpdateConfigCommand(String ip, Integer port, String path, Vertx vertx, Connector connector) {
        this.request = WebClient.create(vertx)
                .put(port, ip, path + "/" + connector.getName() + "/config")
                .as(BodyCodec.jsonObject())
                .putHeader("Accept", "application/json")
                .putHeader("Content-Type", "application/json")
                .expect(methodsPredicate);
        this.connector = connector;
    }

    Function<HttpResponse<Void>, ResponsePredicateResult> methodsPredicate = resp -> {
        int statusCode = resp.statusCode();
        if (statusCode == 200 || statusCode == 201) {
            return ResponsePredicateResult.success();
        }
        return ResponsePredicateResult.failure("Does not work");
    };

    public void run() {
        this.request.sendJson(this.connector.getConfig(), asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println(asyncResult.result().body());
                System.out.println();
            } else if (asyncResult.failed()) {
                asyncResult.cause().printStackTrace();
            }
        });
    }


}
