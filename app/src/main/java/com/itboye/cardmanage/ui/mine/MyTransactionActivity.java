package com.itboye.cardmanage.ui.mine;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.DatePicker;
import com.afollestad.materialdialogs.MaterialDialog;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.MyTranslationAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.TranslationBean;
import com.itboye.cardmanage.databinding.ActivityMyTranslationBinding;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.widget.TimePickerFragment;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

import java.util.ArrayList;
import java.util.Timer;

public class MyTransactionActivity extends BaseMVVMActivity<ActivityMyTranslationBinding, MyTranslationModel> {


    private MaterialDialog dialog;
    String month;
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
        binding.rvTranslation.setLayoutManager(new LinearLayoutManager(this));
        binding.rvTranslation.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MyTranslationAdapter(ar);
        binding.rvTranslation.setAdapter(adapter);

        setRightClickListener(() -> {

            TimePickerFragment newFragment = new TimePickerFragment(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                }
            }, 0, 0, 0);
            newFragment.getDatePicker().setCalendarViewShown(false);
            newFragment.getDatePicker().setSpinnersShown(true);
            newFragment.show();
//            MaterialDialog.Builder builder = MaterialDialogUtils.showDateDialog(this, "选择时间", "嗯嗯");
//            dialog = builder.show();
        });
        myTranslationRecord();
    }

    void myTranslationRecord() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).translationRecord(month, "by_CbOrder_querySimple"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                viewModel.showDialog();
            }
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
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
