package com.inframarket.plugins;

import com.inframarket.plugins.handler.*;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.GoPlugin;
import com.thoughtworks.go.plugin.api.GoPluginIdentifier;
import com.thoughtworks.go.plugin.api.annotation.Extension;
import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.Collections;

@Extension
public class BitbucketPRBuildPlugin implements GoPlugin {
    private RequestHandler requestHandler;

    private static final Logger LOGGER = Logger.getLoggerFor(BitbucketPRBuildPlugin.class);
    private GoApplicationAccessor goApplicationAccessor;

    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor goApplicationAccessor) {
        this.goApplicationAccessor = goApplicationAccessor;
        this.requestHandler = new RequestHandler.Builder()
                .AddHandler(Constants.GET_PLUGIN_CONFIGURATION, new PluginConfigurationHandler())
                .AddHandler(Constants.GET_SCM_CONFIGURATION, new ScmConfigurationHandler())
                .AddHandler(Constants.GET_PLUGIN_VIEW, new PluginViewHandler())
                .AddHandler(Constants.GET_SCM_VIEW, new ScmViewHandler())
                .AddHandler(Constants.VALIDATE_PLUGIN_CONFIGURATION, new PluginValidationHandler())
                .AddHandler(Constants.VALIDATE_SCM_CONFIGURATION, new ScmValidationHandler())
                .build();
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest goPluginApiRequest) throws UnhandledRequestTypeException {
        LOGGER.info("Api request type : "  + goPluginApiRequest.requestName());
        try {
            return this.requestHandler.Handle(goPluginApiRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return new GoPluginIdentifier("scm", Collections.singletonList("1.0"));
    }
}
