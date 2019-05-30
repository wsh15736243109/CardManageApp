package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
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
        ToastUtils.showShort("修改成功");
    }
}
