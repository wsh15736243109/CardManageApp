package com.itboye.cardmanage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.FragmentPageAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.UserAuthDetailBean;
import com.itboye.cardmanage.bean.UserInfoBean;
import com.itboye.cardmanage.databinding.ActivityMainBinding;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.ui.SplashActivity;
import com.itboye.cardmanage.ui.fragment.CardFragment;
import com.itboye.cardmanage.ui.fragment.HomeFragment;
import com.itboye.cardmanage.ui.fragment.LoanFragment;
import com.itboye.cardmanage.ui.fragment.MineFragment;
import com.itboye.cardmanage.util.UserUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem;
import me.majiajie.pagerbottomtabstrip.item.NormalItemView;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding, MainModel> {

    private Disposable mSubscription;
    private NavigationController navigationController;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        navigationController = binding.tab.custom()
                .addItem(newItem(R.mipmap.ic_home_default, R.mipmap.ic_home_select, "首页"))
                .addItem(newItem(R.mipmap.ic_card_default, R.mipmap.ic_bancard_selected, "办卡"))
                .addItem(newItem(R.mipmap.ic_daikuan_default, R.mipmap.ic_loan_selected, "贷款"))
                .addItem(newItem(R.mipmap.ic_mine_default, R.mipmap.ic_mine_selected, "我的"))
                .build();

        binding.viewPager.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(),
                new ArrayList<Fragment>() {{
                    add(new HomeFragment());
                    add(new CardFragment());
                    add(new LoanFragment());
                    add(new MineFragment());
                }}, null));
//        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.setOffscreenPageLimit(4);
        navigationController.setSelect(0);
        //自动适配ViewPager页面切换
        navigationController.setupWithViewPager(binding.viewPager);
        binding.viewPager.setCurrentItem(0);
        binding.titlebar.getLayRoot().setVisibility(View.GONE);
        binding.titlebar.setBarBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.text_main_DD2824));
        binding.titlebar.setStatusUI(false);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                binding.titlebar.setVisibility(View.VISIBLE);
                binding.titlebar.getLayRoot().setVisibility(View.VISIBLE);
                binding.titlebar.setStatusUI(true);
                binding.titlebar.setBarBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                if (i == 0) {
                    binding.titlebar.setStatusUI(false);
                    binding.titlebar.getLayRoot().setVisibility(View.GONE);
                    binding.titlebar.setBarBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.text_main_DD2824));
                    binding.titlebar.setTitle("");
                    binding.titlebar.setStatusBarHeight(0);
                } else if (i == 1) {
                    binding.titlebar.setTitle("办卡");
                } else if (i == 2) {
                    binding.titlebar.setTitle("贷款");
                } else if (i == 3) {
                    binding.titlebar.setTitle("");
                    binding.titlebar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        registerRxBus();
        //获取认证信息
        getUserAuthDetail();
    }

    public void getUserAuthDetail() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryAuthInfo(
                UserUtil.getUserInfo() == null ? "" : UserUtil.getUserInfo().getId() + "",
                "by_UserIdCard_info"),
                viewModel.getLifecycleProvider(), disposable -> {
                }, /*viewModel.showDialog(),*/

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        UserAuthDetailBean user = (UserAuthDetailBean) o;
                        UserInfoBean userInfoBean = UserUtil.getUserInfo();
                        if (user != null && userInfoBean != null) {
                            userInfoBean.setId_validate(user.getVerify());
                            userInfoBean.setName(user.getName());
                            userInfoBean.setId_no(user.getId_no());
                            UserUtil.saveUser(userInfoBean);
                            viewModel.setAuthStatus();//MainActivity的认证状态
                            //我的页面认证状态
                            ((MineFragment) getSupportFragmentManager().getFragments().get(3)).viewModel.initAuthStatus();
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                    }

                    @Override
                    public void dialogDismiss() {
//                        dismissDialog();
                    }
                });
    }

    boolean isAr = false;

    private void registerRxBus() {
        mSubscription = RxBus.getDefault().toObservable(Integer.class)
                .subscribe(s -> {
                    if (s == 1111) {
                        RxBus.getDefault().removeAllStickyEvents();
                        if (!isAr) {
                            Log.e("", "登录过期------");
                            UserUtil.clearUserInfo();//重新登录
                            startAc(SplashActivity.class);
                            isAr = true;
                            finish();
                        }
                        return;
                    }
                    navigationController.setSelect(s);
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }

    /**
     * 创建一个Item
     */
    private BaseTabItem newItem(int drawable, int checkedDrawable, String text) {
        NormalItemView normalItemView = new NormalItemView(this);
        normalItemView.initialize(drawable, checkedDrawable, text);
        normalItemView.setTextDefaultColor(getResources().getColor(R.color.hint_color_a3a3a3));
        normalItemView.setTextCheckedColor(getResources().getColor(R.color.text_main_DD2824));
        return normalItemView;
    }

}
