package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class CardListModel extends BaseViewModel {



    public String preStoreCardIds;
    public String creditCardIds;
    public CardListModel(@NonNull Application application) {
        super(application);
    }


    public void add() {
        //添加

    }
}
