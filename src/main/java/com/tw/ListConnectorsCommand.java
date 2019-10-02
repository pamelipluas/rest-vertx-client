package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class ListConnectorsCommand extends AbstractVerticle {
    @Override
    public void start() {
        WebClient.create(vertx)
                .get(config().getInteger("port"), config().getString("ip"), config().getString("path"))
                .as(BodyCodec.jsonArray())
                .putHeader("Accept", "application/json")
                .expect(ResponsePredicate.SC_OK)
                .send(asyncResult -> {
                    if (asyncResult.succeeded()) {
                        System.out.println(asyncResult.result().body());
                        System.out.println();
                    } else if (asyncResult.failed()) {
                        asyncResult.cause().printStackTrace();
                    }
                });
    }
}
