package me.goldze.mvvmhabit.http.interceptor;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Set;

import me.goldze.mvvmhabit.utils.DataSignatureUtil;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by goldze on 2017/5/10.
 */
public class BaseInterceptor implements Interceptor {
    private Map<String, String> headers;

    public BaseInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        long time = System.currentTimeMillis();
        String client_secret = "df45c46ca6df63e7d5b38bfb7d61b5fc";
        String serviceType = "by_SecurityCode_createAndSend";//headers.get("service_type");
        String serviceVersion = "100";//headers.get("service_version");
        builder.addHeader("app_type", "android");
        builder.addHeader("app_version", "1.0.0");
        builder.addHeader("notify_id", time + "");
        builder.addHeader("app_request_time", time + "");
        builder.addHeader("lang", "zh-cn");

        builder.addHeader("service_version", serviceVersion);
        builder.addHeader("client_id", "by04esfI0fYuD5");
        builder.addHeader("app_request_time", time + "");
        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
            String json = new Gson().toJson(headers);
            try {
                builder.addHeader("sign", DataSignatureUtil.getMD5(time + client_secret + serviceType + serviceVersion + json));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                ToastUtils.showShort("decode_error==" + e.getMessage());
            }
            builder.addHeader("buss_data", json);
        }
        //请求信息
        return chain.proceed(builder.build());
    }
}