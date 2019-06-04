package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class CardDetailModel extends BaseViewModel {
    public ObservableField<String> cardNumber = new ObservableField<>("");

    public CardDetailModel(@NonNull Application application) {
        super(application);
    }

}
