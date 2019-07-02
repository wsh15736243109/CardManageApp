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
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

import java.math.BigDecimal;

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
    }

    private void caculateArrivalAmount() {
        String amount = binding.etAmount.getText().toString();
        if (amount.isEmpty()) {
            return;
        }
        if (Double.parseDouble(amount) <= 20) {
            viewModel.arrivalAmount.set("收款金额要大于20元");
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
                        String timeStart = payWaybean.getDay_time_start();
                        String timeEnd = payWaybean.getDay_time_end();
                        View view = View.inflate(ReceiveMoneyActivity.this, R.layout.item_pay_channel, null);
                        view.setPadding(24, 24, 24, 24);
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

                            }
                        });
                        textView.setText(title + "(" + payWaybean.getFee_per() + "+" + payWaybean.getFixed_fee() + "   " + timeStart + "-" + timeEnd + "交易)");
                        binding.payChannel.addView(view);
                    }

                }
            }
        });


    }

}
