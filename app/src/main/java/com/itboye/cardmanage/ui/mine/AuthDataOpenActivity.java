package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityAuthDataOpenBinding;

public class AuthDataOpenActivity extends BaseMVVMActivity<ActivityAuthDataOpenBinding,AuthDataOpenModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_auth_data_open;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
