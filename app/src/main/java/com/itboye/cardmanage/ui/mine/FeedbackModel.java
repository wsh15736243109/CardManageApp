package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.UserUtil;

import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class FeedbackModel extends BaseViewModel {
    public ObservableField<String> suggestionValue = new ObservableField<>("");

    public FeedbackModel(@NonNull Application application) {
        super(application);
    }

    public void submitSuggestion() {
        if (suggestionValue.get().isEmpty()) {
            ToastUtils.showShort("请输入意见或建议");
            return;
        }
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).feedback(UserUtil.getUserInfo().getId() + "", suggestionValue.get(), "by_Suggest_create"), getLifecycleProvider(), new Consumer() {

            @Override
            public void accept(Object o) throws Exception {
                showDialog();
            }
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg) {
                ToastUtils.showShort(msg);
                finish();
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }
}
