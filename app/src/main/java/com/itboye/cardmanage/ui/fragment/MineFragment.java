package com.itboye.cardmanage.ui.fragment;


import android.Manifest;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.MainActivity;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.base.BaseLazyFragment;
import com.itboye.cardmanage.databinding.FragmentMineBinding;
import com.itboye.cardmanage.util.UserUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends BaseLazyFragment<FragmentMineBinding, MineFragmentModel> {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Fragment> mFragmentList;
    List<String> mTitles;
    FragmentPageAdapter mFragmentPageAdapter;


    public MineFragment() {
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
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
    }

    @Override
    public void onUserInvisible() {

    }

    @Override
    public void initParam() {

    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getUserAuthDetail();
        viewModel.initAuthStatus();
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_mine;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        binding.srRefresh.setOnRefreshListener(refreshLayout -> {
            ((MainActivity) getActivity()).getUserAuthDetail();
            new Handler().postDelayed(() -> refreshLayout.finishRefresh(), 3000);
        });
    }

    @Override
    public void initViewObservable() {
        viewModel.uc.photo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                RxPermissions rxPermissions = new RxPermissions(getActivity());
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                viewModel.getAuthInfo();
                            } else {
                                ToastUtils.showShort("拍照权限被拒绝");
                            }
                        });
            }

        });
    }

}
