package com.itboye.cardmanage.retrofit;


import com.itboye.cardmanage.bean.UploadImageBean;
import com.itboye.cardmanage.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.List;

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
    @POST("/")
    Observable<BaseResponse<String>> getSMSCode(@Query("accepter") String phone, @Query("code_type") String code_type,
                                                @Query("country_no") String country_no, @Query("service_type") String service_type);

    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    Observable<BaseResponse<UserInfoBean>> loginByPwd(@Query("mobile") String phone,
                                                      @Query("device_token") String device_token,
                                                      @Query("device_type") String device_type,
                                                      @Query("loginInfo") String loginInfo,
                                                      @Query("password") String password,
                                                      @Query("service_type") String service_type);


    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    Observable<BaseResponse<UserInfoBean>> register(@Query("mobile") String phone,
                                                    @Query("password") String password,
                                                    @Query("code") String code,
                                                    @Query("country_no") String country_no,
                                                    @Query("idcode") String idcode,
                                                    @Query("service_type") String service_type);


    /**
     * @param phone
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @POST("/")
    Observable<BaseResponse<UserInfoBean>> loginByCode(@Query("mobile") String phone,
                                                       @Query("device_token") String device_token,
                                                       @Query("device_type") String device_type,
                                                       @Query("loginInfo") String loginInfo,
                                                       @Query("code") String code,
                                                       @Query("service_type") String service_type);

    /**
     * 通过手机号码改密码
     *
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
     *
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

    /**
     * 用户添加认证接口
     *
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded //by_UserIdCard_createAuthInfo
    @POST("/")
    Observable<BaseResponse<String>> userAddAuth(@Field("user_id") String user_id,
                                                 @Field("mobile") String mobile,
                                                 @Field("name") String name,
                                                 @Field("id_no") String id_no,
                                                 @Field("card_no") String card_no,
                                                 @Field("opening_bank") String opening_bank,
                                                 @Field("branch_bank") String branch_bank,
                                                 @Field("id_front_img") String id_front_img,
                                                 @Field("id_back_img") String id_back_img,
                                                 @Field("id_hold_img") String id_hold_img,
                                                 @Field("bank_img") String bank_img,
                                                 @Field("branch_no") String branch_no,
                                                 @Field("service_type") String service_type);

    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded //by_UserIdCard_createAuthInfo
    @POST("/")
    Observable<BaseResponse<String>> updateUserInfo(@Field("nickname") String nickname,
                                                    @Query("service_type") String serviceType);

    @POST("/")
    Observable<BaseResponse<String>> updateUserInfo2(@Query(value = "nickname") String nickname,
                                                     @Query("service_type") String serviceType);

    /**
     * 图片上传
     *
     * @param parts 图片file
     * @return 链接
     */
    @Multipart
    @POST("/picture/upload")
    Observable<BaseResponse<UploadImageBean>> uploadImage(@Part List<MultipartBody.Part> parts);

}
