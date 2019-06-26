package com.itboye.cardmanage.ui.home;

import android.app.Activity;
import android.os.Bundle;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityOpenBinding;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;

public class Open extends BaseMVVMActivity<ActivityOpenBinding, OpenModel> {

    private int type;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_open;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type", 0);
        viewModel.bank_id = getIntent().getStringExtra("bank_id");
        viewModel.phone = getIntent().getStringExtra("phone");
        binding.titleBar.setTitle(type == 0 ? "开通代扣" : "开通代付");
    }
}
