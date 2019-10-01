package com.tw;

import io.vertx.core.Vertx;


public class App {
//    @Override
//    public void start() {
//        Connector connector = new Connector("file-sink-connector3");
//        connector.addConfigItem("connector.class", "org.apache.kafka.connect.file.FileStreamSinkConnector");
//        connector.addConfigItem("tasks.max", "10");
//        connector.addConfigItem("topics", "test-topic");
//        connector.addConfigItem("file", "/home/test.sink.txt");
//
//        new CreateConnectorCommand("192.168.64.12", 31918, "/connectors", vertx, connector).run();
//        new ListConnectorsCommand("192.168.64.12", 31918, "/connectors", vertx).run();
//        new CreateUpdateConfigCommand ("192.168.64.12", 31918, "/connectors", vertx, connector).run();
//        new DeleteConnectorCommand ("192.168.64.12", 31918, "/connectors", vertx, "file-sink-connector2").run();
//    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Deployer());
    }
}
