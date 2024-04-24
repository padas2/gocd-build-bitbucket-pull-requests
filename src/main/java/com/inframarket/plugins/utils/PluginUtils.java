package com.inframarket.plugins.utils;

import com.inframarket.plugins.views.ConfigurationView;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import static com.inframarket.plugins.utils.GocdUtils.renderJSON;
import java.util.Map;

public class PluginUtils {
    public static GoPluginApiResponse getPluginConfiguration(ConfigurationView view) {
        Map<String, Object> response = view.fields();
        return renderJSON(200, response);
    }
}
