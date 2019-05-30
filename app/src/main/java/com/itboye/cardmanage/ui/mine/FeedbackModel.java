package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
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
        ToastUtils.showShort("已提交");

    }
}
