package com.itboye.cardmanage.ui;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.MainActivity;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.ui.login.LoginActivity;
import com.itboye.cardmanage.util.UserUtil;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class SplashModel extends BaseViewModel {

    public ObservableField<Drawable> res = new ObservableField<>();

    public SplashModel(@NonNull Application application) {
        super(application);
        res.set(application.getResources().getDrawable(R.drawable.ic_splash));
    }

    public void initTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(1500, 2000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (UserUtil.getUserInfo() != null) {
                    startActivity(MainActivity.class);
                } else {
                    startActivity(LoginActivity.class);
                }
                finish();
            }
        };
        countDownTimer.start();
    }
}
