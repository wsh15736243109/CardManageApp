package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

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

    //保存
    public void save() {

    }
}
