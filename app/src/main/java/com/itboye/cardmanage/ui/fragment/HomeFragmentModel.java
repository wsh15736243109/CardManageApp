package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.support.annotation.NonNull;

import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;

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

    public void cardManage() {

        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getSMSCode("15736243109", "1", "86","by_SecurityCode_createAndSend"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o) {
                        ToastUtils.showShort("ooo==" + o);
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
//        startActivity(CardManageActivity.class);
    }
}
