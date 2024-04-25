package com.inframarket.plugins.handler;

import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public interface Handler {
    GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) throws Exception;
}
