package com.inframarket.plugins.handler;

import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RequestHandler {
    private final Map<String, Handler> handlerRegistry;

    private RequestHandler(Builder builder) {
        this.handlerRegistry = builder.handlerRegistry;
    }

    public GoPluginApiResponse Handle(GoPluginApiRequest apiRequest) throws UnhandledRequestTypeException, Exception {
        Optional<Handler> executor = Optional.ofNullable(this.handlerRegistry.get(apiRequest.requestName()));
        if (!executor.isPresent()) {
            throw new UnhandledRequestTypeException(apiRequest.requestName());
        }

        return executor.get().Execute(apiRequest);
    }

    public static class Builder {
        private final Map<String, Handler> handlerRegistry;

        public Builder() {
            handlerRegistry = new HashMap<String, Handler>();
        }

        public Builder AddHandler(String executorType, Handler executor) {
            handlerRegistry.put(executorType, executor);
            return this;
        }

        public RequestHandler build() {
            return new RequestHandler(this);
        }
    }
}
