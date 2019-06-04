package com.itboye.cardmanage.ui.home;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityAddCardBinding;
import com.itboye.cardmanage.databinding.ActivityCardDetailBinding;

public class CardDetailActivity extends BaseMVVMActivity<ActivityCardDetailBinding,AddCardModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_card_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }
}
