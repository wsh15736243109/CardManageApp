package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class AddCardModel extends BaseViewModel {
    public ObservableField<String> cardOwner=new ObservableField<>("");
    public ObservableField<String> idnumber=new ObservableField<>("");
    public AddCardModel(@NonNull Application application) {
        super(application);
    }

    public void submit(){
        //确认添加卡
    }
}
