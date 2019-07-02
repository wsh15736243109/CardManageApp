package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.bean.UploadImageBean;
import com.itboye.cardmanage.bean.UserAuthDetailBean;
import com.itboye.cardmanage.retrofit.*;
import com.itboye.cardmanage.util.UserUtil;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.itboye.cardmanage.config.Global.IMAGEURL;
import static com.itboye.cardmanage.ui.mine.AuthenticationModel.Status.*;
import static com.itboye.cardmanage.util.ImageCompress.compress;

public class AuthenticationModel extends BaseViewModel {

    public ObservableField<String> bankReservePhone = new ObservableField<>("13858066033");//银行预留手机
    public ObservableField<String> branchBankName = new ObservableField<>("中国工商银行股份有限公司杭州南苑支行");//支行名称
    public ObservableField<String> bankName = new ObservableField<>("中国工商银行");//银行名称
    public ObservableField<String> bankNumberAgain = new ObservableField<>("6222081202007602202");//再次银行卡账号
    public ObservableField<String> bankNumber = new ObservableField<>("6222081202007602202");//银行卡号

    public ObservableField<String> realName = new ObservableField<>("周波");//姓名
    public ObservableField<String> idnumber = new ObservableField<>("330327198407040039");//身份证号
    public ObservableField<String> addr = new ObservableField<>("浙江省杭州市下沙智慧谷");//地址
    public ObservableField<String> email = new ObservableField<>("1147806268@qq.com");//邮箱
    public ObservableField<String> zipCode = new ObservableField<>("057111");//邮编
    public ObservableField<String> validityTime = new ObservableField<>("20211001");//证件有效期

    public ObservableField<String> labelAuthStatus = new ObservableField<>("");//认证状态文字
    public ObservableField<Drawable> iconAuthStatus = new ObservableField<>();//认证状态图标

    public ObservableField<String> label = new ObservableField<>("");//提示语
    public ObservableField<String> buttonLabel = new ObservableField<>("");//按钮文字

    public ObservableField<Integer> photoIdentity = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> authCard = new ObservableField<>(View.GONE);

    public ObservableField<Integer> photo1 = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> photo3 = new ObservableField<>(View.GONE);
    public ObservableField<Integer> photo4 = new ObservableField<>(View.GONE);

    public ObservableField<Integer> bodyVisible = new ObservableField<>(View.GONE);
    public ObservableField<Integer> labelAuthStatusVisible = new ObservableField<>(View.GONE);

    public ObservableField<Drawable> status1 = new ObservableField<>();
    public ObservableField<Drawable> status2 = new ObservableField<>();
    public ObservableField<Drawable> status3 = new ObservableField<>();


    public Status status = PHOTO_IDENTITY;
    private String id_front_img = "";//身份证正面照
    private String id_back_img = "";//身份证反面照
    private String id_hold_img = "";//手持身份证
    private String bank_img = "";//银行卡拍照
    ArrayList<BranchBankBean> branchBankBeanArrayList = new ArrayList<>();
    public String branchNo = "";


    private String id_front_img_id = "20190603-1502-7a525013-44af-46cd-93ea-5e0dae61596c";//身份证正面照id
    private String id_back_img_id = "20190603-1502-7a525013-44af-46cd-93ea-5e0dae61596c";//身份证反面照id
    private String id_hold_img_id = "20190603-1502-7a525013-44af-46cd-93ea-5e0dae61596c";//手持身份证id
    private String bank_img_id = "20190603-1502-7a525013-44af-46cd-93ea-5e0dae61596c";//银行卡拍照id
    public UserAuthDetailBean userAuthDetailBean = new UserAuthDetailBean();

    public AuthenticationModel(@NonNull Application application) {
        super(application);
        status1.set(application.getResources().getDrawable(R.drawable.ic_status));
        status2.set(application.getResources().getDrawable(R.drawable.ic_status));
        status3.set(application.getResources().getDrawable(R.drawable.ic_status));
    }

    public void next() {
        setCurrentItem();
    }

    public void setCurrentItem() {
        switch (status) {
            case INIT:
                setFirst();
                status = PHOTO_IDENTITY;
                break;

            case AUTH_SUCCESS:
            case PHOTO_IDENTITY:
                if (id_front_img_id == null || id_back_img_id == null) {
                    ToastUtils.showShort("请先上传身份证正反面");
                    return;
                }

                if (realName.get().isEmpty()) {
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
                if (idnumber.get().isEmpty()) {
                    ToastUtils.showShort("请输入身份证号");
                    return;
                }
                if (idnumber.get().isEmpty()) {
                    ToastUtils.showShort("请输入地址");
                    return;
                }
                if (email.get().isEmpty()) {
                    ToastUtils.showShort("请输入邮箱");
                    return;
                }
                if (zipCode.get().isEmpty()) {
                    ToastUtils.showShort("请输入邮编");
                    return;
                }
                if (validityTime.get().isEmpty()) {
                    ToastUtils.showShort("请输入证件有效期");
                    return;
                }
                setSecond();
                status = PHOTO_HAND_IDENTITY;
                break;
            case PHOTO_HAND_IDENTITY:
                if (id_hold_img_id == null) {
                    ToastUtils.showShort("请先上传手持身份证正面照");
                    return;
                }
                setThird();
                status = PHOTO_CARD;
                buttonLabel.set("提交审核");
                break;
            case PHOTO_CARD:
                if (bank_img_id == null) {
                    ToastUtils.showShort("请先上传银行卡正面照");
                    return;
                }
                if (bankNumber.get().isEmpty()) {
                    ToastUtils.showShort("请输入银行卡号");
                    return;
                }
                if (bankNumberAgain.get().isEmpty()) {
                    ToastUtils.showShort("请再次输入银行卡号");
                    return;
                }
                if (bankName.get().isEmpty()) {
                    ToastUtils.showShort("开户行名称获取失败");
                    return;
                }
                if (branchBankName.get().isEmpty()) {
                    ToastUtils.showShort("支行名称获取失败");
                    return;
                }
                if (bankReservePhone.get().isEmpty()) {
                    ToastUtils.showShort("请输入银行预留手机号");
                    return;
                }
                status3.set(getApplication().getResources().getDrawable(R.drawable.ic_status_check));
                //先要获取银行卡信息
                if (branchNo.isEmpty()) {
                    branchBankSearch();
                } else {
                    submitAuth();
                }
                break;
            case AUTH_FAIL:
            case AUTH_ING:
//            case AUTH_SUCCESS:
                finish();
                break;
        }
    }

    //搜索支行
    public void branchBankSearch() {
        if (branchBankName.get().isEmpty()) {
            ToastUtils.showShort("请输入支行关键词");
            return;
        }
        AppUtils.upload(RetrofitClient.getInstance().create(CardAPI.class).getBranchInfo(
                bankNumber.get(),
                branchBankName.get()),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        branchBankBeanArrayList = (ArrayList<BranchBankBean>) o;
                        ui.searchBranch.set(!ui.searchBranch.get());
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {

                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });

    }


    private void submitAuth() {
        AppUtils.upload(RetrofitClient.getInstance().create(API.class).userAddAuth(
                UserUtil.getUserInfo().getId() + "",
                bankReservePhone.get(),
                realName.get(),
                idnumber.get(),
                bankNumber.get(),
                bankName.get(),
                branchBankName.get(),
                id_front_img, id_back_img, id_hold_img, bank_img,
                branchNo,
                id_front_img_id, id_back_img_id, id_hold_img_id, bank_img_id,
                validityTime.get(),
                zipCode.get(),
                email.get(),
                addr.get(),
                "by_UserIdCard_createAuthInfo"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        ToastUtils.showShort(msg);
                        finish();
                    }

                    @Override
                    public void onError(int code, String msg) {
                        if (code == -2) {
                            //重新调用更新更新认证信息接口
                            AppUtils.upload(RetrofitClient.getInstance().create(API.class).userAddAuth(
                                    UserUtil.getUserInfo().getId() + "",
                                    bankReservePhone.get(),
                                    realName.get(),
                                    idnumber.get(),
                                    bankNumber.get(),
                                    bankName.get(),
                                    branchBankName.get(),
                                    id_front_img, id_back_img, id_hold_img, bank_img,
                                    branchNo,
                                    id_front_img_id, id_back_img_id, id_hold_img_id, bank_img_id,
                                    validityTime.get(),
                                    zipCode.get(),
                                    email.get(),
                                    addr.get(),
                                    "by_UserIdCard_updateAuthInfo"),
                                    getLifecycleProvider(), disposable -> showDialog(),

                                    new ApiDisposableObserver() {
                                        @Override
                                        public void onResult(Object o, String msg, int code) {
                                            ToastUtils.showShort(msg);
                                            finish();
                                        }

                                        @Override
                                        public void onError(int code, String msg) {
                                            if (code == -2) {
                                                //重新调用更新更新认证信息接口
//                                                ToastUtils.showShort("需要重新更新用户信息");
                                            }
                                        }

                                        @Override
                                        public void dialogDismiss() {
                                            dismissDialog();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }

    public void setThird() {
        label.set("银行卡正面照");
        authCard.set(View.VISIBLE);
        photo3.set(View.GONE);
        photo4.set(View.VISIBLE);
        buttonLabel.set("提交审核");
        status2.set(getApplication().getResources().getDrawable(R.drawable.ic_status_check));
    }

    public void setFirst() {
        photoIdentity.set(View.VISIBLE);
        authCard.set(View.GONE);
        photo1.set(View.VISIBLE);
        photo3.set(View.GONE);
        label.set("请扫描身份证");
        buttonLabel.set("下一步");
    }

    public void setSecond() {
        label.set("手持身份证正面照");
        status1.set(getApplication().getResources().getDrawable(R.drawable.ic_status_check));
        photoIdentity.set(View.GONE);
        authCard.set(View.GONE);
        photo1.set(View.GONE);
        photo3.set(View.VISIBLE);
        photo4.set(View.GONE);
        buttonLabel.set("下一步");
    }

    UIChangeListener ui = new UIChangeListener();

    String path1, path2, path3, path4;

    public void uploadImage(String path, final int type) {
        //先压缩
        path = compress(path);
        String temp = path;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);//表单类型
        builder.addFormDataPart("uid", UserUtil.getUserInfo().getId() + "");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("sid", UserUtil.getUserInfo().getSid());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("t", "id_card");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("oss_type", "zmf");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("image", file.getName(), body); //添加图片数据，body创建的请求体
        List<MultipartBody.Part> parts = builder.build().parts();
        AppUtils.upload(RetrofitClient.getInstance().createUpload(API.class).uploadImage(parts),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        UploadImageBean uploadImageBean = (UploadImageBean) o;
                        switch (type) {
                            case 101:
                                path1 = temp;
                                id_front_img = uploadImageBean.getRelative_path();
                                id_front_img_id = uploadImageBean.getOss_key();
                                break;
                            case 102:
                                path2 = temp;
                                id_back_img = uploadImageBean.getRelative_path();
                                id_back_img_id = uploadImageBean.getOss_key();
                                break;
                            case 103:
                                id_hold_img = uploadImageBean.getRelative_path();
                                id_hold_img_id = uploadImageBean.getOss_key();
                                path3 = temp;
                                break;
                            case 104:
                                bank_img = uploadImageBean.getRelative_path();
                                bank_img_id = uploadImageBean.getOss_key();
                                path4 = temp;
                                break;
                        }
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {

                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });

    }

    public void queryAuthStatus() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryAuthInfo(
                UserUtil.getUserInfo().getId() + "",
                "by_UserIdCard_info"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        userAuthDetailBean = (UserAuthDetailBean) o;
                        if (userAuthDetailBean.getVerify() == 2) {
                            //认证中
                            labelAuthStatus.set("资料提交成功正在审核<br /><font color='gray'>预计1-2个工作日完成，请耐心等待</font>");
                            iconAuthStatus.set(getApplication().getResources().getDrawable(R.drawable.ic_auth_ing));
                            status = AUTH_ING;
                            bodyVisible.set(View.GONE);
                            labelAuthStatusVisible.set(View.VISIBLE);
                            buttonLabel.set("返回首页");
                        } else if (userAuthDetailBean.getVerify() == 1) {
                            //认证成功
//                            labelAuthStatus.set("恭喜您认证成功");
                            labelAuthStatus.set("");
//                            buttonLabel.set("返回首页");
                            ToastUtils.showShort("认证已经成功");
                            //显示该用户的认证资料
                            ui.uiChange.set(!ui.uiChange.get());
//                            iconAuthStatus.set(getApplication().getResources().getDrawable(R.drawable.ic_auth_success));
                            bodyVisible.set(View.VISIBLE);
                            labelAuthStatusVisible.set(View.VISIBLE);
                            status = AUTH_SUCCESS;
                            realName.set(userAuthDetailBean.getName());
                            idnumber.set(userAuthDetailBean.getId_no());
                            addr.set(userAuthDetailBean.getAddress());
                            email.set(userAuthDetailBean.getEmail());
                            zipCode.set(userAuthDetailBean.getZipcode());
                            zipCode.set(userAuthDetailBean.getZipcode());
                            id_back_img = userAuthDetailBean.getId_back_img();
                            id_front_img = userAuthDetailBean.getId_front_img();
                            bank_img = userAuthDetailBean.getBank_front_img();
                            id_hold_img = userAuthDetailBean.getId_hold_img();
                            bankNumber.set(userAuthDetailBean.getBank_card_no());
                            bankNumberAgain.set(userAuthDetailBean.getBank_card_no());
                            bankName.set(userAuthDetailBean.getOpening_bank());
                            branchBankName.set(userAuthDetailBean.getBranch_bank());
                            bankReservePhone.set(userAuthDetailBean.getMobile());
                        }
//                        else if (userAuthDetailBean.getVerify() == -1) {
////                            //认证失败
//                            labelAuthStatus.set("认证失败<br />很抱歉，您的认证信息未通过审核<br /> 请在<font color='red'>30</font>天后重审");
//                            iconAuthStatus.set(getApplication().getResources().getDrawable(R.drawable.ic_auth_fail));
//                            status = AUTH_FAIL;
//                            bodyVisible.set(View.GONE);
//                            labelAuthStatusVisible.set(View.VISIBLE);
////                            bodyVisible.set(View.VISIBLE);
//                            buttonLabel.set("返回首页");
//                        }
                        else if (userAuthDetailBean.getVerify() == 0 || userAuthDetailBean.getVerify() == -1) {
                            //未提交审核
                            status = INIT;
                            bodyVisible.set(View.VISIBLE);
                            labelAuthStatusVisible.set(View.GONE);
                            buttonLabel.set("下一步");
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        if (code == -2) {
                            //未提交审核
                            status = INIT;
                            bodyVisible.set(View.VISIBLE);
                            labelAuthStatusVisible.set(View.GONE);
                            buttonLabel.set("下一步");
                        }
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }

    public class UIChangeListener {
        ObservableBoolean photo1 = new ObservableBoolean(false);
        ObservableBoolean photo2 = new ObservableBoolean(false);
        ObservableBoolean photo3 = new ObservableBoolean(false);
        ObservableBoolean photo4 = new ObservableBoolean(false);
        ObservableBoolean searchBranch = new ObservableBoolean(false);
        ObservableBoolean uiChange = new ObservableBoolean(false);
    }

    public void photo(int type) {
        switch (type) {
            case 1:
                ui.photo1.set(!ui.photo1.get());
                break;
            case 2:
                ui.photo2.set(!ui.photo2.get());
                break;
            case 3:
                ui.photo3.set(!ui.photo3.get());
                break;
            case 4:
                ui.photo4.set(!ui.photo4.get());
                break;
        }
    }

    enum Status {
        INIT,
        AUTH_ING,
        AUTH_SUCCESS,
        AUTH_FAIL,
        PHOTO_IDENTITY,
        PHOTO_HAND_IDENTITY,
        PHOTO_CARD
    }
}
