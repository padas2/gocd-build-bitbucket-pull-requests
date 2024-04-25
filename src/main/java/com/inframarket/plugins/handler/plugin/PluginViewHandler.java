package com.inframarket.plugins.handler.plugin;

import com.inframarket.plugins.handler.AbstractBaseHandler;
import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.DefaultGeneralPluginConfigurationView;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.io.IOException;

public class PluginViewHandler extends AbstractBaseHandler {
    public GoPluginApiResponse Handle(GoPluginApiRequest apiRequest) throws IOException {
        this.logger.info("Successfully reached " + this.getClass());
        return PluginUtils.getPluginView(new DefaultGeneralPluginConfigurationView());
    }
}
