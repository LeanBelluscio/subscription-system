package com.adidas.subscription.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author lbelluscio
 */
public class JsonUtil {
    
     /**
     * Return a Json string
     * @param obj
     * @return
     */
    public static String stringify(Object obj){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        return gson.toJson(obj);
    }
}
