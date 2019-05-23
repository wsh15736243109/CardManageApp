package com.itboye.cardmanage.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.itboye.cardmanage.MainActivity;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;
import com.itboye.cardmanage.ui.login.LoginModel;

public class SplashActivity extends BaseMVVMActivity<ActivitySplashBinding, LoginModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

//    @Override
//    public int initVariableId(Bundle savedInstanceState) {
//        return BR.viewModel;
//    }

    @Override
    public LoginModel initViewModel() {
        return ViewModelProviders.of(this).get(LoginModel.class);
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        Toast.makeText(this,"弹出",Toast.LENGTH_SHORT).show();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }
//        }, 1500);
    }
}
