package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.UserUtil;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class PersonDataModel extends BaseViewModel {
    //用户名的绑定
    public ObservableField<String> nickName = new ObservableField<>("");

    public PersonDataModel(@NonNull Application application) {
        super(application);
    }

    //更换头像
    public void updateHead() {
        ToastUtils.showShort("更换头像");
    }

    //保存用户更改信息
    public void saveData() {
        if (nickName.get().isEmpty()) {
            ToastUtils.showShort("昵称为空");
            return;
        }

        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).updateUserInfo(nickName.get(), UserUtil.getUserInfo().getSid(), UserUtil.getUserInfo().getId() + "", "by_UserLoginSession_updateInfo"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg) {
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }
}
