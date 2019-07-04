package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.home.CardManageActivity;
import com.itboye.cardmanage.ui.mine.*;
import com.itboye.cardmanage.util.UserUtil;
import com.itboye.cardmanage.web.WebActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.KLog;

public class MineFragmentModel extends BaseViewModel {

    public ObservableField<String> nickname = new ObservableField<>(UserUtil.getUserInfo().getNickname());
    public ObservableField<String> mobile = new ObservableField<>(UserUtil.getUserInfo().getMobile());
    public ObservableField<String> headUrl = new ObservableField<>(UserUtil.getUserInfo().getAvatar());
    public ObservableField<String> authStatus = new ObservableField<>("未认证");
    public ObservableField<Drawable> vipRes = new ObservableField<>();

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
            headUrl.set("http://admin.361fit.cn/img/logo@2x.05fe4930.png");
            KLog.v("头像地址===" + headUrl.get());
            if (UserUtil.getUserInfo().getGrade_id().equals("1")) {
                //普通会员ic_vip_normal
                vipRes.set(getApplication().getResources().getDrawable(R.drawable.ic_vip_normal));
            } else if (UserUtil.getUserInfo().getGrade_id().equals("2")) {
                //VIP会员
                vipRes.set(getApplication().getResources().getDrawable(R.drawable.ic_vip));
            }
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
                bundle.putString("url", Global.H5URL+Global.CONTACT_CUSTOMER);
                bundle.putString("title", "联系客服");
                startActivity(WebActivity.class, bundle);
                break;
            case NORMAL_PROBLEM:
                bundle.putString("url", Global.H5URL+Global.NORMAL_PROBLEM);
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
