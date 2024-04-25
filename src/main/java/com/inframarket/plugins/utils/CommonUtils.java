package com.inframarket.plugins.utils;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {
    public static Map<String, String> keyValuePairs(Map<String, Object> requestBodyMap, String mainKey) {
        Map<String, String> keyValuePairs = new HashMap<String, String>();
        Map<String, Object> fieldsMap = (Map<String, Object>) requestBodyMap.get(mainKey);
        for (String field : fieldsMap.keySet()) {
            Map<String, Object> fieldProperties = (Map<String, Object>) fieldsMap.get(field);
            String value = (String) fieldProperties.get("value");
            keyValuePairs.put(field, value);
        }
        return keyValuePairs;
    }
}
