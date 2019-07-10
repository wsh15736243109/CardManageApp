package com.itboye.cardmanage.ui.fragment;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.ui.mine.RepaymentDetailActivity;
import com.itboye.cardmanage.util.TimeUtils;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class HomeRepaymentModel extends BaseViewModel {

    public ObservableField<String> no_plan_label = new ObservableField<>("用APP还信用卡<br />用APP还信用卡信用卡额度立即恢复   轻松不逾期");
    public ObservableField<String> repaymentStatus = new ObservableField<>("");
    public ObservableField<String> repayCreateTime = new ObservableField<>("");
    public ObservableField<String> repaidAmount = new ObservableField<>("");
    public ObservableField<String> days = new ObservableField<>("");
    public ObservableField<String> remainDays = new ObservableField<>("");
    public ObservableField<Integer> type = new ObservableField<>(View.GONE);
    public ObservableField<Integer> type2 = new ObservableField<>(View.GONE);
    public ObservableField<Integer> no_plan_label_visible = new ObservableField<>(View.GONE);

    public int typeValue = 0;
    public CardManageModel model;

    public HomeRepaymentModel(@NonNull Application application) {
        super(application);
    }

    public void changeStatus() {
        this.type.set(typeValue == 0 ? View.INVISIBLE : View.VISIBLE);
        this.type2.set(View.INVISIBLE);
        no_plan_label_visible.set(typeValue == 0 ? View.VISIBLE : View.GONE);
        repayCreateTime.set("计划开始时间<br />" + (TimeUtils.timeFormat(model.getCreate_time() * 1000, "yyyy/MM/dd")));
        repaidAmount.set("已还金额<br /><font color='red'>" + model.getRepay_total_money() / 100 + "</font>");
        days.set("周期<br />" + model.getDays());
        remainDays.set("剩余" + (model.getDays() - model.getRepay_count() + "期"));
        repaymentStatus.set(typeValue == 0 ? "添加还款" : "查看计划");
    }

    public void btnClick() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", typeValue);
        bundle.putSerializable("model", model);
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
