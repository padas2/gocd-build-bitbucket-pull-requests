package com.inframarket.plugins.executor;

import com.thoughtworks.go.plugin.api.logging.Logger;

public abstract class AbstractBaseExecutor implements Executor {
    protected Logger logger;

    protected AbstractBaseExecutor() {
        logger = Logger.getLoggerFor(this.getClass());
    }
}
