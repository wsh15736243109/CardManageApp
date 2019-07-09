package com.itboye.cardmanage.ui.home;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityAuthMobileBinding;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AuthMobileActivity extends BaseMVVMActivity<ActivityAuthMobileBinding, AuthMobileModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_auth_mobile;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.bankId = getIntent().getStringExtra("bank_id");
        viewModel.order_code = getIntent().getStringExtra("order_code");
        viewModel.type = getIntent().getIntExtra("type", 1);
        viewModel.phone.set(getIntent().getStringExtra("phone"));
        viewModel.sendAuthCode(false);

    }


}
