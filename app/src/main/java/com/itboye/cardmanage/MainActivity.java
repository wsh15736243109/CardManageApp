package com.itboye.cardmanage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityMainBinding;
import com.itboye.cardmanage.ui.fragment.CardFragment;
import com.itboye.cardmanage.ui.fragment.HomeFragment;
import com.itboye.cardmanage.ui.fragment.LoanFragment;
import com.itboye.cardmanage.ui.fragment.MineFragment;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

import java.util.ArrayList;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding, MainModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        NavigationController navigationController = binding.tab.custom()
                .addItem(newItem(R.mipmap.ic_home_select, R.mipmap.ic_home_select, "首页"))
                .addItem(newItem(R.mipmap.ic_card_default, R.mipmap.ic_bancard_selected, "办卡"))
                .addItem(newItem(R.mipmap.ic_daikuan_default, R.mipmap.ic_loan_selected, "贷款"))
                .addItem(newItem(R.mipmap.ic_mine_default, R.mipmap.ic_mine_selected, "我的"))
                .build();

        binding.viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(),
                new ArrayList<Fragment>() {{
                    add(new HomeFragment());
                    add(new CardFragment());
                    add(new LoanFragment());
                    add(new MineFragment());
                }}, null));
//        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.setOffscreenPageLimit(4);
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(binding.viewPager);
    }

    /**
     * 创建一个Item
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(getResources().getColor(R.color.hint_color_a3a3a3));
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.text_main_DD2824));
        return normalItemView;
    }

}
