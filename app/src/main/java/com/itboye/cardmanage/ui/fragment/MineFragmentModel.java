package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.mine.*;
import com.itboye.cardmanage.util.UserUtil;
import com.itboye.cardmanage.web.WebActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MineFragmentModel extends BaseViewModel {

    public ObservableField<String> nickname = new ObservableField<>(UserUtil.getUserInfo().getNickname());
    public ObservableField<String> headUrl = new ObservableField<>(UserUtil.getUserInfo().getAvatar());

    public MineFragmentModel(@NonNull Application application) {
        super(application);
        ToastUtils.showShort(headUrl.get());
        KLog.v("头像地址=" + UserUtil.getUserInfo().getAvatar());
        headUrl.set(UserUtil.getUserInfo().getAvatar());
    }

    UIChangeObser uc = new UIChangeObser();

    public class UIChangeObser {
        public ObservableBoolean photo = new ObservableBoolean(false);
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
                uc.photo.set(true);
                break;
            case SETTING:
                startActivity(SettingActivity.class);
                break;
        }
    }

    public void toAuthActivity() {
        startActivity(AuthenticationActivity.class);
    }
}
