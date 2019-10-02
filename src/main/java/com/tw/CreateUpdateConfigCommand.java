package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicateResult;
import io.vertx.ext.web.codec.BodyCodec;

import java.util.function.Function;

public class CreateUpdateConfigCommand extends AbstractVerticle {
    private HttpRequest<JsonObject> request;

    Function<HttpResponse<Void>, ResponsePredicateResult> methodsPredicate = resp -> {
        int statusCode = resp.statusCode();
        if (statusCode == 200 || statusCode == 201) {
            return ResponsePredicateResult.success();
        }
        return ResponsePredicateResult.failure("Does not work");
    };

    @Override
    public void start() {
        WebClient.create(vertx)
                .put(config().getInteger("port"), config().getString("ip"), config().getString("path") + "/"
                        + config().getJsonObject("connector").getString("name") + "/config")
                .as(BodyCodec.jsonObject())
                .putHeader("Accept", "application/json")
                .putHeader("Content-Type", "application/json")
                .expect(methodsPredicate)
                .sendJson(config().getJsonObject("connector").getJsonObject("config"), asyncResult -> {
                    if (asyncResult.succeeded()) {
                        System.out.println(asyncResult.result().body());
                        System.out.println();
                    } else if (asyncResult.failed()) {
                        asyncResult.cause().printStackTrace();
                    }
                });
    }


}
