package com.itboye.cardmanage.ui.home;

import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityOpenBinding;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;

public class Open extends BaseMVVMActivity<ActivityOpenBinding, OpenModel> {

    private int type;
    private Disposable mSubscription;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_open;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type", 1);
        viewModel.bank_id = getIntent().getStringExtra("bank_id");
        viewModel.phone = getIntent().getStringExtra("phone");
        viewModel.type = type;
        binding.titleBar.setTitle(type == 1 ? "开通代扣" : "开通代付");

        mSubscription = RxBus.getDefault().toObservable(Integer.class)
                .subscribe(s -> finish());
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }
}
