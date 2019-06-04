package com.itboye.cardmanage.retrofit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * JsonMapHelper
 * <p>
 * Created by Mr.w on 2019/6/2.
 * <p>
 * 版本      ${version}
 * <p>
 * 修改时间
 * <p>
 * 修改内容
 */

public class JsonMapHelper {
    public static HashMap<String, Object> parseJSONObject(String str) {
        JSONObject jsonobj = null;
        try {
            jsonobj = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray a_name = jsonobj.names();
        HashMap<String, Object> map = new HashMap<>();
        if (a_name != null) {
            int i = 0;
            while (i < a_name.length()) {
                String key;
                try {
                    key = a_name.getString(i);
                    Object obj = jsonobj.get(key);
                    map.put(key, parseUnknowObjectToJson(obj));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
        return map;
    }

    public static ArrayList<Object> parseJSONArray(JSONArray jsonarr) {
        ArrayList<Object> list = new ArrayList<Object>();
        int len = jsonarr.length();
        for (int i = 0; i < len; i++) {
            Object o;
            try {
                o = jsonarr.get(i);
                list.add(parseUnknowObjectToJson(o));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    private static Object parseUnknowObjectToJson(Object o) {
        if (o instanceof JSONObject) {
            return parseJSONObject("" + o);
        } else if (o instanceof JSONArray) {
            return parseJSONArray((JSONArray) o);
        }
        return o;
    }

    //service_type=by_SecurityCode_createAndSend&accepter=15736243109&code_type=1&country_no=86
    public static JSONObject parseJsonToMap(String json) {
        JSONObject jsonObject = new JSONObject();
//        Hashtable<String, String> hashMap = new Hashtable<>();
        String[] arg = json.split("&");
        if (arg != null) {
            for (int i = 0; i < arg.length; i++) {
                String key = arg[i].split("=")[0];
//                if (key.equalsIgnoreCase("service_type")) {
//                    continue;
//                }
                String value = arg[i].split("=")[1];
//                hashMap.put(key, value);
                try {
                    jsonObject.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }
}
