package com.itboye.cardmanage.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.base.BaseLazyFragment;
import com.itboye.cardmanage.bean.NoticeBean;
import com.itboye.cardmanage.databinding.FragmentHomeBinding;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends BaseLazyFragment<FragmentHomeBinding, HomeFragmentModel> {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Fragment> mFragmentList;
    List<String> mTitles;
    FragmentPageAdapter mFragmentPageAdapter;

    int pageIndex = 1;
    private NoticeBean noticeBean;

    public HomeFragment() {
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
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        getAuthDetail();
        //为RecyclerView增加分割线，水平和垂直方向都有。增加分割线值比如为32。
//        RecycleGridDivider decoration = new RecycleGridDivider(1);
//        binding.rvTop.addItemDecoration(decoration);
    }


    public void getAuthDetail() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).noticeMessage(pageIndex + "", "10", "by_Message_querySystemNoticeMessage"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                noticeBean = (NoticeBean) o;
                setViewFliperItem();
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {

            }
        });
    }

    @Override
    public void initViewObservable() {

    }

    private void setViewFliperItem() {
        for (int i = 0; i < noticeBean.getList().size(); i++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.item_notice, null);
            LinearLayout parent = item.findViewById(R.id.flipper_item);
//            parent.setBackgroundResource(noticeBean[i]);
            TextView text1 =  item.findViewById(R.id.tv1);
            text1.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_platform_announcement,0,0,0);
            text1.setCompoundDrawablePadding(12);
            text1.setText(noticeBean.getList().get(i).getContent());
            binding.viewFlipper.addView(item);
        }
        binding.viewFlipper.startFlipping();
    }
}
