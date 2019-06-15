package com.itboye.cardmanage.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityCardManageBinding;

import java.util.ArrayList;

public class CardManageActivity extends BaseMVVMActivity<ActivityCardManageBinding, CardManageModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_card_manage;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.tab1 = binding.tabitem1;
        viewModel.tab2 = binding.tabitem2;
        viewModel.switchTitle(0);

        binding.viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(),
                new ArrayList<Fragment>() {{
                    add(PayOrSettlementCardFragment.newInstance("1", ""));
                    add(PayOrSettlementCardFragment.newInstance("2", ""));
                }}, null));
        //自动适配ViewPager页面切换
        binding.viewPager.setCurrentItem(0);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewModel.switchTitle(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
