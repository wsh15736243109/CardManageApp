package com.itboye.cardmanage.ui.login;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityLoginBinding;
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

}
