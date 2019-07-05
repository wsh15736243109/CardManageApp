package com.itboye.cardmanage.ui.home;

import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.databinding.ActivityAddCardBinding;
import com.itboye.cardmanage.util.GlideUtil;
import com.itboye.cardmanage.widget.TimePickerFragment;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageLoader;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.*;

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
        viewModel.index = getIntent().getIntExtra("index", 0);
        viewModel.setAddCardType(viewModel.index);
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


        viewModel.ui.choosePic.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                openLibrary(101);
            }
        });
    }

    //选择图片
    private void openLibrary(int requestCode) {
        ImageConfig imageConfig
                = new ImageConfig.Builder((ImageLoader) (context, path, imageView) -> GlideUtil.display(context, path, imageView))
                .steepToolBarColor(getApplication().getResources().getColor(R.color.white))
                .titleBgColor(getApplication().getResources().getColor(R.color.white))
                .titleSubmitTextColor(getApplication().getResources().getColor(R.color.white))
                .titleTextColor(getApplication().getResources().getColor(R.color.red))
                // (截图默认配置：关闭    比例 1：1    输出分辨率  500*500)
//                .crop(2, 1, 1000, 500)
                // 开启单选   （默认为多选）
                .requestCode(requestCode)
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ImageSelector/Pictures")
                .build();
        ImageSelector.open(this, imageConfig);   // 开启图片选择器
    }

    private void showDate() {

        int year = Calendar.getInstance().get(Calendar.YEAR), month = Calendar.getInstance().get(Calendar.MONTH), dayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        switch (viewModel.chooseType) {
            case 0://账单日
                year = -1;
                month = -1;
                break;
            case 1://还款日
                year = -1;
                month = -1;
                break;
            case 2://有效期
                dayOfMonth = -1;
                break;
        }
        TimePickerFragment newFragment = new TimePickerFragment(this, (datePicker, i, i1, i2) -> {
            if (viewModel.chooseType == 0) {
                viewModel.bill_date.set(i2 + "");
            } else if (viewModel.chooseType == 1) {
                viewModel.repayment_date.set(i2 + "");
            } else {
                viewModel.validDate.set(i + "" + (i1 + 1 < 10 ? "0" + (i1 + 1) : (i1 + 1) + ""));
            }
        }, year, month, dayOfMonth);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            String path = pathList.get(0);
            GlideUtil.display(this, path, binding.ivBankHold);
            viewModel.uploadImage(path, requestCode);
        }
    }
}
