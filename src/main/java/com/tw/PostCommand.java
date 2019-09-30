package com.tw;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class PostCommand {
    private HttpRequest<JsonObject> request;
    private Connector connector;

    public PostCommand(String ip, Integer port, String path, Vertx vertx, Connector connector) {
        this.request = WebClient.create(vertx)
                .post(port, ip, path)
                .as(BodyCodec.jsonObject())
                .putHeader("Accept", "application/json")
                .putHeader("Content-Type", "application/json")
                .expect(ResponsePredicate.SC_CREATED);
        this.connector = connector;
    }

    public void run () {
        this.request.sendJson(this.connector, asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println(asyncResult.result().body()); // (7)
                System.out.println();
            } else if(asyncResult.failed()) {
                asyncResult.cause().printStackTrace();
            }
        });
    }


}
