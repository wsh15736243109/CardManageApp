package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityAuthenticationBinding;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;

public class AuthenticationActivity extends BaseMVVMActivity<ActivityAuthenticationBinding, AuthenticationModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_authentication;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
