package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.home.CardManageActivity;
import com.itboye.cardmanage.ui.mine.*;
import com.itboye.cardmanage.util.UserUtil;
import com.itboye.cardmanage.web.WebActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MineFragmentModel extends BaseViewModel {

    public ObservableField<String> nickname = new ObservableField<>(UserUtil.getUserInfo().getNickname());
    public ObservableField<String> mobile = new ObservableField<>(UserUtil.getUserInfo().getMobile());
    public ObservableField<String> headUrl = new ObservableField<>(UserUtil.getUserInfo().getAvatar());
    public ObservableField<String> authStatus = new ObservableField<>("未认证");

    public MineFragmentModel(@NonNull Application application) {
        super(application);
    }

    UIChangeObser uc = new UIChangeObser();

    public void initAuthStatus() {
        if (UserUtil.getUserInfo() != null) {
            authStatus.set(UserUtil.getUserInfo().getId_validate() == 1 ? "已认证" : "未认证");
            nickname.set(UserUtil.getUserInfo().getNickname());
            mobile.set(UserUtil.getUserInfo().getMobile());
            headUrl.set(UserUtil.getUserInfo().getAvatar());
        } else {
            authStatus.set("未认证");
        }
    }

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

    public void card(int index) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 1);
        bundle.putInt("index", index);
        startActivity(CardManageActivity.class, bundle);
    }

    public void toAuthActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        startActivity(AuthenticationActivity.class, bundle);
    }
}
