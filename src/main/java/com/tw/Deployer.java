package com.tw;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.json.JsonObject;

public class Deployer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        JsonObject connectorConfigJson = new JsonObject().put("connector.class", "org.apache.kafka.connect.file.FileStreamSinkConnector")
                .put("tasks.max", "10")
                .put("topics", "test-topic")
                .put("file", "/home/test.sink.txt");
        JsonObject connectorJson = new JsonObject().put("name", "file-sink-connector5").put("config", connectorConfigJson);
        JsonObject config = new JsonObject().put("ip", "192.168.64.12").put("port", 31918).put("path", "/connectors").put("connector", connectorJson);
        JsonObject configForDelete = new JsonObject().put("ip", "192.168.64.12").put("port", 31918).put("path", "/connectors").put("connectorName", "file-sink-connector5");
        JsonObject configForList = new JsonObject().put("ip", "192.168.64.12").put("port", 31918).put("path", "/connectors");

        vertx.deployVerticle(new CreateConnectorCommand(), new DeploymentOptions().setConfig(config));
        vertx.deployVerticle(new CreateUpdateConfigCommand(), new DeploymentOptions().setConfig(config));
        vertx.deployVerticle(new DeleteConnectorCommand(), new DeploymentOptions().setConfig(configForDelete));
        vertx.deployVerticle(new ListConnectorsCommand(), new DeploymentOptions().setConfig(configForList));
    }
}
