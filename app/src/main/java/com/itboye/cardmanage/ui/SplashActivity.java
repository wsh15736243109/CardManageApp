package com.itboye.cardmanage.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;
import me.goldze.mvvmhabit.base.BaseActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏状态栏
        return R.layout.activity_splash;
    }

    @Override
    public SplashModel initViewModel() {
        return ViewModelProviders.of(this).get(SplashModel.class);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.initTimer();
    }
}
