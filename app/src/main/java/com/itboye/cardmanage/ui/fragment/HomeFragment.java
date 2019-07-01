package com.itboye.cardmanage.ui.fragment;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.BannerAdapter;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.adapter.RepaymentListAdapter;
import com.itboye.cardmanage.base.BaseLazyFragment;
import com.itboye.cardmanage.bean.BannerBean;
import com.itboye.cardmanage.bean.HomeBean;
import com.itboye.cardmanage.bean.NoticeBean;
import com.itboye.cardmanage.databinding.FragmentHomeBinding;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.ui.mine.RepaymentDetailActivity;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.itboye.cardmanage.util.SizeUtils.dip2px;

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
    private ArrayList<CardManageModel> repaymentList = new ArrayList<>();
    private HomeBean bannerBean;

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
//        getAuthDetail();
        initRepaymentAdater();
        getRepaymentPlan();
        getHomeData();
        //为RecyclerView增加分割线，水平和垂直方向都有。增加分割线值比如为32。
//        RecycleGridDivider decoration = new RecycleGridDivider(1);
//        binding.rvTop.addItemDecoration(decoration);
    }

    private void getHomeData() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getBannersAndNotice("by_AppComb_index"), viewModel.getLifecycleProvider(), disposable -> {
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                bannerBean = (HomeBean) o;
                BannerAdapter bannerAdapter = new BannerAdapter(bannerBean.getApply_card(), new OnMyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, Object item) {

                    }

                    @Override
                    public void onItemClick(int position, Object item) {

                    }
                });
                binding.rvTop.setAdapter(bannerAdapter);//顶部数据

                BannerAdapter bannerAdapter2 = new BannerAdapter(bannerBean.getLend(), new OnMyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, Object item) {

                    }

                    @Override
                    public void onItemClick(int position, Object item) {

                    }
                });
                binding.rvLoan.setAdapter(bannerAdapter2);//中部数据
                BannerAdapter bannerAdapter3 = new BannerAdapter(bannerBean.getCarousel(), new OnMyItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position, Object item) {

                    }

                    @Override
                    public void onItemClick(int position, Object item) {

                    }
                });
                binding.rvMoreCard.setAdapter(bannerAdapter3); //底部数据
                //公告
                setViewFliperItem();
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
//                dismissDialog();
            }
        });
    }

    ArrayList<ImageView> cursorImageView = new ArrayList<>();

    private void initRepaymentAdater() {
        ArrayList<Fragment> arr = new ArrayList<>();
        arr.add(new HomeRepaymentFragment());
        arr.add(new HomeRepaymentFragment());
        arr.add(new HomeRepaymentFragment());
        for (int i = 0; i < arr.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundDrawable(getResources().getDrawable(i == 0 ? R.drawable.item_cursor_select : R.drawable.item_cursor_unselect));
            int dp = dip2px(getActivity(), 2);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(getActivity(), 9), dip2px(getActivity(), 2));
            layoutParams.setMargins(dp, dp, dp, dp);
            binding.llRepaymentCursor.addView(imageView, layoutParams);
            cursorImageView.add(imageView);
        }
        binding.vpRepayment.setAdapter(new FragmentPageAdapter(getFragmentManager(), arr, null));
        binding.vpRepayment.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                for (int i1 = 0; i1 < cursorImageView.size(); i1++) {
                    if (i == i1) {
                        cursorImageView.get(i1).setBackgroundDrawable(getResources().getDrawable(R.drawable.item_cursor_select));
                    } else {
                        cursorImageView.get(i1).setBackgroundDrawable(getResources().getDrawable(R.drawable.item_cursor_unselect));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void getRepaymentPlan() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryRepaymentPlan(pageIndex + "", "by_CbPlan_query"), viewModel.getLifecycleProvider(), disposable -> {
        }/*viewModel.showDialog()*/, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                repaymentList.clear();
                repaymentList.addAll((ArrayList<CardManageModel>) o);
                if (repaymentList.isEmpty() || repaymentList.size() <= 0) {
                    ToastUtils.showShort("暂无还款计划");
                }
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
//                dismissDialog();
            }
        });
    }


//    public void getAuthDetail() {
//        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).noticeMessage(pageIndex + "", "10", "by_Message_querySystemNoticeMessage"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
//            @Override
//            public void accept(Disposable disposable) throws Exception {
//
//            }
//        }, new ApiDisposableObserver() {
//            @Override
//            public void onResult(Object o, String msg, int code) {
//                noticeBean = (NoticeBean) o;
//                setViewFliperItem();
//            }
//
//            @Override
//            public void onError(int code, String msg) {
//
//            }
//
//            @Override
//            public void dialogDismiss() {
//
//            }
//        });
//    }

    @Override
    public void initViewObservable() {

    }

    private void setViewFliperItem() {
        for (int i = 0; i < bannerBean.getNotice().size(); i++) {
            View item = LayoutInflater.from(getActivity()).inflate(R.layout.item_notice, null);
            LinearLayout parent = item.findViewById(R.id.flipper_item);
//            parent.setBackgroundResource(noticeBean[i]);
            TextView text1 = item.findViewById(R.id.tv1);
            text1.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_platform_announcement, 0, 0, 0);
            text1.setCompoundDrawablePadding(12);
            text1.setText(bannerBean.getNotice().get(i).getContent());
            binding.viewFlipper.addView(item);
        }
        binding.viewFlipper.startFlipping();
    }
}
