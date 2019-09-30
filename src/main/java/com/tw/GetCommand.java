package com.tw;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class GetCommand {
    private HttpRequest<JsonArray> request;

    public GetCommand(String ip, Integer port, String path, Vertx vertx) {
        this.request = WebClient.create(vertx)
                .get(port, ip, path)
                .as(BodyCodec.jsonArray())
                .putHeader("Accept", "application/json")
                .expect(ResponsePredicate.SC_OK);
    }

    public void run () {
        this.request.send(asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println(asyncResult.result().body()); // (7)
                System.out.println();
            } else if(asyncResult.failed()) {
                asyncResult.cause().printStackTrace();
            }
        });
    }


}
