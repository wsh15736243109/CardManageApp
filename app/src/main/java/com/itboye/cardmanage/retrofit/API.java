package com.itboye.cardmanage.retrofit;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {
    /**
     * @param phone
     * @param code_type 注册: 1
     *                  更新密码:2
     *                  绑定手机号,之前未绑定过:3
     *                  更换手机号:4;
     *                  登录:5;
     *                  找回密码:6;
     *                  验证是否本人操作:7;
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> getSMSCode(@Field("accepter") String phone, @Field("code_type") String code_type,
                                                @Field("country_no") String country_no, @Field("service_type") String service_type);

    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> loginByPwd(@Field("mobile") String phone,
                                                @Field("device_token") String device_token,
                                                @Field("device_type") String device_type,
                                                @Field("loginInfo") String loginInfo,
                                                @Field("password") String password,
                                                @Field("service_type") String service_type);


    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> register(@Field("mobile") String phone,
                                              @Field("password") String password,
                                              @Field("code") String code,
                                              @Field("country_no") String country_no,
                                              @Field("idcode") String idcode,
                                              @Field("service_type") String service_type);


    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> loginByCode(@Field("mobile") String phone,
                                                 @Field("device_token") String device_token,
                                                 @Field("device_type") String device_type,
                                                 @Field("loginInfo") String loginInfo,
                                                 @Field("code") String code,
                                                 @Field("service_type") String service_type);
}
