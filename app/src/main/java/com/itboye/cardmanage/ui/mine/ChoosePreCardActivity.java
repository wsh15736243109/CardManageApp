package com.itboye.cardmanage.ui.mine;

import android.app.Activity;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityChoosePreCardBinding;

public class ChoosePreCardActivity extends BaseMVVMActivity<ActivityChoosePreCardBinding,ChoosePreCardModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_choose_pre_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
