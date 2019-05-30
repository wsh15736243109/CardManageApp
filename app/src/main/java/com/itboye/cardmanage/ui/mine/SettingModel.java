package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.interfaces.MineClickType;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class SettingModel extends BaseViewModel {
    public SettingModel(@NonNull Application application) {
        super(application);
    }

    public void goToAc(MineClickType mineClickType){
        switch (mineClickType) {

        }
    }
}
