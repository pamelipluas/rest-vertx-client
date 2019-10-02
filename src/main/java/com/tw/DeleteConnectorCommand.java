package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;

public class DeleteConnectorCommand extends AbstractVerticle {
    @Override
    public void start() {
        WebClient.create(vertx)
                .delete(config().getInteger("port"), config().getString("ip"), config().getString("path") + "/"
                        + config().getString("connectorName"))
                .expect(ResponsePredicate.SC_NO_CONTENT)
                .send(asyncResult -> {
                    if (asyncResult.succeeded()) {
                        System.out.println(asyncResult.result().statusCode());
                        System.out.println();
                    } else if (asyncResult.failed()) {
                        asyncResult.cause().printStackTrace();
                    }
                });
    }


}
