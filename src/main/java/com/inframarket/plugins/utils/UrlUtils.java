package com.inframarket.plugins.utils;

import in.ashwanthkumar.utils.lang.StringUtils;

/*
    Pure duplicate class from original repo.
    Needs to be cleaned up.
 */
public class UrlUtils {
    public static boolean isValidURL(String url) {
        if (StringUtils.isEmpty(url))
            return false;
       // this code needs to be fixed... will ignore it for now and work on it
        return isValidHTTPUrl(url) || isValidSSHUrl(url);
    }

    public static boolean isValidHTTPUrl(String url) {
        return true;
    }

    public static boolean isValidSSHUrl(String url) {
        return url.contains("@") && url.replaceAll("//", "/").split("/").length == 2;
    }
}

