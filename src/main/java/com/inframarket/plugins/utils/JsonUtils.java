package com.inframarket.plugins.utils;

import com.google.gson.GsonBuilder;

public class JsonUtils {
    public static Object fromJSON(String json) {
        return new GsonBuilder().create().fromJson(json, Object.class);
    }

    public static <T> T fromJSON(String json, Class<T> type) {
        return new GsonBuilder().create().fromJson(json, type);
    }

    public static String toJSON(Object object) {
        return new GsonBuilder().create().toJson(object);
    }
}