package com.inframarket.plugins.utils;

import com.inframarket.plugins.views.ConfigurationView;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import static com.inframarket.plugins.utils.GocdUtils.renderJSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PluginUtils {
    public static GoPluginApiResponse getPluginConfiguration(ConfigurationView view) {
        Map<String, Object> response = view.fields();
        return renderJSON(200, response);
    }

    public static GoPluginApiResponse getPluginView(ConfigurationView view) throws IOException {
        if (view.hasConfigurationView()) {
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("displayValue", "bitbucket");
            response.put("template", FileUtils.getFileContents(view.templateName()));
            return renderJSON(200, response);
        } else {
            return renderJSON(404, null);
        }
    }
}
