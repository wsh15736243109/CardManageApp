package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.single.SingleFlatMapIterableFlowable;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class RepaymentDetailModel extends BaseViewModel {


    public String preStoreCardIds;
    public String creditCardIds;
    public String pre_store_money = "1400";
    public String id;

    public ObservableField<String> amount = new ObservableField<>("");
    public ObservableField<String> fee = new ObservableField<>("0.00<br />手续费（元）");
    public double feeValue = -1;
    public ObservableField<String> days = new ObservableField<>("");
    public double daysValue = 0;
    public ObservableField<String> yucun = new ObservableField<>("0.00<br />预存（元）");
    public double yucunValue = 0;
    public ObservableField<String> yuqihuankuanzonge = new ObservableField<>("0.00<br />预期还款总额（元）");
    public double yuqihuankuanzongeValue = 0;

    public RepaymentDetailModel(@NonNull Application application) {
        super(application);
    }

    //添加还款计划
    public void addRepaymentPlan(int usage, int chooseCount) {
        Bundle bundle = new Bundle();
        bundle.putInt("chooseCount", chooseCount);
        bundle.putInt("usage", usage);
        if (usage == 1) {
            bundle.putString("title", "添加还款计划卡");
        } else {
            bundle.putString("title", "添加预存资金卡");
        }
        startActivity(CardListActivity.class, bundle);

    }

//    //添加预存资金卡
//    public void addPreCard() {
//        startActivity(ChoosePreCardActivity.class);
//    }

    //保存
    public void save() {
        if (amount.get().isEmpty()) {
            ToastUtils.showShort("请填写预算金额");
            return;
        }
        if (days.get().isEmpty()) {
            ToastUtils.showShort("请填写还款周期");
            return;
        }
        if (feeValue == -1) {
            ToastUtils.showShort("手续费获取失败，请稍后再试");
            return;
        }
        if (creditCardIds == null) {
            ToastUtils.showShort("请选择还款计划卡");
            return;
        }
        if (preStoreCardIds == null) {
            ToastUtils.showShort("请选择预存资金卡");
            return;
        }

        //保存计划
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).createCbPlan(amount.get(), days.get(), pre_store_money, feeValue, preStoreCardIds, creditCardIds, "by_CbPlan_create"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ToastUtils.showShort(msg);
                finish();
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }

    public void restartCbPlan() {
        if (amount.get().isEmpty()) {
            ToastUtils.showShort("请填写预算金额");
            return;
        }
        if (days.get().isEmpty()) {
            ToastUtils.showShort("请填写还款周期");
            return;
        }
        if (feeValue == -1) {
            ToastUtils.showShort("手续费获取失败，请稍后再试");
            return;
        }
        if (creditCardIds == null) {
            ToastUtils.showShort("请选择还款计划卡");
            return;
        }
        if (preStoreCardIds == null) {
            ToastUtils.showShort("请选择预存资金卡");
            return;
        }
        //重启计划
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).restartCbPlan(id, "by_CbPlan_reboot"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ToastUtils.showShort(msg);
                finish();
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }

}
