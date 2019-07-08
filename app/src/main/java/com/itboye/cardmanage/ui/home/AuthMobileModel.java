package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AuthMobileModel extends BaseViewModel {

    public ObservableField<String> yzStatus = new ObservableField<>("没收到验证码?<font color='red'>重新发送</font>");
    public ObservableField<String> code = new ObservableField<>();

    int status = 1;
    public String bankId;
    public ObservableField<String> verificationCode = new ObservableField<>("");
    public String order_code;
    int type = 1;

    public AuthMobileModel(@NonNull Application application) {
        super(application);
    }

    public void authMobile() {
        switch (status) {
            case 1:
                //立即验证
                if (type == 3) {
                    //收款验证
                    receiveMoneyAuth();
                } else {
                    sendAuthCode(true);
                }
                break;
            case 2:
                //验证成功，返回首页
                finish();
                break;
            case 3:
                //验证失败，重新验证
                yzStatus.set("验证失败，重新验证");
                status = 1;
                break;
        }

    }

    private void receiveMoneyAuth() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).sendPayment(order_code, verificationCode.get(), "by_CbOrder_quickPay"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ToastUtils.showShort(msg);
                RxBus.getDefault().post(1);//关闭Open.class
                finish();
            }

            @Override
            public void onError(int code, String msg) {
                RxBus.getDefault().post(1);//关闭Open.class
                finish();
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }

    public void sendAuthCode(boolean isAuth) {
        String serviceType = "";
        if (type == 1) {  //开通代扣
            serviceType = "by_UserBankCard_signWithhold";
            if (isAuth) {
                if (verificationCode.get().equals("")) {
                    ToastUtils.showShort("请填写收到的验证码");
                    return;
                }
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signAuth(bankId, verificationCode.get(), serviceType), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        //验证
                        yzStatus.set("验证成功，返回首页");
                        status = 2;
                        RxBus.getDefault().post(1);//关闭Open.class
                        finish();
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        //验证
//                        yzStatus.set("验证失败，重新尝试");
                        status = 3;
                        RxBus.getDefault().post(1);//关闭Open.class
                        finish();
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
            } else {
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signGetCode(bankId, verificationCode.get(), serviceType), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        yzStatus.set("验证成功，返回首页");
                        status = 2;
                        RxBus.getDefault().post(1);//关闭Open.class
                        finish();
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {
//                        if (isAuth) {
//                            //验证
////                        yzStatus.set("验证失败，重新尝试");
                        RxBus.getDefault().post(1);//关闭Open.class
                        finish();
                        status = 3;
//                        RxBus.getDefault().post(1);//关闭Open.class
//                        finish();
//                        } else {
//
//                        }
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
            }

        } else if (type == 2) {//开通代付
            serviceType = "by_UserBankCard_signRepay";
            if (isAuth) {
                if (verificationCode.get().isEmpty()) {
                    ToastUtils.showShort("请填写收到的验证码");
                    return;
                }
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signAuth(bankId, verificationCode.get(), serviceType), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {

                        RxBus.getDefault().post(1);//关闭Open.class
                        finish();
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
            } else {
                //开通代付
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signGetCode(bankId, null, serviceType), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {
                        RxBus.getDefault().post(1);//关闭Open.class
                        finish();
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
            }
        } else if (type == 3) { //收款验证码验证

        }
    }
}
