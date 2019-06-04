package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.support.annotation.NonNull;

import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;

import com.itboye.cardmanage.ui.home.CardManageActivity;
import com.itboye.cardmanage.ui.home.ReceiveMoneyActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class HomeFragmentModel extends BaseViewModel {
    public HomeFragmentModel(@NonNull Application application) {
        super(application);
    }

    public void goToAc(MineClickType mineClickType) {
        switch (mineClickType) {
            case MY_TRANSLATION:
                ToastUtils.showShort("点击了我的交易");
                break;
            case REPAYMENT_PLAN:
                ToastUtils.showShort("点击了还款计划");
                break;
        }
    }

    public void cardManage(int type) {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getSMSCode("15736243109", "1", "86", "by_SecurityCode_createAndSend"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg) {
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
//        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).loginByPwd(
//                "by_UserLoginSession_loginByMobilePassword", "15736243109", "1", "android", "android", "123456"),
//                getLifecycleProvider(), disposable -> showDialog(),
//
//                new ApiDisposableObserver() {
//                    @Override
//                    public void onResult(Object o) {
//                        ToastUtils.showShort("ooo==" + o);
//                    }
//
//                    @Override
//                    public void dialogDismiss() {
//                        dismissDialog();
//                    }
//                });
        if (type == 0) {
            startActivity(ReceiveMoneyActivity.class);

        } else {
            startActivity(CardManageActivity.class);
        }
    }

    /*
    *  when (index) {
            0 -> {
                //选中第一个tab
                tabitem_1!!.setBackgroundDrawable((resources.getDrawable(R.drawable.tab_left_select_style_bg_white)))
                tabitem_2!!.setBackgroundDrawable((resources.getDrawable(R.drawable.tab_right_select_style_bg_green)))
                tabitem_2!!.setTextColor(resources.getColor(R.color.white))
                tabitem_1!!.setTextColor(resources.getColor(R.color.main_blue))
                if (fragment1 == null) {
                    fragment1 = WagesBillsFragment.newInstance("$index", "")
                    fragmentTransaction!!.add(R.id.frameLayout_wagesbills, fragment1)
                } else {
                    fragmentTransaction!!.show(fragment1)
                }
            }
            1 -> {
                //选中第二个tab
                tabitem_1!!.setBackgroundDrawable((resources.getDrawable(R.drawable.tab_left_select_style_bg_green)))
                tabitem_2!!.setBackgroundDrawable((resources.getDrawable(R.drawable.tab_right_select_style_bg_white)))
                tabitem_1!!.setTextColor(resources.getColor(R.color.white))
                tabitem_2!!.setTextColor(resources.getColor(R.color.main_blue))
                if (fragment2 == null) {
                    fragment2 = WagesBillsFragment.newInstance("$index", "")
                    fragmentTransaction!!.add(R.id.frameLayout_wagesbills, fragment2)
                } else {
                    fragmentTransaction!!.show(fragment2)
                }
            }*/
}
