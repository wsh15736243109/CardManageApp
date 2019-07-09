package com.itboye.cardmanage.ui.home;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.FeilvAboutAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.PayWaybean;
import com.itboye.cardmanage.databinding.ActivityFeiLvAboutBinding;

import java.util.ArrayList;

public class FeiLvAboutActivity extends BaseMVVMActivity<ActivityFeiLvAboutBinding, FeiLvAboutModel> {

    ArrayList<PayWaybean> ar;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_fei_lv_about;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        ar = (ArrayList<PayWaybean>) getIntent().getSerializableExtra("array");
        binding.recyclerView.setAdapter(new FeilvAboutAdapter(ar, null));
    }
}
