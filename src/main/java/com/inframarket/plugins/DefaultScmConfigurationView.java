package com.inframarket.plugins;

import java.util.HashMap;
import java.util.Map;

import com.inframarket.plugins.utils.BranchFilter;
import com.inframarket.plugins.utils.FieldFactory;

public class DefaultScmConfigurationView {
    public String templateName() {
        return "/views/scm.template.html";
    }

    public Map<String, Object> fields() {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("url", FieldFactory.createForScm("URL", null, true, true, false, "0"));
        response.put("username", FieldFactory.createForScm("Username", null, false, false, false, "1"));
        response.put("password", FieldFactory.createForScm("Password", null, false, false, true, "2"));
        response.put("defaultBranch", FieldFactory.createForScm("Default Branch", "master", false, false, false, "3"));
        response.put("shallowClone", FieldFactory.createForScm("Default Clone Behavior", "false", false, false, false, "4"));
        return response;
    }

    public BranchFilter getBranchFilter(Map<String, String> configuration) {
        return new BranchFilter();
    }

    public boolean hasConfigurationView() {
        return true;
    }
}
