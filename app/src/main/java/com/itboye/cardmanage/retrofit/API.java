package com.itboye.cardmanage.retrofit;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> getSMSCode(@Field("accepter") String phone, @Field("code_type") String code_type, @Field("country_no") String country_no, @Field("service_type") String service_type);

    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/t")
    Observable<BaseResponse<String>> loginByPwd(@Field("service_type") String service_type, @Field("mobile") String phone, @Field("device_token") String device_token,
                                                @Field("device_type") String device_type, @Field("loginInfo") String loginInfo, @Field("password") String password);


}
