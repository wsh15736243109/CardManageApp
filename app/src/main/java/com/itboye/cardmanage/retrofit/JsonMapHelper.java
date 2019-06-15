package com.itboye.cardmanage.retrofit;

import me.goldze.mvvmhabit.utils.KLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.*;

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
        TreeMap<String, String> hashMap = new TreeMap<>();
        String[] arg = json.split("&");
        if (arg != null) {
            for (int i = 0; i < arg.length; i++) {
                String[] va = arg[i].split("=");
                String key = va[0];
                KLog.v("Okhttp_R", "签名key====" + key);
                String value = "";
                if (va.length >= 2) {
                    value = (va[1]);
                    value = URLDecoder.decode(value);
                }
                KLog.v("OkHttp_R", "签名value====" + value);
                hashMap.put(key, value);
                try {
                    jsonObject.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return new JSONObject(hashMap);
    }

    public static String parseJsonToMap2(JSONObject jsonObject) {
        TreeMap<String, String> hashMap = new TreeMap<>();
        Iterator<String> iterator = jsonObject.keys();
        while (iterator.hasNext()) {
            String key = iterator.next();
            try {
                String value = URLEncoder.encode(jsonObject.get(key) + "");
//                String value = stringToUnicode(jsonObject.get(key) + "");
//                String value = (jsonObject.get(key) + "");
                hashMap.put(key, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        String json = new JSONObject(hashMap).toString().replace("\\\\", "\\");
        return json;
    }

    //String-->UniCode
    public static String stringToUnicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            //"\\u只是代号，请根据具体所需添加相应的符号"
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }
}
