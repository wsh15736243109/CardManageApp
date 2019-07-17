package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.UserAuthDetailBean;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.ui.home.CardManageActivity;
import com.itboye.cardmanage.ui.mine.*;
import com.itboye.cardmanage.util.UserUtil;
import com.itboye.cardmanage.web.WebActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class MineFragmentModel extends BaseViewModel {

    public ObservableField<String> nickname = new ObservableField<>(UserUtil.getUserInfo().getNickname());
    public ObservableField<String> mobile = new ObservableField<>(UserUtil.getUserInfo().getMobile());
    public ObservableField<String> headUrl = new ObservableField<>(UserUtil.getUserInfo().getAvatar());
    public ObservableField<String> authStatus = new ObservableField<>("未认证");
    public ObservableField<Drawable> vipRes = new ObservableField<>();
    public ObservableField<Drawable> authRes = new ObservableField<>();
    public ObservableField<Drawable> authLeftRes = new ObservableField<>();
    public ObservableField<Integer> authColor = new ObservableField<>();

    public MineFragmentModel(@NonNull Application application) {
        super(application);
    }

    UIChangeObser uc = new UIChangeObser();

    public void initAuthStatus() {
        if (UserUtil.getUserInfo() != null) {
            setAuth(UserUtil.getUserInfo().getId_validate());
            nickname.set(UserUtil.getUserInfo().getNickname());
            mobile.set(UserUtil.getUserInfo().getMobile());
            headUrl.set(UserUtil.getUserInfo().getAvatar());
            if (UserUtil.getUserInfo().getGrade_id().equals("1")) {
                //普通会员ic_vip_normal
                vipRes.set(getApplication().getResources().getDrawable(R.drawable.ic_vip_normal));
            } else if (UserUtil.getUserInfo().getGrade_id().equals("2")) {
                //VIP会员
                vipRes.set(getApplication().getResources().getDrawable(R.drawable.ic_vip));
            }
        } else {
            setAuth(0);
        }
    }

    private void setAuth(int value) {
        switch (value) {
            case 0:
                authRes.set(getApplication().getResources().getDrawable(R.drawable.rect_bg_none_border_red));
                authLeftRes.set(getApplication().getResources().getDrawable(R.drawable.ic_mine_unauth));
                authStatus.set("未认证");
                authColor.set(ContextCompat.getColor(getApplication(), R.color.red));
                break;
            case 1:
                authRes.set(getApplication().getResources().getDrawable(R.drawable.rect_bg_none_border_yellow));
                authLeftRes.set(getApplication().getResources().getDrawable(R.drawable.ic_verified));
                authStatus.set("已认证");
                authColor.set(ContextCompat.getColor(getApplication(), R.color.yellow_E8AB27));
                break;
        }
    }

    public class UIChangeObser {
        public ObservableBoolean photo = new ObservableBoolean(false);
    }

    public void goToAc(MineClickType mineClickType) {
        Bundle bundle = new Bundle();
        switch (mineClickType) {
            case MY_WALLET://我的钱包
                startActivity(MyWalletActivity.class, bundle);
                break;
            case MY_TRANSLATION:
                bundle.putInt("type", 1);
                startActivity(MyTransactionActivity.class, bundle);
                break;
            case REPAYMENT_PLAN:
                startActivity(RepaymentPlanActivity.class);
                break;
            case CONTACT_CUSTOMER_SERVICE:
                bundle.putString("url", Global.H5URL + Global.CONTACT_CUSTOMER);
                bundle.putString("title", "联系客服");
                startActivity(WebActivity.class, bundle);
                break;
            case NORMAL_PROBLEM:
                bundle.putString("url", Global.H5URL + Global.NORMAL_PROBLEM);
                bundle.putString("title", "常见问题");
                startActivity(WebActivity.class, bundle);
                break;
            case CERTIFICATION_DATA:
                uc.photo.set(!uc.photo.get());
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

    public void getAuthInfo() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryAuthInfo(
                UserUtil.getUserInfo().getId() + "",
                "by_UserIdCard_info"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        UserAuthDetailBean userAuthDetailBean = (UserAuthDetailBean) o;
                        int vertify = userAuthDetailBean.getVerify();
                        switch (vertify) {
                            case 1:
                                //认证成功
                                startActivity(AuthDataOpenActivity.class);
                                break;
                            case 2:
                                //审核中
                                ToastUtils.showShort("您的资料正在审核中");
                                break;
                            case 0:
                                toAuthActivity();
                                break;
                        }
                    }

                    @Override
                    public void onError(int code, String msg) {
                        if (msg.equalsIgnoreCase("not exists")) {
                            toAuthActivity();
                        }
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }
}
