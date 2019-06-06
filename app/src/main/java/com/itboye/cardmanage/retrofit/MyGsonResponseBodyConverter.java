package com.itboye.cardmanage.retrofit;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

public class MyGsonResponseBodyConverter implements Converter {
    private Gson gson;
    private Type type;

    public MyGsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }


    @Override
    public Object convert(Object value) throws IOException {
        ResponseBody responseBody = (ResponseBody) value;
        String response = responseBody.string();
        try {
            BaseResponse baseBean = gson.fromJson(response, BaseResponse.class);
            if (baseBean.getCode() != 0) {
                throw new DataResultException(baseBean.getMsg(), baseBean.getCode());
            }
            return gson.fromJson((response), type);
        } finally {
            responseBody.close();
        }
    }
}
