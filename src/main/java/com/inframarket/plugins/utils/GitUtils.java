package com.inframarket.plugins.utils;

import com.tw.go.plugin.model.GitConfig;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class GitUtils {
    public static GitConfig getGitConfig(Map<String, String> configuration) {
        return new GitConfig(
                configuration.get("url"),
                configuration.get("username"),
                configuration.get("password"),
                StringUtils.trimToNull(configuration.get("defaultBranch")),
                true,
                Boolean.parseBoolean(configuration.get("shallowClone")));
    }
}
