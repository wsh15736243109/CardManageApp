package com.itboye.cardmanage.ui.home;

import android.databinding.Observable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.PayWaybean;
import com.itboye.cardmanage.databinding.ActivityReceiveMoneyBinding;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.TimeUtils;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ReceiveMoneyActivity extends BaseMVVMActivity<ActivityReceiveMoneyBinding, ReceiveMoneyModel> {


    private Disposable mSubscription;
    private String fee = "0";
    private String fixAmount = "0";

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_receive_money;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //获取支付通道
        viewModel.getPayWay();
        getMasterArrivalCard();
        mSubscription = RxBus.getDefault().toObservable(CardManageModel.class)
                .subscribe(s -> {
                    if (s.getChooseType().equals("1")) {
                        viewModel.pay_card_id = s.getId();//支付卡id
                        binding.tvCardBank.setText(s.getBranch_bank());
                    } else {
                        viewModel.withdraw_card_id = s.getId();//结算卡账户id
                        binding.tvWithdrawCard.setText(s.getBranch_bank());//结算卡账户

                    }
                    viewModel.phone = s.getMobile();//支付卡id
                });

        RxBus.getDefault().toObservable(Integer.class)
                .subscribe(s -> {
                    finish();
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);

        binding.etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                caculateArrivalAmount();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //费率介绍
        binding.titleBar.getTvRight().setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("array", viewModel.payWaybeanArrayList);
            startActivity(FeiLvAboutActivity.class, bundle);
        });
    }

    //获取到账结算卡主卡
    private void getMasterArrivalCard() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).cardList("2", "1", "10", "by_UserBankCard_query"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ArrayList<CardManageModel> ar = (ArrayList<CardManageModel>) o;
                if (ar != null) {
                    for (CardManageModel model : ar) {
                        if (model.getMaster() == 1) {
                            viewModel.withdraw_card_id = model.getId();//结算卡账户id
                            binding.tvWithdrawCard.setText(model.getBranch_bank());//结算卡账户
                            break;
                        }
                    }
                }
            }

            @Override
            public void onError(int code, String msg) {
            }

            @Override
            public void dialogDismiss() {
                viewModel.dismissDialog();
            }
        });
    }

    private void caculateArrivalAmount() {
        String amount = binding.etAmount.getText().toString();
        if (amount.isEmpty()) {
            return;
        }
        if (Double.parseDouble(amount) < 50) {
            viewModel.arrivalAmount.set("收款金额不小于50元");
            return;
        }
        viewModel.arrivalAmount.set("到账金额：￥" + String.format("%.2f", Double.parseDouble(amount) * Double.parseDouble(fee) - Double.parseDouble(fixAmount)));
    }

    @Override
    public void initViewObservable() {
        viewModel.payChannel.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                //获取到了支付通道
                if (viewModel.payWaybeanArrayList != null && viewModel.payWaybeanArrayList.size() > 0) {
                    for (int i = 0; i < viewModel.payWaybeanArrayList.size(); i++) {
                        PayWaybean payWaybean = viewModel.payWaybeanArrayList.get(i);
                        String title = payWaybean.getTitle();
                        String timeStart = TimeUtils.parseTime(TimeUtils.getStrFormat(payWaybean.getDay_time_start(), 4), "HHmm", "HH:mm");
                        String timeEnd = TimeUtils.parseTime(TimeUtils.getStrFormat(payWaybean.getDay_time_end(), 4), "HHmm", "HH:mm");
                        View view = View.inflate(ReceiveMoneyActivity.this, R.layout.item_pay_channel, null);
                        view.setPadding(0, 24, 24, 24);
                        TextView textView = view.findViewById(R.id.tv_title);
                        CheckBox checkBox = view.findViewById(R.id.cb_check);
                        checkBox.setTag(i);
                        checkBox.setOnCheckedChangeListener((compoundButton, b) -> {
                            if (b) {
                                viewModel.pay_channel_id = viewModel.payWaybeanArrayList.get(Integer.parseInt(compoundButton.getTag() + "")).getId() + "";
                                fee = viewModel.payWaybeanArrayList.get(Integer.parseInt(compoundButton.getTag() + "")).getFee_per() + "";
                                fixAmount = viewModel.payWaybeanArrayList.get(Integer.parseInt(compoundButton.getTag() + "")).getFixed_fee() + "";
                                caculateArrivalAmount();
                            } else {
                                viewModel.pay_channel_id = "";
                                fee = "0";
                                fixAmount = "0";
                                caculateArrivalAmount();
                            }
                        });
                        textView.setText(title + "(" + payWaybean.getFee_per() + "%+" + payWaybean.getFixed_fee() + "   " + timeStart + "-" + timeEnd + "交易)");
                        binding.payChannel.addView(view);
                    }

                }
            }
        });


    }

}
