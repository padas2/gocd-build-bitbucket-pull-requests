package com.inframarket.plugins;

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
    private static Logger LOGGER = Logger.getLoggerFor(BitbucketPRBuildPlugin.class);
    private GoApplicationAccessor goApplicationAccessor;

    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor goApplicationAccessor) {
        this.goApplicationAccessor = goApplicationAccessor;
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest goPluginApiRequest) throws UnhandledRequestTypeException {
        LOGGER.info("Api request type : "  + goPluginApiRequest.requestName());
        switch (goPluginApiRequest.requestName()) {
            case "go.plugin-settings.get-configuration" :
                return handlePluginSettingsGetConfiguration(goPluginApiRequest);
            case "go.plugin-settings.get-view" :
                try {
                    return handlePluginSettingsGetView(goPluginApiRequest);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case "scm-configuration":
                try {
                    return handleScmConfiguration(goPluginApiRequest);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case "scm-view":
                try {
                    return handleScmView(goPluginApiRequest);
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

    private GoPluginApiResponse handlePluginSettingsGetConfiguration(GoPluginApiRequest goPluginApiRequest) {
        return getPluginConfiguration(new DefaultGeneralPluginConfigurationView());
    }

    private GoPluginApiResponse getPluginConfiguration(DefaultGeneralPluginConfigurationView view) {
        Map<String, Object> response = view.fields();
        return renderJSON(200, response);
    }

    private GoPluginApiResponse handlePluginSettingsGetView(GoPluginApiRequest goPluginApiRequest) throws IOException {
        return getPluginView(new DefaultGeneralPluginConfigurationView());
    }

    private GoPluginApiResponse getPluginView(DefaultGeneralPluginConfigurationView view) throws IOException {
        if (view.hasConfigurationView()) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("displayValue", "bitbucket");
            response.put("template", getFileContents(view.templateName()));
            return renderJSON(200, response);
        } else {
            return renderJSON(404, null);
        }
    }

    private GoPluginApiResponse handleScmView(GoPluginApiRequest goPluginApiRequest) throws IOException {
        return getPluginView(new DefaultScmConfigurationView());
    }

    private GoPluginApiResponse getPluginView(DefaultScmConfigurationView view) throws IOException {
        if (view.hasConfigurationView()) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("displayValue", "bitbucket");
            response.put("template", getFileContents(view.templateName()));
            return renderJSON(200, response);
        } else {
            return renderJSON(404, null);
        }
    }

    private GoPluginApiResponse handleScmConfiguration(GoPluginApiRequest goPluginApiRequest) throws IOException {
        return getPluginConfiguration(new DefaultScmConfigurationView());
    }

    private GoPluginApiResponse getPluginConfiguration(DefaultScmConfigurationView view) {
        Map<String, Object> response = view.fields();
        return renderJSON(200, response);
    }

    private String getFileContents(String filePath) throws IOException {
        return IOUtils.toString(getClass().getResourceAsStream(filePath), "UTF-8");
    }
}
