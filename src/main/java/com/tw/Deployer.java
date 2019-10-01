package com.tw;

import io.vertx.core.AbstractVerticle;

public class Deployer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        Connector connector = new Connector("file-sink-connector3");
        connector.addConfigItem("connector.class", "org.apache.kafka.connect.file.FileStreamSinkConnector");
        connector.addConfigItem("tasks.max", "10");
        connector.addConfigItem("topics", "test-topic");
        connector.addConfigItem("file", "/home/test.sink.txt");
        vertx.deployVerticle("com.tw.EmptyVerticle");
        vertx.deployVerticle(new CreateConnectorCommand());
    }
}
