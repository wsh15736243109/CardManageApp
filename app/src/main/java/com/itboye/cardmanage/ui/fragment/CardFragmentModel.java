package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CardFragmentModel extends BaseViewModel {
    public ObservableField<String> url = new ObservableField<>(Global.WEBURL+"card");
//    //给RecyclerView添加items
//    public final ObservableList<String> observableList = new ObservableArrayList<>();
//    public ObservableField<List<String>> hobbies = new ObservableField<>();
//    //给RecyclerView添加ItemBinding
//    public final ItemBinding<String> itemBinding = ItemBinding.of(BR.item, R.layout.item_card);

    public CardFragmentModel(@NonNull Application application) {
        super(application);
//        ArrayList<String> ar = new ArrayList<>();
////        observableList.add("two");
////        observableList.add("one");
////        observableList.add("three");
//
//        ar.add("two");
//        ar.add("one");
//        ar.add("three");
//        hobbies.set(ar);
    }


}
