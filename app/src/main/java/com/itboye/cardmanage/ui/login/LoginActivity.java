package com.itboye.cardmanage.ui.login;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.os.Bundle;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityLoginBinding;
import com.itboye.cardmanage.util.TimeCount;
import me.goldze.mvvmhabit.BR;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class LoginActivity extends BaseMVVMActivity<ActivityLoginBinding, LoginModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;//ViewModelProviders.of(this).get(LoginModel.class);
    }

    @Override
    public void initData() {
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.registerCountDown.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                beginCountDownTimeRegister();
            }
        });
    }

    private void beginCountDownTimeRegister() {
        //开始倒计时
        TimeCount timeCount = new TimeCount(2 * 1000, 1 * 1000, binding.tvGetCode);
        timeCount.start();
    }
}
