package com.itboye.cardmanage.ui.home;

import android.app.DatePickerDialog;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.databinding.ActivityAddCardBinding;
import com.itboye.cardmanage.widget.TimePickerFragment;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddCardActivity extends BaseMVVMActivity<ActivityAddCardBinding, AddCardModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_add_card;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        viewModel.type = getIntent().getIntExtra("type", 0);
        ToastUtils.showShort(viewModel.type+"");
        viewModel.setAddCardType(viewModel.type);
    }

    @Override
    public void initViewObservable() {

        viewModel.ui.searchBranch.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.branchBankBeanArrayList != null && viewModel.branchBankBeanArrayList.size() > 0) {
                    //展示所有搜索到的支行
                    showBranchBankList(viewModel.branchBankBeanArrayList);
                }
            }
        });

        viewModel.ui.showDate.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                showDate();
            }
        });
    }

    private void showDate() {
        TimePickerFragment newFragment = new TimePickerFragment(this, (datePicker, i, i1, i2) -> {
            if (viewModel.chooseType == 0) {
                viewModel.bill_date.set(i + "" + i1 + "" + i2);
            } else {
                viewModel.repayment_date.set(i + "" + i1 + "" + i2);
            }
        }, 0, 0, 0);
        newFragment.getDatePicker().setCalendarViewShown(false);
        newFragment.getDatePicker().setSpinnersShown(true);
        newFragment.show();
    }

    private void showBranchBankList(ArrayList<BranchBankBean> branchBankBeanArrayList) {
        //初始化map
        Map<String, String> keyValuePair = new HashMap<>();
        for (int i = 0; i < branchBankBeanArrayList.size(); i++) {
            BranchBankBean branchBankBean = branchBankBeanArrayList.get(i);
            keyValuePair.put("name", branchBankBean.getName());
        }
        ListView listView = new ListView(this);
        List<Map<String, String>> list = new ArrayList<>();
        list.add(keyValuePair);
        listView.setAdapter(new SimpleAdapter(this, list, android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1}));
        AlertDialog alert = new AlertDialog.Builder(this).setView(listView).setCancelable(false).create();
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            viewModel.branchNo = branchBankBeanArrayList.get(i).getPaysysbank();
            viewModel.branchBankName.set(branchBankBeanArrayList.get(i).getName());
            alert.dismiss();
        });
        alert.show();
    }

}
