package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.predicate.ResponsePredicate;
import io.vertx.ext.web.codec.BodyCodec;

public class CreateConnectorCommand extends AbstractVerticle {
    @Override
    public void start () {
        HttpRequest<JsonObject> request = WebClient.create(vertx)
                .post(31918, "192.168.64.12", "/connectors")
                .as(BodyCodec.jsonObject())
                .putHeader("Accept", "application/json")
                .putHeader("Content-Type", "application/json")
                .expect(ResponsePredicate.SC_CREATED);
        Connector connector = new Connector("file-sink-connector3");
        connector.addConfigItem("connector.class", "org.apache.kafka.connect.file.FileStreamSinkConnector");
        connector.addConfigItem("tasks.max", "10");
        connector.addConfigItem("topics", "test-topic");
        connector.addConfigItem("file", "/home/test.sink.txt");
        request.sendJson(connector, asyncResult -> {
            if (asyncResult.succeeded()) {
                System.out.println(asyncResult.result().body());
                System.out.println();
            } else if(asyncResult.failed()) {
                asyncResult.cause().printStackTrace();
            }
        });
    }


}
