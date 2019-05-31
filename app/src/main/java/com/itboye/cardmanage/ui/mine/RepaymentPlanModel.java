package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.LoanModel;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;

public class RepaymentPlanModel extends BaseViewModel {

    public ObservableArrayList<LoanModel> planList = new ObservableArrayList<>();

    public RepaymentPlanModel(@NonNull Application application) {
        super(application);
    }

    public OnItemBindClass itemBind2 = new OnItemBindClass<>().map(LoanModel.class, new OnItemBind<LoanModel>() {

        @Override
        public void onItemBind(ItemBinding itemBinding, final int position, LoanModel item) {
            itemBinding.clearExtras()
                    .set(BR.item, R.layout.item_repayment_plan)
                    .bindExtra(BR.listener, new OnMyItemClickListener<LoanModel>() {
                        @Override
                        public void onItemClick(int position232323, LoanModel item) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", 0);
                            startActivity(RepaymentDetailActivity.class, bundle);
                        }

                        @Override
                        public void onLongClick() {
                            ToastUtils.showShort(position + "item 长按了吗>>>>>>");
                        }
                    });
        }
    });

    //添加还款计划
    public void addRepaymentPlan() {
        planList.add(new LoanModel(getApplication()));
    }
}
