package com.ccsu.bookshop.utils;

import com.alibaba.fastjson.JSONArray;
public class JsonUtil {
    //
    public static String objectToJson(Object data) {
        String json = JSONArray.toJSONString(data);
        return json;
    }
}
