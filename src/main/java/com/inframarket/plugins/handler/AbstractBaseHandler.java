package com.inframarket.plugins.handler;

import com.thoughtworks.go.plugin.api.logging.Logger;

public abstract class AbstractBaseHandler implements Handler {
    protected Logger logger;

    protected AbstractBaseHandler() {
        logger = Logger.getLoggerFor(this.getClass());
    }
}
