package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class CustomerServiceModel extends BaseViewModel {
    public CustomerServiceModel(@NonNull Application application) {
        super(application);
    }

    public void goToAc(MineClickType mineClickType) {
        switch (mineClickType) {
            case MY_TRANSLATION:
                startActivity(MyTransactionActivity.class);
                break;
            case REPAYMENT_PLAN:
                startActivity(RepaymentPlanActivity.class);
                break;
            case CONTACT_CUSTOMER_SERVICE:
                ToastUtils.showShort("联系客服");
                break;
            case NORMAL_PROBLEM:
                ToastUtils.showShort("常见问题");
                break;
            case CERTIFICATION_DATA:
                ToastUtils.showShort("认证资料");
                break;
            case SETTING:
                ToastUtils.showShort("设置");
                break;
        }
    }
}
