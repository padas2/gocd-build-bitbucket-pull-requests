package com.inframarket.plugins.views;

import java.util.HashMap;
import java.util.Map;

public interface ConfigurationView {
    Map<String, Object> fields();

    String templateName();

    boolean hasConfigurationView();
}
