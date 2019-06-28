package com.itboye.cardmanage.ui.home;

import android.databinding.Observable;
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


    int selecctIndex = 0;

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
        selecctIndex = getIntent().getIntExtra("index", 0);
        viewModel.type = getIntent().getIntExtra("type", 0);//是否跳转选择支付卡
        binding.viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(),
                new ArrayList<Fragment>() {{
                    add(PayOrSettlementCardFragment.newInstance("1", viewModel.type + ""));
                    add(PayOrSettlementCardFragment.newInstance("2", viewModel.type + ""));
                }}, null));
        //自动适配ViewPager页面切换
        binding.viewPager.setCurrentItem(selecctIndex);
        viewModel.switchTitle(selecctIndex);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewModel.switchTitle(i);
                selecctIndex = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    public void initViewObservable() {
        viewModel.selectIndex.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                binding.viewPager.setCurrentItem(viewModel.selectIndex.get());
            }
        });
    }
}
