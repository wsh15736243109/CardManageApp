package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.bean.RepaymentDetailBean;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.math.BigDecimal;

public class RepaymentDetailModel extends BaseViewModel {


    public String preStoreCardIds;
    public String creditCardIds;
    public String pre_store_money = "1400";
    public String id;

    public ObservableField<String> amount = new ObservableField<>("");
    public ObservableField<String> fee = new ObservableField<>("0.00<br />手续费（元）");
    public String feeValue = "";
    public ObservableField<String> days = new ObservableField<>("");
    public ObservableField<Integer> planType = new ObservableField<>(View.GONE);
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
        if (feeValue == null) {
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

//        {"credit_card_ids":"56","days":"1","fee":"1328.00","money":"100000.00","pre_store_card_id":"54","pre_store_money":"101328.00"}
//        {"credit_card_ids":"56","days":"1","fee":"133.00","money":"10000.00","pre_store_card_id":"54","pre_store_money":"10133.00"}
//        手续费加每次还款金额必须等于预存金额
//        pre_store_money = String.format("%.2f", Double.parseDouble(feeValue) * 100 + Double.parseDouble(amount.get()) / Double.parseDouble(days.get()) * 100);
        int value1 = (int) (new BigDecimal(amount.get()).doubleValue() * 100);
        int value2 = (int) (new BigDecimal(pre_store_money).doubleValue() * 100);
        int value3 = (int) (new BigDecimal(feeValue).doubleValue() * 100);
        //保存计划
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).createCbPlan(value1 + "", days.get(), value2 + "", value3 + "", preStoreCardIds, creditCardIds, "by_CbPlan_create"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
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
        if (feeValue == null) {
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

    public void setRepaymentDetail(RepaymentDetailBean model) {
        amount.set(model.getMoney() / 100 + "");
        days.set(model.getDays() + "");
        fee.set(model.getTotal_fee() / 100 + "<br />手续费（元）");
        yucun.set(model.getPrestore_money() / 100 + "<br />预存（元）");
        yuqihuankuanzonge.set(model.getRepay_total_money() / 100 + "<br />预存还款总额（元）");
    }
}
