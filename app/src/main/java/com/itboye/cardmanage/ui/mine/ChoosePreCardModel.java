package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class ChoosePreCardModel extends BaseViewModel {
    public ChoosePreCardModel(@NonNull Application application) {
        super(application);
    }

    //提交确认选择当前卡片
    public void submit() {
        ToastUtils.showShort("提交啦");
    }
}
