package com.itboye.cardmanage.ui.mine;
import com.itboye.cardmanage.R;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class MyTranslationModel extends BaseViewModel {
    public ObservableField<String> handingFee = new ObservableField<>();
    public ObservableField<Drawable> icon = new ObservableField<>();

    public MyTranslationModel(@NonNull Application application) {
        super(application);
        handingFee.set(("1000.00<br />手续费（元）"));
        icon.set(application.getResources().getDrawable(R.drawable.ic_changjianwenti));
    }
}
