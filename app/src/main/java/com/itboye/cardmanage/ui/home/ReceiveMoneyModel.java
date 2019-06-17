package com.itboye.cardmanage.ui.home;

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
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class ReceiveMoneyModel extends BaseViewModel {

    public ObservableField<String> amount = new ObservableField<>("");
    public ObservableField<String> note = new ObservableField<>("");
    public String pay_card_id = "";//支付卡id
    public String withdraw_card_id = "";//支付卡id
    public String pay_channel_id = "";//支付卡id

    public ReceiveMoneyModel(@NonNull Application application) {
        super(application);
    }

    public void submit() {
        if (amount.get().isEmpty()) {
            ToastUtils.showShort("请输入金额");
            return;
        }
        //立即下单
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).createPaymentOrder(amount.get(), note.get(), pay_card_id, withdraw_card_id, pay_channel_id, "by_CbOrder_autoReceipt"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
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

    public void choosePayCard() {
        //选择支付卡
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        startActivity(CardManageActivity.class,bundle);
    }
}
