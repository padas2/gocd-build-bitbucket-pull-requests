package com.inframarket.plugins.executor;

import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.DefaultGeneralPluginConfigurationView;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.io.IOException;

public class PluginValidationExecutor extends AbstractBaseExecutor {
    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) throws IOException {
        this.logger.info("Successfully reached " + this.getClass());
        return PluginUtils.getPluginView(new DefaultGeneralPluginConfigurationView());
    }
}