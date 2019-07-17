package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class MyWalletModel extends BaseViewModel {

    public ObservableField<String> banlance = new ObservableField<>("账户余额<br />￥0");
    public ObservableField<String> withdrawAmount = new ObservableField<>("0");
    public ObservableField<String> arrivalBank = new ObservableField<>("到账结算卡");

    public MyWalletModel(@NonNull Application application) {
        super(application);
    }

    public void submit() {
        //提交提现申请

    }
}
