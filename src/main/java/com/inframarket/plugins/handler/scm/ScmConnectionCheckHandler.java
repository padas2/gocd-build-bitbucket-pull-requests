package com.inframarket.plugins.handler.scm;

import com.inframarket.plugins.handler.AbstractBaseHandler;
import com.inframarket.plugins.utils.GitUtils;
import com.inframarket.plugins.utils.GocdUtils;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import com.tw.go.plugin.HelperFactory;
import com.tw.go.plugin.model.GitConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.inframarket.plugins.utils.CommonUtils.keyValuePairs;
import static com.inframarket.plugins.utils.JsonUtils.fromJSON;

public class ScmConnectionCheckHandler extends AbstractBaseHandler {
    @Override
    public GoPluginApiResponse Handle(GoPluginApiRequest apiRequest) throws Exception {
        this.logger.info("Successfully reached " + this.getClass());
        // Prepare gitConfig object from GoPluginApiRequest
        Map<String, Object> requestBodyMap = (Map<String, Object>) fromJSON(apiRequest.requestBody());
        final Map<String, String> configuration = keyValuePairs(requestBodyMap, "scm-configuration");
        final GitConfig gitConfig = GitUtils.getGitConfig(configuration);
        this.logger.info("Git url : " + gitConfig.getUrl());

        Map<String, Object> responseMap = new HashMap<>();
        List<String> messages = new ArrayList<>();
        try {
            HelperFactory.gitCmd(gitConfig, null).checkConnection();
        } catch (Exception e) {
            responseMap.put("status", "failure");
            messages.add(e.getMessage());
            responseMap.put("messages", messages);
            return GocdUtils.renderJSON(200, responseMap);
        }

        responseMap.put("status", "success");
        return GocdUtils.renderJSON(200, responseMap);
    }
}
