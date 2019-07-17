package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.databinding.ActivityMyWalletBinding;

public class MyWalletActivity extends BaseMVVMActivity<ActivityMyWalletBinding, MyWalletModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_wallet;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
