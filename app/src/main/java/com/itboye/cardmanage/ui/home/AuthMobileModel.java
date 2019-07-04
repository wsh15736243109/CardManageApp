package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AuthMobileModel extends BaseViewModel {

    public ObservableField<String> yzStatus = new ObservableField<>("");
    public ObservableField<String> authButtonLabel = new ObservableField<>("立即验证");
    public ObservableField<String> codeStatus = new ObservableField<>("验证码已经发送到你的手机号码上");
    public ObservableField<String> resendCode = new ObservableField<>("没收到验证码?<font color='red'>重新发送</font>");
    public ObservableField<String> code = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<Integer> authStatus = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> authFail = new ObservableField<>(View.GONE);
    public ObservableField<Drawable> authIcon = new ObservableField();

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
                sendAuthCode(true);
                break;
            case 2:
                //验证成功，返回首页
                RxBus.getDefault().post(0);//关闭Open.class
                finish();
                break;
            case 3:
                //验证失败，重新验证
                sendAuthCode(false);
                break;
        }

    }

    private void receiveMoneyAuth(String code, boolean isAuth) {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).sendPayment(order_code, code, "by_CbOrder_quickPay"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                if (isAuth) {
                    setAuthStatus(true, msg);
                } else {
                    setCodeSendStatus(true);
                }
            }

            @Override
            public void onError(int code, String msg) {
                if (isAuth) {
                    setAuthStatus(false, msg);
                } else {
                    setCodeSendStatus(false);
                }
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }

    private void setAuthStatus(boolean b, String msg) {
        if (b) {
            status = 2;
            yzStatus.set("验证成功<br />系统将在24小时内放款");
            authButtonLabel.set("返回首页");
            authStatus.set(View.INVISIBLE);
            authFail.set(View.VISIBLE);
            authIcon.set(getApplication().getResources().getDrawable(R.drawable.ic_auth_success));
            ToastUtils.showShort(msg);
        } else {
            status = 3;
            yzStatus.set("验证失败，重新验证<br />验证码验证失败，请重新验证");
            authButtonLabel.set("重新验证");
            authStatus.set(View.INVISIBLE);
            authFail.set(View.VISIBLE);
            authIcon.set(getApplication().getResources().getDrawable(R.drawable.ic_phone_auth_fail));
            ToastUtils.showShort(msg);
        }
    }

    private void initUI() {
        status = 1;
        yzStatus.set("");
        authButtonLabel.set("立即验证");
        authStatus.set(View.VISIBLE);
        authFail.set(View.INVISIBLE);
    }

    public void sendAuthCode(boolean isAuth) {
        String serviceType = "";
        status = 1;
        initUI();
        switch (type) {
            case 1:   //开通代扣
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
                            setAuthStatus(true, msg);
                        }

                        @Override
                        public void onError(int code, String msg) {
                            setAuthStatus(false, msg);
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
                            //验证码发送成功
                            setCodeSendStatus(true);
                        }

                        @Override
                        public void onError(int code, String msg) {
                            setCodeSendStatus(false);
                        }

                        @Override
                        public void dialogDismiss() {
                            dismissDialog();
                        }
                    });
                }

                break;
            case 2: //开通代付
                serviceType = "by_UserBankCard_signRepay";
                if (isAuth) {
                    if (verificationCode.get().isEmpty()) {
                        ToastUtils.showShort("请填写收到的验证码");
                        return;
                    }
                    AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signAuth(bankId, verificationCode.get(), serviceType), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                        @Override
                        public void onResult(Object o, String msg, int code) {
                            status = 2;
                            setAuthStatus(true, msg);
                            ToastUtils.showShort(msg);
                        }

                        @Override
                        public void onError(int code, String msg) {
                            status = 3;
                            setAuthStatus(false, msg);
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
                            setCodeSendStatus(true);
                        }

                        @Override
                        public void onError(int code, String msg) {
                            setCodeSendStatus(false);
                        }

                        @Override
                        public void dialogDismiss() {
                            dismissDialog();
                        }
                    });
                }
                break;
            case 3:  //收款验证码验证
                if (isAuth) {
                    if (verificationCode.get().equals("")) {
                        ToastUtils.showShort("请填写收到的验证码");
                        return;
                    }
                    receiveMoneyAuth(verificationCode.get(), isAuth);
                } else {
                    receiveMoneyAuth(null, isAuth);
                }

                break;
        }
    }

    private void setCodeSendStatus(boolean b) {
        if (b) {
            codeStatus.set("验证码已发送至您的手机号码上");
            ToastUtils.showShort("验证码发送成功");
        } else {
            codeStatus.set("验证码发送失败");
        }
    }
}
