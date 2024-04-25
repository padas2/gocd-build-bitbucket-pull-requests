package com.inframarket.plugins.executor;

import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExecutorFactory {
    private final Map<String, Executor> executorRegistry;

    private ExecutorFactory(ExecutorFactoryBuilder builder) {
        this.executorRegistry = builder.executorRegistry;
    }

    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) throws UnhandledRequestTypeException, Exception {
        Optional<Executor> executor = Optional.ofNullable(this.executorRegistry.get(apiRequest.requestName()));
        if (!executor.isPresent()) {
            throw new UnhandledRequestTypeException(apiRequest.requestName());
        }

        return executor.get().Execute(apiRequest);
    }

    public static class ExecutorFactoryBuilder {
        private final Map<String, Executor> executorRegistry;

        public ExecutorFactoryBuilder() {
            executorRegistry = new HashMap<String, Executor>();
        }

        public ExecutorFactoryBuilder AddExecutor(String executorType, Executor executor) {
            executorRegistry.put(executorType, executor);
            return this;
        }

        public ExecutorFactory build() {
            return new ExecutorFactory(this);
        }
    }
}
