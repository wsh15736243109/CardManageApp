package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;

public class MyTransactionActivity extends BaseMVVMActivity<ActivitySplashBinding, MyTranslationModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_translation;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}