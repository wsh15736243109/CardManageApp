package com.itboye.cardmanage.retrofit;


import android.databinding.ObservableField;
import com.itboye.cardmanage.bean.*;

import com.itboye.cardmanage.model.CardManageModel;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.*;

import java.util.ArrayList;
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
                                                          @Field("country_no") String country_no,
                                                          @Field("new_pwd") String new_pwd,
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
    @FormUrlEncoded
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
                                                 @Field("id_front_img_id") String id_front_img_id,
                                                 @Field("id_back_img_id") String id_back_img_id,
                                                 @Field("id_hold_img_id") String id_hold_img_id,
                                                 @Field("bank_img_id") String bank_img_id,
                                                 @Field("expire_date") String validityTime,
                                                 @Field("zipcode") String zipCode,
                                                 @Field("email") String email,
                                                 @Field("address") String address,
                                                 @Field("service_type") String service_type);

    /**
     * 用户更新用户认证接口
     *
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> userUpdateAuth(@Field("user_id") String user_id,
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
                                                    @Field("id_front_img_id") String id_front_img_id,
                                                    @Field("id_back_img_id") String id_back_img_id,
                                                    @Field("id_hold_img_id") String id_hold_img_id,
                                                    @Field("bank_img_id") String bank_img_id,
                                                    @Field("expire_date") String validityTime,
                                                    @Field("zipcode") String zipCode,
                                                    @Field("email") String email,
                                                    @Field("address") String address,
                                                    @Field("service_type") String service_type);

    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded
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

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> feedback(@Field("uid") String uid, @Field("content") String content, @Field("service_type") String serviceType);

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ArrayList<CardManageModel>>> cardList(@Field("card_usage") String card_usage, @Field("page_index") String page_index, @Field("page_size") String page_size, @Field("service_type") String serviceType);

    /**
     * 绑定结算卡
     *
     * @param card_no     卡号
     * @param bank_name   开户行
     * @param mobile      预留手机号
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<CardManageModel>> addSettlementCard(@Field("card_no") String card_no, @Field("bank_name") String bank_name, @Field("mobile") String mobile, @Field("bank_img") String bank_img, @Field("branch_name") String branch_name, @Field("service_type") String serviceType);

    /**
     * 添加支付卡
     *
     * @param card_no        卡号
     * @param bank_name      开户行
     * @param mobile         预留手机号
     * @param cvn2           信用卡背后3位
     * @param expire_date    信用卡过期日期格式(YYMM)年月
     * @param bill_date      账单日 几号1-31
     * @param repayment_date 还款日 1-31
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<CardManageModel>> addPaymentCard(@Field("card_no") String card_no, @Field("bank_name") String bank_name, @Field("mobile") String mobile, @Field("cvn2") String cvn2, @Field("expire_date") String expire_date, @Field("bill_date") String bill_date, @Field("repayment_date") String repayment_date, @Field("service_type") String serviceType);

    /**
     * 解绑卡片
     *
     * @param id
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> unbindCard(@Field("id") String id, @Field("service_type") String serviceType);

    /**
     * 查询认证信息
     *
     * @param userId
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<UserAuthDetailBean>> queryAuthInfo(@Field("user_id") String userId, @Field("service_type") String serviceType);

    /**
     * 支付通道查询
     *
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ArrayList<PayWaybean>>> queryPayWays(@Field("service_type") String serviceType);

    /**
     * 创建自动收款订单
     *
     * @param amount
     * @param note
     * @param pay_card_id
     * @param withdraw_card_id
     * @param pay_channel_id
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> createPaymentOrder(@Field("amount") String amount,
                                                        @Field("note") String note,
                                                        @Field("pay_card_id") String pay_card_id,
                                                        @Field("withdraw_card_id") String withdraw_card_id,
                                                        @Field("pay_channel_id") String pay_channel_id,
                                                        @Field("service_type") String serviceType);

    /**
     * 设置主卡
     *
     * @param id
     * @param card_usage
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<CardManageModel>> setMasterBalance(@Field("id") String id,
                                                               @Field("card_usage") String card_usage,
                                                               @Field("service_type") String serviceType);

    /**
     * 发起收款请求
     *
     * @param id
     * @param card_usage
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ReceiveMoneyAuthPassBean>> sendPayment(@Field("order_code") String id,
                                                                   @Field("code") String card_usage,
                                                                   @Field("service_type") String serviceType);


    /**
     * 代扣协议
     *
     * @param bank_id
     * @param verification_code
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ArrayList>> signWithholding(@Field("bank_id") String bank_id,
                                                        @Field("verification_code") String verification_code,
                                                        @Field("service_type") String serviceType);

    /**
     * 代付协议
     *
     * @param bank_id
     * @param verification_code
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ArrayList>> signRepay(@Field("bank_id") String bank_id,
                                                  @Field("verification_code") String verification_code,
                                                  @Field("service_type") String serviceType);

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> authDetail(@Field("service_type") String serviceType);


    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<NoticeBean>> noticeMessage(@Field("page_index") String page_index,
                                                       @Field("page_size") String page_size,
                                                       @Field("service_type") String serviceType);

    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ArrayList<TranslationBean>>> translationRecord(@Field("month") String month,
                                                                           @Field("service_type") String serviceType);

    /**
     * 添加还款计划
     *
     * @param amount
     * @param days
     * @param pre_store_money
     * @param fee
     * @param preStoreCardIds
     * @param creditCardIds
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> createCbPlan(@Field("money") String amount,
                                                  @Field("days") String days,
                                                  @Field("pre_store_money") String pre_store_money,
                                                  @Field("fee") Double fee,
                                                  @Field("pre_store_card_id") String preStoreCardIds,
                                                  @Field("credit_card_ids") String creditCardIds, @Field("service_type") String serviceType);

    /**
     * 重启还款计划
     *
     * @param id
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<String>> restartCbPlan(@Field("id") String id, @Field("service_type") String serviceType);

    /**
     * 查询还款计划
     *
     * @param page_index
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<ArrayList<CardManageModel>>> queryRepaymentPlan(@Field("page_index") String page_index, @Field("service_type") String serviceType);


    /**
     * 自动还款获取手续费(根据每次还款额度+ 银行卡数量）
     *
     * @param money
     * @param card_count
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<Double>> getRepaymentFee(@Field("money") double money,
                                                     @Field("card_count") int card_count,
                                                     @Field("days") int days, @Field("service_type") String serviceType);


    /**
     * 获取首页相关信息
     * @param position
     * @param page_index
     * @param page_size
     * @param serviceType
     * @return
     */
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<BannerBean>> getBanners(@Field("position") String position,
                                                @Field("page_index") int page_index,
                                                @Field("page_size") int page_size, @Field("service_type") String serviceType);


    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("/")
    Observable<BaseResponse<HomeBean>> getBannersAndNotice(@Field("service_type") String serviceType);
}
