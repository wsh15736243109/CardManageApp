package com.itboye.cardmanage.ui.home;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.CardManageItemAdapter;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.base.BaseLazyFragment;
import com.itboye.cardmanage.databinding.FragmentPayOrSettlementBinding;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PayOrSettlementCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PayOrSettlementCardFragment extends BaseLazyFragment<FragmentPayOrSettlementBinding, PayOrSettlementModel> {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Fragment> mFragmentList;
    List<String> mTitles;
    FragmentPageAdapter mFragmentPageAdapter;
    private String cardUse = "3";
    int pageIndex = 1;

    public PayOrSettlementCardFragment() {
        // Required empty public constructor
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
    public static PayOrSettlementCardFragment newInstance(String param1, String param2) {
        PayOrSettlementCardFragment fragment = new PayOrSettlementCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onFirstUserInvisible() {
    }

    @Override
    public void onUserVisible() {
        ToastUtils.showShort(cardUse);
    }

    @Override
    public void onUserInvisible() {

    }

    @Override
    public void initParam() {
        cardUse = getArguments().getString(ARG_PARAM1);
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_pay_or_settlement;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider_shape_10);
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(drawable);
        binding.recyclerView.addItemDecoration(decoration);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        viewModel.adapter = new CardManageItemAdapter(viewModel.observableList, new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {
                Bundle bundle = new Bundle();
                switch (view.getId()) {
                    case R.id.item_card_manage_daikou://代扣
                        bundle.putInt("type", 0);
                        startActivity(Open.class, bundle);
                        break;
                    case R.id.item_card_manage_daifu://代付
                        bundle.putInt("type", 1);
                        startActivity(Open.class, bundle);
                        break;
                    case R.id.item_card_manage_master:
                        break;//设为主卡
                }
            }

            @Override
            public void onItemClick(int position, Object item) {

            }
        });
        viewModel.adapter.bindToRecyclerView(binding.recyclerView);
        viewModel.adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("model", viewModel.observableList.get(position));
            startActivity(CardDetailActivity.class, bundle);
        });
        viewModel.getCardList(cardUse, pageIndex);
    }

    @Override
    public void initViewObservable() {

    }

}
