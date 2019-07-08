package com.itboye.cardmanage.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseLazyFragment;
import com.itboye.cardmanage.databinding.ItemHomeRepaymentHuankuanCardBinding;

public class HomeRepaymentFragment extends BaseLazyFragment<ItemHomeRepaymentHuankuanCardBinding, HomeRepaymentModel> {

    int type;

    public static String ARG_PARAM1 = "type";
    public static String ARG_PARAM2 = "type2";

    public HomeRepaymentFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeRepaymentFragment newInstance(int param1, String param2) {
        HomeRepaymentFragment fragment = new HomeRepaymentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        binding.tvRemainDays2.setVisibility(View.VISIBLE);
        binding.tvRemainDays.setVisibility(View.INVISIBLE);
        viewModel.typeValue = getArguments().getInt(ARG_PARAM1);
        viewModel.changeStatus();
    }

    @Override
    public void initViewObservable() {

    }
}
