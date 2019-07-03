package com.itboye.cardmanage.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseLazyFragment;
import com.itboye.cardmanage.databinding.ItemHomeRepaymentHuankuanCardBinding;

public class HomeRepaymentFragment extends BaseLazyFragment<ItemHomeRepaymentHuankuanCardBinding, HomeRepaymentModel> {

    int type;

    public HomeRepaymentFragment() {

    }

    @SuppressLint("ValidFragment")
    public HomeRepaymentFragment(int type) {
        this.type = type;
    }

    @Override
    public void onFirstUserInvisible() {

    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }

    @Override
    public void initParam() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.item_home_repayment_huankuan_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.typeValue = type;
        viewModel.changeStatus();
    }

    @Override
    public void initViewObservable() {

    }
}
