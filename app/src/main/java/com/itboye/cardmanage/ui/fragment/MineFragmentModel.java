package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.mine.CustomerServiceActivity;
import com.itboye.cardmanage.ui.mine.MyTransactionActivity;
import com.itboye.cardmanage.ui.mine.RepaymentPlanActivity;
import com.itboye.cardmanage.ui.mine.SettingActivity;
import com.itboye.cardmanage.web.WebActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MineFragmentModel extends BaseViewModel {
    public MineFragmentModel(@NonNull Application application) {
        super(application);
    }

    public void goToAc(MineClickType mineClickType) {
        Bundle bundle = new Bundle();
        switch (mineClickType) {
            case MY_TRANSLATION:
                startActivity(MyTransactionActivity.class);
                break;
            case REPAYMENT_PLAN:
                startActivity(RepaymentPlanActivity.class);
                break;
            case CONTACT_CUSTOMER_SERVICE:
                startActivity(CustomerServiceActivity.class);
                break;
            case NORMAL_PROBLEM:
                bundle.putString("url", "http://www.baidu.com");
                bundle.putString("title", "常见问题");
                startActivity(WebActivity.class, bundle);
                break;
            case CERTIFICATION_DATA:
                ToastUtils.showShort("认证资料");
                break;
            case SETTING:
                startActivity(SettingActivity.class);
                break;
        }
    }
}
