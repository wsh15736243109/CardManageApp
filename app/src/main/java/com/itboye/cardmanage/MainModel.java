package com.itboye.cardmanage;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.ui.mine.AuthenticationActivity;
import com.itboye.cardmanage.util.UserUtil;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MainModel extends BaseViewModel {

    public ObservableField authStatus = new ObservableField(true);
    public ObservableField<String> authLabel = new ObservableField<>("您尚未完成认证,认证后方可收款！<u>马上认证</u>");

    public MainModel(@NonNull Application application) {
        super(application);

    }

    public void setAuthStatus(){
        if (UserUtil.getUserInfo()!=null) {
            authStatus.set(UserUtil.getUserInfo().getId_validate() == 0);
        }else{
            authStatus.set(false);
        }
    }

    public void toAuth() {
        //去验证
        Bundle bundle=new Bundle();
        bundle.putInt("type",1);
        startActivity(AuthenticationActivity.class,bundle);
    }


}
