package com.itboye.cardmanage.ui.mine;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityRepaymentPlanBinding;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;

public class RepaymentPlanActivity extends BaseMVVMActivity<ActivityRepaymentPlanBinding, RepaymentPlanModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_repayment_plan;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
