package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.home.CardManageActivity;
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

    public void cardManage(){
        startActivity(CardManageActivity.class);
    }
}
