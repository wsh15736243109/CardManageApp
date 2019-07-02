package com.itboye.cardmanage;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.ui.mine.AuthenticationActivity;
import com.itboye.cardmanage.util.UserUtil;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MainModel extends BaseViewModel {

    public ObservableField authStatus = new ObservableField(true);

    public MainModel(@NonNull Application application) {
        super(application);
        if (UserUtil.getUserInfo()!=null) {
            authStatus.set(UserUtil.getUserInfo().getId_validate() == 1 ? true: false);
        }else{
            authStatus.set(false);
        }
    }

    public void toAuth() {
        //去验证
        startActivity(AuthenticationActivity.class);
    }


}
