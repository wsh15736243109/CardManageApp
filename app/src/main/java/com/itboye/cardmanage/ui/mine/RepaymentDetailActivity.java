package com.itboye.cardmanage.ui.mine;

import android.app.Activity;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityRepaymentDetailBinding;

public class RepaymentDetailActivity extends BaseMVVMActivity<ActivityRepaymentDetailBinding, RepaymentDetailModel> {

    private int type;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_repayment_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            setTitle("添加还款计划");
        } else {
            setRightText("删除");
            setTitle("计划详情");
        }
    }
}
