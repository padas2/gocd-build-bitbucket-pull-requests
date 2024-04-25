package com.inframarket.plugins.handler.scm;

import com.inframarket.plugins.handler.AbstractBaseHandler;
import com.inframarket.plugins.utils.GitUtils;
import com.inframarket.plugins.utils.GocdUtils;
import com.inframarket.plugins.utils.UrlUtils;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import com.tw.go.plugin.model.GitConfig;
import com.tw.go.plugin.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.inframarket.plugins.utils.CommonUtils.keyValuePairs;
import static com.inframarket.plugins.utils.JsonUtils.fromJSON;

public class ScmValidationHandler extends AbstractBaseHandler {
    @Override
    public GoPluginApiResponse Handle(GoPluginApiRequest apiRequest) throws Exception {
        this.logger.info("Successfully reached " + this.getClass());
        // Prepare gitConfig object from GoPluginApiRequest
        Map<String, Object> requestBodyMap = (Map<String, Object>) fromJSON(apiRequest.requestBody());
        final Map<String, String> configuration = keyValuePairs(requestBodyMap, "scm-configuration");
        final GitConfig gitConfig = GitUtils.getGitConfig(configuration);
        this.logger.info("Git url : " + gitConfig.getUrl());

        // Perform validations and prepare responseMap
        Map<String, Object> responseMap = new HashMap<String, Object>();
        if (StringUtil.isEmpty(gitConfig.getUrl())) {
            responseMap.put("key", "url");
            responseMap.put("message", "URL is a required field");
        } else if (!UrlUtils.isValidURL(gitConfig.getUrl())) {
            responseMap.put("key", "url");
            responseMap.put("message", "Invalid URL");
        }

        List<Map<String, Object>> responseList = new ArrayList<Map<String, Object>>();
        responseList.add(responseMap);
        return GocdUtils.renderJSON(200, responseList);
    }
}
