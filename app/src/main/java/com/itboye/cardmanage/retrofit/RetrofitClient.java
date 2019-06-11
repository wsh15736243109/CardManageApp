package com.itboye.cardmanage.retrofit;

import android.content.Context;
import android.text.TextUtils;

import android.util.Log;
import com.google.gson.Gson;

import java.io.File;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.itboye.cardmanage.util.DataEncryptionUtil;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.goldze.mvvmhabit.BuildConfig;
import me.goldze.mvvmhabit.http.interceptor.CacheInterceptor;
import me.goldze.mvvmhabit.http.interceptor.logging.Level;
import me.goldze.mvvmhabit.http.interceptor.logging.LoggingInterceptor;
import me.goldze.mvvmhabit.utils.DataSignatureUtil;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.Utils;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.itboye.cardmanage.config.Global.BASEURL;

/**
 * Created by goldze on 2017/5/10.
 * RetrofitClient封装单例类, 实现网络请求
 */
public class RetrofitClient {
    //超时时间
    private static final int DEFAULT_TIMEOUT = 20*1000;
    //缓存时间
    private static final int CACHE_TIMEOUT = 10 * 1024 * 1024;
    //服务端根路径
    public static String baseUrl = BASEURL;

    private static Context mContext = Utils.getContext();

    private static OkHttpClient okHttpClient;
    private static OkHttpClient okHttpClientUpload;
    private static Retrofit retrofit;
    private static Retrofit retrofitUpload;

    private Cache cache = null;
    private File httpCacheDirectory;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient() {
        this(baseUrl, null);
    }

    private RetrofitClient(String url, Map<String, String> map) {

        if (TextUtils.isEmpty(url)) {
            url = baseUrl;
        }

        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(mContext.getCacheDir(), "goldze_cache");
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, CACHE_TIMEOUT);
            }
        } catch (Exception e) {
            KLog.e("Could not create http cache", e);
        }
//        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory();
        okHttpClient = new OkHttpClient.Builder()
//                .cookieJar(new CookieJarImpl(new PersistentCookieStore(mContext)))
//                .cache(cache)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    //获取请求体
                    RequestBody body = request.body();
                    if (body instanceof FormBody) {
                    } else if (body instanceof RequestBody) {
                        Buffer buffer = new Buffer();
                        body.writeTo(buffer);
                        Charset charset = Util.UTF_8;
                        MediaType contentType = body.contentType();
                        if (contentType != null) {
                            charset = contentType.charset(charset);
                        }
                        String content = buffer.readString(charset);
                        long time = System.currentTimeMillis();
                        String client_secret = "df45c46ca6df63e7d5b38bfb7d61b5fc";
                        String client_id = "by04esfI0fYuD5";
                        String serviceVersion = "100";//headers.get("service_version");
                        JSONObject json1 = JsonMapHelper.parseJsonToMap(content);
                        String serviceType = null;
                        try {
                            serviceType = json1.getString("service_type") + "";
                            json1.remove("service_type");
                        } catch (JSONException e) {
                            e.printStackTrace();
//                            serviceType = "by_SecurityCode_createAndSend";
                        }
                        if (serviceType == null) {
                            throw new RuntimeException("缺少 service_type参数");
                        }
                        String json = json1.toString();
                        KLog.d("sign====" + (time + "" + client_secret + "" + serviceType + "" + serviceVersion + "" + json));
                        body = new FormBody.Builder()
                                .add("app_type", "android")
                                .add("app_version", "1.0.0")
                                .add("notify_id", time + "")
                                .add("lang", "zh-cn")
                                .add("service_version", serviceVersion)
                                .add("service_type", serviceType)
                                .add("client_id", client_id)
                                .add("app_request_time", time + "")
                                .add("buss_data", json)
                                .add("code", "123456")
                                .add("sign", DataSignatureUtil.getMD5(time + "" + client_secret + "" + serviceType + "" + serviceVersion + "" + json))
                                .build();
                    }
                    // 若请求体不为Null，重新构建post请求，并传入修改后的参数体
                    if (body != null) {
                        request = request.newBuilder().post(body).build();
                    }
                    return chain.proceed(request);
                })
//                .addInterceptor(new CacheInterceptor(mContext))
                .addInterceptor(new LoggingInterceptor
                        .Builder()//构建者模式
                        .loggable(BuildConfig.DEBUG) //是否开启日志打印
                        .setLevel(Level.BASIC) //打印的等级
                        .log(Platform.INFO) // 打印类型
                        .request("Request") // request的Tag
                        .response("Response")// Response的Tag
                        .addHeader("Content-Type", "application/x-www-form-urlencoded") // 添加打印头, 注意 key 和 value 都不能是中文
                        .build()
                )
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                // 这里你可以根据自己的机型设置同时连接的个数和时间，我这里8个，和每个保持时间为10s
                .build();
        okHttpClientUpload = new OkHttpClient.Builder().addInterceptor(new LoggingInterceptor
                .Builder()//构建者模式
                .loggable(BuildConfig.DEBUG) //是否开启日志打印
                .setLevel(Level.BASIC) //打印的等级
                .log(Platform.INFO) // 打印类型
                .request("Request") // request的Tag
                .response("Response")// Response的Tag
                .addHeader("Content-Type", "application/x-www-form-urlencoded") // 添加打印头, 注意 key 和 value 都不能是中文
                .build())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(8, 15, TimeUnit.SECONDS))
                .build();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
        retrofitUpload = new Retrofit.Builder()
                .client(okHttpClientUpload)
                .addConverterFactory(MyGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();

    }

    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T create(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofit.create(service);
    }
    /**
     * create you ApiService
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     */
    public <T> T createUpload(final Class<T> service) {
        if (service == null) {
            throw new RuntimeException("Api service is null!");
        }
        return retrofitUpload.create(service);
    }
    /**
     * /**
     * execute your customer API
     * For example:
     * MyApiService service =
     * RetrofitClient.getInstance(MainActivity.this).create(MyApiService.class);
     * <p>
     * RetrofitClient.getInstance(MainActivity.this)
     * .execute(service.lgon("name", "password"), subscriber)
     * * @param subscriber
     */

    public static <T> T execute(Observable<T> observable, Observer<T> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

        return null;
    }
}
