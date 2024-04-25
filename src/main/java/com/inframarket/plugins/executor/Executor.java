package com.inframarket.plugins.executor;

import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public interface Executor {
    GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) throws Exception;
}
