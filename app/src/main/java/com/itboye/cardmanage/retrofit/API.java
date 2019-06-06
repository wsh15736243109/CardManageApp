package com.itboye.cardmanage.retrofit;


import com.itboye.cardmanage.bean.UserInfoBean;
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
    Observable<BaseResponse<UserInfoBean>> loginByPwd(@Field("mobile") String phone,
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
    Observable<BaseResponse<UserInfoBean>> register(@Field("mobile") String phone,
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
    Observable<BaseResponse<UserInfoBean>> loginByCode(@Field("mobile") String phone,
                                                 @Field("device_token") String device_token,
                                                 @Field("device_type") String device_type,
                                                 @Field("loginInfo") String loginInfo,
                                                 @Field("code") String code,
                                                 @Field("service_type") String service_type);

    /**
     * 通过手机号码改密码
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> forgetPasswordByCode(@Field("mobile") String phone,
                                                       @Field("device_token") String device_token,
                                                       @Field("device_type") String device_type,
                                                       @Field("loginInfo") String loginInfo,
                                                       @Field("code") String code,
                                                       @Field("service_type") String service_type);
    /**
     * 通过密码改密码
     * @param uid
     * @param old_pwd
     * @param new_pwd
     * @param sid
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> forgetPasswordByPwd(@Field("uid") String uid,
                                                                @Field("old_pwd") String old_pwd,
                                                                @Field("new_pwd") String new_pwd,
                                                                @Field("sid") String sid,
                                                                @Field("service_type") String service_type);
}
