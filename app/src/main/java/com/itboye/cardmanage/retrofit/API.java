package com.itboye.cardmanage.retrofit;


import com.itboye.cardmanage.bean.UploadImageBean;
import com.itboye.cardmanage.bean.UserInfoBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
                                                    @Field("service_type") String serviceType);

    /**
     * 图片上传
     *
     * @param parts 图片file
     * @return 链接
     */
    @Multipart
    @POST("/picture/upload")
    Observable<BaseResponse<UploadImageBean>> uploadImage(@Part List<MultipartBody.Part> parts);


    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded //
    @POST("/")
    Observable<BaseResponse<String>> feedback(@Field("user_id") String userId,
                                              @Field("content") String content,
                                              @Field("service_type") String serviceType);

}
