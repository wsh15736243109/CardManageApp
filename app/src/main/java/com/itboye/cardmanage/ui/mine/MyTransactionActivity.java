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
import com.itboye.cardmanage.widget.TimePickerFragment;
import me.goldze.mvvmhabit.utils.MaterialDialogUtils;

import java.util.ArrayList;
import java.util.Timer;

public class MyTransactionActivity extends BaseMVVMActivity<ActivityMyTranslationBinding, MyTranslationModel> {


    private MaterialDialog dialog;

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
        binding.rvTranslation.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        ArrayList<TranslationBean> ar = new ArrayList<>();
        ar.add(new TranslationBean());
        ar.add(new TranslationBean());
        ar.add(new TranslationBean());
        binding.rvTranslation.setAdapter(new MyTranslationAdapter(ar));

        setRightClickListener(() -> {

            TimePickerFragment newFragment = new TimePickerFragment(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                }
            },0,0,0);
            newFragment.getDatePicker().setCalendarViewShown(false);
            newFragment.getDatePicker().setSpinnersShown(true);
            newFragment.show();
//            MaterialDialog.Builder builder = MaterialDialogUtils.showDateDialog(this, "选择时间", "嗯嗯");
//            dialog = builder.show();
        });
    }
}
