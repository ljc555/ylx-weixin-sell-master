package com.ylx.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by LJH
 * 2018-10-04 01:30
 */
public class JsonUtil {

    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
