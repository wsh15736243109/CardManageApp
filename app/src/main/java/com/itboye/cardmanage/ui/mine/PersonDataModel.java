package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
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
        ToastUtils.showShort("保存信息");
    }
}
