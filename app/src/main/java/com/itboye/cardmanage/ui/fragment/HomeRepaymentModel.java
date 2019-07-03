package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class HomeRepaymentModel extends BaseViewModel {

    public ObservableField<String> no_plan_label = new ObservableField<>("用APP还信用卡<br />用APP还信用卡信用卡额度立即恢复   轻松不逾期");
    public ObservableField<String> repaymentStatus = new ObservableField<>("立即还款");
    public ObservableField<Integer> type = new ObservableField<>(View.INVISIBLE);
    public ObservableField<Integer> no_plan_label_visible = new ObservableField<>(View.GONE);

    public int typeValue = 0;

    public HomeRepaymentModel(@NonNull Application application) {
        super(application);
    }

    public void changeStatus() {
        this.type.set(typeValue == 1 ? View.INVISIBLE : View.VISIBLE);
        no_plan_label_visible.set(typeValue == 1 ? View.VISIBLE : View.GONE);
        repaymentStatus.set(typeValue == 1 ? "添加还款" : "立即还款");
    }
}
