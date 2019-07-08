package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.ui.mine.RepaymentDetailActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class HomeRepaymentModel extends BaseViewModel {

    public ObservableField<String> no_plan_label = new ObservableField<>("用APP还信用卡<br />用APP还信用卡信用卡额度立即恢复   轻松不逾期");
    public ObservableField<String> repaymentStatus = new ObservableField<>("立即还款");
    public ObservableField<String> repayCreateTime = new ObservableField<>("计划开始时间<br />");
    public ObservableField<String> repaidAmount = new ObservableField<>("已还金额<br /><font color='red'>￥5000</font>");
    public ObservableField<String> days = new ObservableField<>("周期<br />10");
    public ObservableField<String> remainDays = new ObservableField<>("剩余8期");
    public ObservableField<Integer> type = new ObservableField<>(View.GONE);
    public ObservableField<Integer> no_plan_label_visible = new ObservableField<>(View.GONE);

    public int typeValue = 0;

    public HomeRepaymentModel(@NonNull Application application) {
        super(application);
    }

    public void changeStatus() {
        this.type.set(typeValue == 0 ? View.INVISIBLE : View.VISIBLE);
        no_plan_label_visible.set(typeValue == 0 ? View.VISIBLE : View.GONE);
        repaymentStatus.set(typeValue == 0 ? "添加还款" : "查看计划");
    }

    public void btnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", typeValue);
        startActivity(RepaymentDetailActivity.class, bundle);
//        switch (typeValue) {
//            case 0://去还款
//                startActivity(AddRe.class);
//                break;
//            case 1://去添加计划
//                startActivity(RepaymentDetailActivity.class);
//                break;
//        }
    }
}
