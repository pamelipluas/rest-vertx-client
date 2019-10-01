package com.tw;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;

public class DeleteConnectorCommand {
    private HttpRequest<Buffer> request;

    public DeleteConnectorCommand(String ip, Integer port, String path, Vertx vertx, String connectorName) {
        this.request = WebClient.create(vertx)
                .delete(port, ip, path + "/" + connectorName)
                .expect(ResponsePredicate.SC_NO_CONTENT);
    }

    public void run() {
        this.request.send(asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println(asyncResult.result().statusCode());
                System.out.println();
            } else if (asyncResult.failed()) {
                asyncResult.cause().printStackTrace();
            }
        });
    }


}
