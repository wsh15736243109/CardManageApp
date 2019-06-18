package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;

public class PayOrSettlementModel extends BaseViewModel {

    public TextView tab1;
    public TextView tab2;

    public ObservableField<Integer> isEmpty = new ObservableField<>(View.GONE);

    public PayOrSettlementModel(@NonNull Application application) {
        super(application);

    }

    //添加卡
    public void addCard() {
        startActivity(AddCardActivity.class);
    }

    public void getCardList(String cardUse, int pageIndex) {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).cardList(cardUse, pageIndex + "", "10", "by_UserBankCard_query"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ArrayList<String> ar = (ArrayList<String>) o;
                if (ar != null && ar.size() > 0) {
                    isEmpty.set(View.GONE);
                } else {
                    isEmpty.set(View.VISIBLE);
                }
            }

            @Override
            public void onError(int code, String msg) {
                isEmpty.set(View.VISIBLE);
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }
}
