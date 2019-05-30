package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;

public class PersonDataActivity extends BaseMVVMActivity<ActivitySplashBinding, PersonDataModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_person_data;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
