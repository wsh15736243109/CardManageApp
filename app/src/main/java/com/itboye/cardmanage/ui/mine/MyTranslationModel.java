package com.itboye.cardmanage.ui.mine;
import com.itboye.cardmanage.R;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

import java.util.ListIterator;

public class MyTranslationModel extends BaseViewModel {
    public ObservableField<String> handingFee = new ObservableField<>("");
    public ObservableField<Drawable> icon = new ObservableField<>();
    public ObservableField<String> yusuan=new ObservableField<>("");
    public ObservableField<String> jieyu=new ObservableField<>("");

    public MyTranslationModel(@NonNull Application application) {
        super(application);
        icon.set(application.getResources().getDrawable(R.drawable.ic_changjianwenti));
    }
}
