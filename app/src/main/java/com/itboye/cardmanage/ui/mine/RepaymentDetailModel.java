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
    public ObservableField<String> fee = new ObservableField<>();
    public ObservableField<String> days = new ObservableField<>("");

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
        //保存计划
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).createCbPlan(amount.get(), days.get(), pre_store_money, Double.valueOf(fee.get()), preStoreCardIds, creditCardIds, "by_CbPlan_create"), getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                showDialog();
            }
        }, new ApiDisposableObserver() {
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
        //重启计划
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).restartCbPlan(id, "by_CbPlan_reboot"), getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                showDialog();
            }
        }, new ApiDisposableObserver() {
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
