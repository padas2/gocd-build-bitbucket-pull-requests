package com.inframarket.plugins.executor;

import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;
import java.util.Map;

public class ExecutorFactory {
    private final Map<String, Executor> executorRegistry;

    private ExecutorFactory(ExecutorFactoryBuilder builder) {
        this.executorRegistry = builder.executorRegistry;
    }

    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) {
        Executor executor = this.executorRegistry.get(apiRequest.requestName());
        return executor.Execute(apiRequest);
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
