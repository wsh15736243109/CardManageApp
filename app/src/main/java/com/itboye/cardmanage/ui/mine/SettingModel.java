package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.SplashActivity;
import com.itboye.cardmanage.util.CacheUtil;
import com.itboye.cardmanage.util.UserUtil;
import com.itboye.cardmanage.web.WebActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class SettingModel extends BaseViewModel {

    public ObservableField<String> versionName = new ObservableField<>("");
    public ObservableField<String> cacheData = new ObservableField<>("");

    public SettingModel(@NonNull Application application) {
        super(application);
    }

    public void goToAc(MineClickType mineClickType) {
        Bundle bundle = new Bundle();
        switch (mineClickType) {
            case PERSON_DATA:
                startActivity(PersonDataActivity.class);
                break;//个人资料
            case UPDATE_PASSWORD:
                startActivity(UpdatePasswordActivity.class);
                break;//修改密码
            case FEEDBACK:
                startActivity(FeedbackActivity.class);
                break;//用户反馈
            case ABOUT_US:
                bundle.putString("title", "关于我们");
                bundle.putString("url", Global.H5URL + Global.ABOUT_US);
                startActivity(WebActivity.class, bundle);
                break;//关于我们
            case CHECK_UPDATE:
                bundle.putString("title", "版本更新");
                bundle.putString("url", Global.H5URL + Global.DOWNLOAD_APP);
                startActivity(WebActivity.class, bundle);
                break;//检查更新
            case CLEAN_CACHE://清理缓存
                CacheUtil.clearAllCache(getApplication());
                cacheData.set("0K");
                ToastUtils.showShort("缓存已清理");
                break;
            case CURRENT_VERSION:
                break;//当前版本
            case LOGIN_OUT:
                UserUtil.clearUserInfo();
                startActivity(SplashActivity.class);
                break;
        }
    }
}
