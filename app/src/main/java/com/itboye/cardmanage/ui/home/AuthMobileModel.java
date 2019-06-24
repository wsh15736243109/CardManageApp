package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AuthMobileModel extends BaseViewModel {

    public ObservableField<String> yzStatus = new ObservableField<>("没收到验证码?<font color='red'>重新发送</font>");

    int status = 1;

    public AuthMobileModel(@NonNull Application application) {
        super(application);
    }

    public void authMobile() {
        switch (status) {
            case 1:
                //立即验证
                ToastUtils.showShort("立即验证");
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

}
