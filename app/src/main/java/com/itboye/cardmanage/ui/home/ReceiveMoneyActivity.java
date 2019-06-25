package com.itboye.cardmanage.ui.home;

import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.PayWaybean;
import com.itboye.cardmanage.databinding.ActivityReceiveMoneyBinding;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class ReceiveMoneyActivity extends BaseMVVMActivity<ActivityReceiveMoneyBinding, ReceiveMoneyModel> {


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
                        String fee = payWaybean.getFee_per();
                        View view = View.inflate(ReceiveMoneyActivity.this, R.layout.item_pay_channel, null);
                        view.setPadding(24, 24, 24, 24);
                        TextView textView = view.findViewById(R.id.tv_title);
                        CheckBox checkBox = view.findViewById(R.id.cb_check);
                        checkBox.setTag(payWaybean.getId());
                        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if (b) {
                                    viewModel.pay_channel_id =(compoundButton.getTag() + "");
                                }else{

                                }
                            }
                        });
                        textView.setText(title + "(" + timeStart + "-" + timeEnd + "交易)");
                        binding.payChannel.addView(view);
                    }

                }
            }
        });
    }

}
