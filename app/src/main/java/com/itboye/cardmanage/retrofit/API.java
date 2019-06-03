package com.itboye.cardmanage.retrofit;


import io.reactivex.Observable;
import me.goldze.mvvmhabit.http.BaseResponse;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    String HOST = "api.361fit.cn";
    String BASE_AUTH = "http://" + HOST + "/";
    String COMMON_URL = "";


    String BASEURL = BASE_AUTH + COMMON_URL;

    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<Object>> getSMSCode(@Field("accepter") String phone, @Field("code_type") String code_type, @Field("country_no") String country_no, @Field("service_type") String service_type);

    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/t")
    Observable<BaseResponse<Object>> loginByPwd(@Field("service_type") String service_type, @Field("mobile") String phone, @Field("device_token") String device_token,
                                                @Field("device_type") String device_type, @Field("loginInfo") String loginInfo, @Field("password") String password);


}
