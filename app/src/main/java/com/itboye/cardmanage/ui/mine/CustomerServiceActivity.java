package com.itboye.cardmanage.ui.mine;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityContactCustomerServiceBinding;

public class CustomerServiceActivity extends BaseMVVMActivity<ActivityContactCustomerServiceBinding, CustomerServiceModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_contact_customer_service;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
