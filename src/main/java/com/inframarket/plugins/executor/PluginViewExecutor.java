package com.inframarket.plugins.executor;

import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.DefaultGeneralPluginConfigurationView;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.io.IOException;

public class PluginViewExecutor implements Executor {
    private static final Logger LOGGER = Logger.getLoggerFor(PluginViewExecutor.class);
    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) throws IOException {
        LOGGER.info("Successfully reached PluginViewExecutor.class");
        return PluginUtils.getPluginView(new DefaultGeneralPluginConfigurationView());
    }
}
