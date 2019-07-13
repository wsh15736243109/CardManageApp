package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.bean.PayWaybean;
import com.itboye.cardmanage.bean.ReceiveMoneyAuthPassBean;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.web.WebActivity;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;

public class ReceiveMoneyModel extends BaseViewModel {

    public ObservableField<String> amount = new ObservableField<>("");
    public ObservableField<String> note = new ObservableField<>("0");
    public ObservableField<String> arrivalAmount = new ObservableField<>("");
    public String pay_card_id = "";//支付卡id
    public String withdraw_card_id = "";//到账结算卡id
    public String pay_channel_id = "";//支付通道id
//    public String order_code;//订单号
    public ObservableBoolean payChannel = new ObservableBoolean(false);
    public ArrayList<PayWaybean> payWaybeanArrayList;
    public String phone;

    public ReceiveMoneyModel(@NonNull Application application) {
        super(application);
        registerRxBus();
    }

    public void submit() {
        if (amount.get().isEmpty()) {
            ToastUtils.showShort("请输入金额");
            return;
        }
        if (pay_card_id.isEmpty()) {
            ToastUtils.showShort("请选择支付卡");
            return;
        }
        if (withdraw_card_id.isEmpty()) {
            ToastUtils.showShort("请选择到账结算卡");
            return;
        }
        if (pay_channel_id.isEmpty()) {
            ToastUtils.showShort("请选择支付通道");
            return;
        }
        sendPayment();
    }

    //发起收款请求(第一次发送验证码 第二次支付)
    private void sendPayment() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 3);
        bundle.putString("phone", phone);
//        bundle.putString("order_code", order_code);
        bundle.putString("amount", amount.get());
        bundle.putString("pay_card_id", pay_card_id);
        bundle.putString("withdraw_card_id", withdraw_card_id);
        bundle.putString("pay_channel_id", pay_channel_id);
        startActivity(AuthMobileActivity.class, bundle);
    }

    public void choosePayCard(int index) {
        if (!withdraw_card_id.isEmpty() && index == 1) {//已经获取到到账结算卡就不用去选择了
            return;
        }
        //选择支付卡
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        bundle.putInt("type", 0);
        startActivity(CardManageActivity.class, bundle);
    }


    public void getPayWay() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryPayWays("by_PaymentChannel_query"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                payWaybeanArrayList = (ArrayList<PayWaybean>) o;
                payChannel.set(!payChannel.get());
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

    public void toWeb() {
        Bundle bundle = new Bundle();
        bundle.putString("title", "升级VIP");
        bundle.putString("url", Global.H5URL + Global.UPDATE_VIP);
        startActivity(WebActivity.class, bundle);
    }

//    //移除RxBus
//    @Override
//    public void removeRxBus() {
//        super.removeRxBus();
//        //将订阅者从管理站中移除
//        RxSubscriptions.remove(mSubscription);
//    }
}
