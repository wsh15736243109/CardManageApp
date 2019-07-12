package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.MyTranslationAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.RepaymentDetailBean;
import com.itboye.cardmanage.bean.TranslationBean;
import com.itboye.cardmanage.databinding.ActivityMyTranslationBinding;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.TimeUtils;
import com.itboye.cardmanage.widget.TimePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class MyTransactionActivity extends BaseMVVMActivity<ActivityMyTranslationBinding, MyTranslationModel> {


    private MaterialDialog dialog;
    String month;
    int year;
    int type;
    int pageIndex;
    String id;
    ArrayList<TranslationBean> ar = new ArrayList<>();
    private MyTranslationAdapter adapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_my_translation;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type", 0);
        id = getIntent().getStringExtra("id");
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = getMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
        binding.rvTranslation.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTranslation.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MyTranslationAdapter(ar);
        binding.rvTranslation.setAdapter(adapter);
        year = Calendar.getInstance().get(Calendar.YEAR);
        setRightClickListener(() -> {
            TimePickerFragment newFragment = new TimePickerFragment(this, (datePicker, i, i1, i2) -> {
                year = i;
                month = getMonth(i1 + 1);
                myTranslationRecord();
            }, year, Integer.parseInt(month), -1);
            newFragment.getDatePicker().setCalendarViewShown(false);
            newFragment.getDatePicker().setSpinnersShown(true);
            newFragment.setCanceledOnTouchOutside(false);
            newFragment.show();
//            MaterialDialog.Builder builder = MaterialDialogUtils.showDateDialog(this, "选择时间", "嗯嗯");
//            dialog = builder.show();
        });
        binding.llHead.setVisibility(type == 1 ? View.GONE : View.VISIBLE);
        if (type == 0) {
            // 获取计划账户详情
            getPlanDetailPlanInfo();
        } else
            myTranslationRecord();
    }

    private void getPlanDetailPlanInfo() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryPlanDetailPlanInfo(id, pageIndex, "by_CbPlan_planDetail"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                RepaymentDetailBean model = (RepaymentDetailBean) o;
                viewModel.handingFee.set(model.getFee() + "<br />手续费");
                viewModel.yusuan.set(model.getBalance() + "<br />预算");
                viewModel.jieyu.set(model.getBalance() + "");
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

    private String getMonth(int month) {
        return TimeUtils.parseTime(TimeUtils.getStrFormat(month + "", 2), "MM", "MM");
    }

    void myTranslationRecord() {
        binding.tvBillDate.setText(year + "年" + month + "月账单");
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).translationRecord(year + month, "by_CbOrder_querySimple"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ar.clear();
                ar.addAll((ArrayList<TranslationBean>) o);
                adapter.notifyDataSetChanged();
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
