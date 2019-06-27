package com.itboye.cardmanage.ui.home;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityAuthMobileBinding;

public class AuthMobileActivity extends BaseMVVMActivity<ActivityAuthMobileBinding, AuthMobileModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_auth_mobile;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.bankId = getIntent().getStringExtra("bank_id");
        viewModel.order_code = getIntent().getStringExtra("order_code");
        viewModel.type = getIntent().getIntExtra("type",1);
        binding.phone.setText(getIntent().getStringExtra("phone"));
        viewModel.sendAuthCode(false);
    }
}
