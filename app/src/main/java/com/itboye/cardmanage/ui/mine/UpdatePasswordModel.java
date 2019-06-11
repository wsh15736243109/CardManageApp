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

public class UpdatePasswordModel extends BaseViewModel {


    public ObservableField<String> oldPassword = new ObservableField<>("");
    public ObservableField<String> newPassword = new ObservableField<>("");
    public ObservableField<String> newPasswordConfirm = new ObservableField<>("");

    public UpdatePasswordModel(@NonNull Application application) {
        super(application);
    }

    //确认修改密码
    public void updateConfirm() {
        if (oldPassword.get().isEmpty()) {
            ToastUtils.showShort("请输入原密码");
            return;
        }
        if (newPassword.get().isEmpty()) {
            ToastUtils.showShort("请输入新密码");
            return;
        }
        if (newPasswordConfirm.get().isEmpty()) {
            ToastUtils.showShort("请再次输入新密码");
            return;
        }
        if (!newPassword.get().equals(newPasswordConfirm.get())) {
            ToastUtils.showShort("密码不一致");
            return;
        }
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).forgetPasswordByPwd(UserUtil.getUserInfo().getId() + "", oldPassword.get(), newPassword.get(), UserUtil.getUserInfo().getSid(), "by_UserLoginSession_updatePassword"),
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
