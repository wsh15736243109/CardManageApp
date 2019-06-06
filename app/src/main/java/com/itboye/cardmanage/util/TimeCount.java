package com.itboye.cardmanage.util;
import com.itboye.cardmanage.R;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.itboye.cardmanage.app.App;

public class TimeCount extends CountDownTimer {


    private TextView tv;

    public TimeCount(long millisInFuture, long countDownInterval, TextView tv) {
        super(millisInFuture, countDownInterval);
        this.tv = tv;
    }


    @Override
    public void onFinish() {
        tv.setText(App.getInstance().getString(R.string.revalidation));
        tv.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv.setClickable(false);
        tv.setText(String.format(App.getInstance().getString(R.string.after), millisUntilFinished / 1000));

    }

}