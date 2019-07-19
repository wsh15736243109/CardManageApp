package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.LoanModel;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

import java.util.ArrayList;
import java.util.List;

public class LoanFragmentModel extends BaseViewModel {

//    public ObservableField<String> url = new ObservableField<>(Global.WEBURL+"/index.php/web/lend");
    public ObservableField<String> url = new ObservableField<>(Global.WEBURL+"index.php/web/lend");

    public LoanFragmentModel(@NonNull Application application) {
        super(application);
        Log.v("OkHttp_R",url.get());
    }

}
