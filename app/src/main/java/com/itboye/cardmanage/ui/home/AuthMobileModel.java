package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AuthMobileModel extends BaseViewModel {

    public ObservableField<String> yzStatus = new ObservableField<>("没收到验证码?<font color='red'>重新发送</font>");

    int status = 1;
    public String bankId;
    public String verificationCode;
    int type = 1;

    public AuthMobileModel(@NonNull Application application) {
        super(application);
    }

    public void authMobile() {
        switch (status) {
            case 1:
                //立即验证
                ToastUtils.showShort("立即验证");
                sendAuthCode(true);
                //验证
                yzStatus.set("验证成功，返回首页");
                status = 2;
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

    public void sendAuthCode(boolean isAuth) {
        if (type == 1) {//开通代扣
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signWithholding(bankId, verificationCode, "by_UserBankCard_signWithhold"), getLifecycleProvider(), new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    showDialog();
                }
            }, new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    if (isAuth) {

                    }else{

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
        } else {
            //开通代付
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).signRepay(bankId, verificationCode, "by_UserBankCard_signWithhold"), getLifecycleProvider(), new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    showDialog();
                }
            }, new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    if (isAuth) {

                    }else{

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
    }
}
