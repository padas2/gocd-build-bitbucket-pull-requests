package com.inframarket.plugins;

import com.inframarket.plugins.executor.*;
import com.inframarket.plugins.views.ConfigurationView;
import com.inframarket.plugins.views.DefaultScmConfigurationView;
import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.GoPlugin;
import com.thoughtworks.go.plugin.api.GoPluginIdentifier;
import com.thoughtworks.go.plugin.api.annotation.Extension;
import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.inframarket.plugins.utils.GocdUtils.renderJSON;

@Extension
public class BitbucketPRBuildPlugin implements GoPlugin {
    private ExecutorFactory executorFactory;

    private static final Logger LOGGER = Logger.getLoggerFor(BitbucketPRBuildPlugin.class);
    private GoApplicationAccessor goApplicationAccessor;

    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor goApplicationAccessor) {
        this.goApplicationAccessor = goApplicationAccessor;
        this.executorFactory = new ExecutorFactory
                .ExecutorFactoryBuilder()
                .AddExecutor(Constants.GET_PLUGIN_CONFIGURATION, new PluginConfigurationExecutor())
                .AddExecutor(Constants.GET_SCM_CONFIGURATION, new ScmConfigurationExecutor())
                .AddExecutor(Constants.GET_PLUGIN_VIEW, new PluginViewExecutor())
                .AddExecutor(Constants.GET_SCM_VIEW, new ScmViewExecutor())
                .AddExecutor(Constants.VALIDATE_PLUGIN_CONFIGURATION, new PluginValidationExecutor())
                .build();
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest goPluginApiRequest) throws UnhandledRequestTypeException {
        LOGGER.info("Api request type : "  + goPluginApiRequest.requestName());
        try {
            return this.executorFactory.Execute(goPluginApiRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return new GoPluginIdentifier("scm", Collections.singletonList("1.0"));
    }
}
