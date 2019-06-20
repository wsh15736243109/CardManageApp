package com.itboye.cardmanage.ui.home;

import android.databinding.Observable;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityCardDetailBinding;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.widget.TimePickerFragment;

public class CardDetailActivity extends BaseMVVMActivity<ActivityCardDetailBinding, CardDetailModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_card_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.setDetailModel((CardManageModel) getIntent().getSerializableExtra("model"));
        viewModel.billDateCLick.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showDate(1);
            }
        });
        viewModel.repaymentDateCLick.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showDate(2);
            }
        });
        viewModel.validDateCLick.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showDate(4);
            }
        });
    }

    private void showDate(int type) {
        TimePickerFragment newFragment = new TimePickerFragment(this, (datePicker, i, i1, i2) -> {
            String date = i + "" + i1 + "" + i2;
            if (type == 1) {
                viewModel.billDate.set(date);
            } else if (type == 2) {
                viewModel.repaymentDate.set(date);
            } else if (type == 4) {
                viewModel.validDate.set(date);
            }
        }, 0, 0, 0);
        newFragment.getDatePicker().setCalendarViewShown(false);
        newFragment.getDatePicker().setSpinnersShown(true);
        newFragment.show();
    }
}
