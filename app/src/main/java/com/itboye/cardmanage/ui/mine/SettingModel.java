package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import com.itboye.cardmanage.ui.SplashActivity;
import com.itboye.cardmanage.util.UserUtil;
import com.itboye.cardmanage.web.WebActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class SettingModel extends BaseViewModel {
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
                bundle.putString("url", "");
                startActivity(WebActivity.class, bundle);
                break;//关于我们
            case CHECK_UPDATE:
                break;//检查更新
            case CLEAN_CACHE:
                break;//清理缓存
            case CURRENT_VERSION:
                break;//当前版本
            case LOGIN_OUT:
                UserUtil.clearUserInfo();
                startActivity(SplashActivity.class);
                break;
        }
    }
}
