package com.inframarket.plugins.handler;

import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.DefaultGeneralPluginConfigurationView;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

public class PluginConfigurationHandler extends AbstractBaseHandler {
    public GoPluginApiResponse Execute(GoPluginApiRequest apiRequest) {
        this.logger.info("Successfully reached " + this.getClass());
        return PluginUtils.getPluginConfiguration(new DefaultGeneralPluginConfigurationView());
    }
}
