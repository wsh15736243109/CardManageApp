package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.RepaymentListAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityRepaymentPlanBinding;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class RepaymentPlanActivity extends BaseMVVMActivity<ActivityRepaymentPlanBinding, RepaymentPlanModel> {


    private RepaymentListAdapter adapter;
    List<CardManageModel> repaymentList = new ArrayList<>();
    int pageIndex = 1;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_repayment_plan;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //查询计划列表
        binding.rvRepaymentPlan.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRepaymentPlan.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new RepaymentListAdapter(repaymentList, new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {

            }

            @Override
            public void onItemClick(int position, Object item) {
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                startActivity(RepaymentDetailActivity.class, bundle);
            }
        });

        getRepaymentData();
        binding.rvRepaymentPlan.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getRepaymentData() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryRepaymentPlan(pageIndex + "", "by_CbPlan_query"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                repaymentList.clear();
                repaymentList.addAll((ArrayList<CardManageModel>) o);
                repaymentList.add(new CardManageModel());
                repaymentList.add(new CardManageModel());
                repaymentList.add(new CardManageModel());

                adapter.notifyDataSetChanged();
                if (repaymentList.isEmpty() || repaymentList.size() <= 0) {
                    ToastUtils.showShort("暂无还款计划");
                }
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }
}
