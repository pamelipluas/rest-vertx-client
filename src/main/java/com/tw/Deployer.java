package com.tw;

import io.vertx.core.AbstractVerticle;

public class Deployer extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.deployVerticle("com.tw.EmptyVerticle");
    }
}
