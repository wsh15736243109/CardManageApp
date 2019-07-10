package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class AuthDataOpenModel extends BaseViewModel {

    public ObservableField<String> identityInformation = new ObservableField<>("<b><font color='black'>身份信息</font></b><br />身份信息已验证通过");
    public ObservableField<String> handInformation = new ObservableField<>("<b><font color='black'>手持拍照信息</font></b><br />手持拍照信息已验证通过");
    public ObservableField<String> bankInformation = new ObservableField<>("<b><font color='black'>银行卡认证信息</font></b><br />银行卡认证信息已验证通过");

    public AuthDataOpenModel(@NonNull Application application) {
        super(application);
    }

    public void toAuthActivity(int page){
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        bundle.putInt("page", page);
        startActivity(AuthenticationActivity.class, bundle);
    }
}
