package com.inframarket.plugins.executor;

import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.DefaultScmConfigurationView;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class ScmConfigurationExecutor implements Executor {
    private static final Logger LOGGER = Logger.getLoggerFor(ScmConfigurationExecutor.class);
    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) {
        LOGGER.info("Successfully reached ScmConfigurationExecutor.class");
        return PluginUtils.getPluginConfiguration(new DefaultScmConfigurationView());
    }
}
