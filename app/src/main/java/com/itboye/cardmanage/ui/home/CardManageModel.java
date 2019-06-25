package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import com.itboye.cardmanage.R;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class CardManageModel extends BaseViewModel {

    public TextView tab1;
    public TextView tab2;
    public int type = 0;
    private int index;
    public ObservableInt selectIndex = new ObservableInt(1);

    public CardManageModel(@NonNull Application application) {
        super(application);

    }

    public void finishClick() {
        finish();
    }

    public void switchTitle(int type) {
        this.index = type;
        switch (type) {
            case 0://支付卡
                //选中第一个tab
                tab1.setBackgroundDrawable((getApplication().getResources().getDrawable(R.drawable.tab_left_select_style_bg_green)));
                tab2.setBackgroundDrawable((getApplication().getResources().getDrawable(R.drawable.tab_right_select_style_bg_white)));
                tab1.setTextColor(ContextCompat.getColor(getApplication(), R.color.white));
                tab2.setTextColor(ContextCompat.getColor(getApplication(), R.color.red));
//                if (fragment1 == null) {
//                    fragment1 = WagesBillsFragment.newInstance("$index", "")
//                    fragmentTransaction!!.add(R.id.frameLayout_wagesbills, fragment1)
//                } else {
//                    fragmentTransaction!!.show(fragment1)
//                }
                break;
            case 1://结算卡

                tab1.setBackgroundDrawable((getApplication().getResources().getDrawable(R.drawable.tab_left_select_style_bg_white)));
                tab2.setBackgroundDrawable((getApplication().getResources().getDrawable(R.drawable.tab_right_select_style_bg_green)));
                tab2.setTextColor(ContextCompat.getColor(getApplication(), R.color.white));
                tab1.setTextColor(ContextCompat.getColor(getApplication(), R.color.red));
                break;
        }
        selectIndex.set(type);
    }

    //添加卡
    public void addCard() {
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        bundle.putInt("type", type);
        startActivity(AddCardActivity.class, bundle);
    }
}
