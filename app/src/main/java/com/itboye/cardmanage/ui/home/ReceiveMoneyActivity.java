package com.itboye.cardmanage.ui.home;

import android.app.Activity;
import android.database.Observable;
import android.databinding.ObservableField;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityReceiveMoneyBinding;

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
}
