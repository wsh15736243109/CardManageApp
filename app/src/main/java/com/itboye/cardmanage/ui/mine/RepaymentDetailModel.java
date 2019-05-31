package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class RepaymentDetailModel extends BaseViewModel {
    public RepaymentDetailModel(@NonNull Application application) {
        super(application);
    }

    //添加还款计划
    public void addRepaymentPlan() {

    }

    //添加预存资金卡
    public void addPreCard() {
        startActivity(ChoosePreCardActivity.class);
    }
}
