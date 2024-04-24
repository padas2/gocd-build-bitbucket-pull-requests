package com.inframarket.plugins.executor;

import com.inframarket.plugins.BitbucketPRBuildPlugin;
import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.DefaultGeneralPluginConfigurationView;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class PluginConfigurationExecutor implements Executor {
    private static final Logger LOGGER = Logger.getLoggerFor(PluginConfigurationExecutor.class);
    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) {
        LOGGER.info("Successfully reached PluginConfigurationExecutor.class");
        return PluginUtils.getPluginConfiguration(new DefaultGeneralPluginConfigurationView());
    }
}
