package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;


public class App extends AbstractVerticle {
    @Override
    public void start() {
        Connector connector = new Connector("file-sink-connector2");
        connector.addConfigItem("connector.class", "org.apache.kafka.connect.file.FileStreamSinkConnector");
        connector.addConfigItem("tasks.max", "10");
        connector.addConfigItem("topics", "test-topic");
        connector.addConfigItem("file", "/home/test.sink.txt");

        new PostCommand("192.168.64.12", 31918, "/connectors", vertx, connector).run();
        new GetCommand ("192.168.64.12", 31918, "/connectors", vertx).run();
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new App());
    }
}
