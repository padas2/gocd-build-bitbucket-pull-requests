package com.inframarket.plugins;

import java.util.HashMap;
import java.util.Map;

public class DefaultGeneralPluginConfigurationView {
    public String templateName() {
        return "/views/scm.template.html";
    }

    public Map<String, Object> fields() {
        Map<String, Object> response = new HashMap<String, Object>();
        return response;
    }

    public boolean hasConfigurationView() {
        return false;
    }
}
