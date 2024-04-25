package com.inframarket.plugins.views;

import java.util.HashMap;
import java.util.Map;

public class DefaultGeneralPluginConfigurationView implements ConfigurationView {
    public String templateName() {
        return "/views/scm.template.html";
    }

    public Map<String, Object> fields() {
        Map<String, Object> response = new HashMap<String, Object>();
        return response;
    }

    public boolean hasConfigurationView() {
        return true;
    }
}
