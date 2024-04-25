package com.inframarket.plugins.handler.plugin;

import com.inframarket.plugins.handler.AbstractBaseHandler;
import com.inframarket.plugins.utils.GocdUtils;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.io.IOException;
import java.util.Collections;

public class PluginValidationHandler extends AbstractBaseHandler {
    public GoPluginApiResponse Handle(GoPluginApiRequest apiRequest) throws IOException {
        this.logger.info("Successfully reached " + this.getClass());
        return GocdUtils.renderJSON(200, Collections.EMPTY_LIST);
    }
}
