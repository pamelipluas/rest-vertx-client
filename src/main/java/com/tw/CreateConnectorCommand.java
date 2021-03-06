package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class CreateConnectorCommand extends AbstractVerticle {
    @Override
    public void start() {
        WebClient.create(vertx)
                .post(config().getInteger("port"), config().getString("ip"), config().getString("path"))
                .as(BodyCodec.jsonObject())
                .putHeader("Accept", "application/json")
                .putHeader("Content-Type", "application/json")
                .expect(ResponsePredicate.SC_CREATED)
                .sendJson(config().getJsonObject("connector"), asyncResult -> {
                    if (asyncResult.succeeded()) {
                        System.out.println(asyncResult.result().body());
                        System.out.println();
                    } else if (asyncResult.failed()) {
                        asyncResult.cause().printStackTrace();
                    }
                });
    }


}
