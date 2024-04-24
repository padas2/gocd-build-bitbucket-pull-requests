package com.inframarket.plugins;

import com.inframarket.plugins.executor.ExecutorFactory;
import com.inframarket.plugins.executor.PluginConfigurationExecutor;
import com.inframarket.plugins.utils.PluginUtils;
import com.inframarket.plugins.views.ConfigurationView;
import com.inframarket.plugins.views.DefaultGeneralPluginConfigurationView;
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
                .build();
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest goPluginApiRequest) throws UnhandledRequestTypeException {
        LOGGER.info("Api request type : "  + goPluginApiRequest.requestName());
        switch (goPluginApiRequest.requestName()) {
//            case Constants.GET_PLUGIN_CONFIGURATION:
//                return handlePluginSettingsGetConfiguration();
            case Constants.GET_PLUGIN_CONFIGURATION:
                return this.executorFactory.Execute(goPluginApiRequest);
            case Constants.GET_PLUGIN_VIEW:
                try {
                    return handlePluginSettingsGetView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case Constants.SCM_CONFIGURATION:
                try {
                    return handleScmConfiguration();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case Constants.SCM_VIEW:
                try {
                    return handleScmView();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
        return renderJSON(200, null);
    }

    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return new GoPluginIdentifier("scm", Collections.singletonList("1.0"));
    }

    private GoPluginApiResponse handlePluginSettingsGetConfiguration() {
        return PluginUtils.getPluginConfiguration(new DefaultGeneralPluginConfigurationView());
    }

    private GoPluginApiResponse handlePluginSettingsGetView() throws IOException {
        return getPluginView(new DefaultGeneralPluginConfigurationView());
    }

    private GoPluginApiResponse getPluginView(ConfigurationView view) throws IOException {
        if (view.hasConfigurationView()) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("displayValue", "bitbucket");
            response.put("template", getFileContents(view.templateName()));
            return renderJSON(200, response);
        } else {
            return renderJSON(404, null);
        }
    }

    private GoPluginApiResponse handleScmView() throws IOException {
        return getPluginView(new DefaultScmConfigurationView());
    }

    private GoPluginApiResponse handleScmConfiguration() throws IOException {
        return PluginUtils.getPluginConfiguration(new DefaultScmConfigurationView());
    }

    private String getFileContents(String filePath) throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream(filePath), "UTF-8");
    }
}
