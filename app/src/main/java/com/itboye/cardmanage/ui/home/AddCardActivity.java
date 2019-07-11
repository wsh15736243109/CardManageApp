package com.itboye.cardmanage.ui.home;

import android.Manifest;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.databinding.ActivityAddCardBinding;
import com.itboye.cardmanage.util.GalleryUtil;
import com.itboye.cardmanage.util.GlideUtil;
import com.itboye.cardmanage.widget.TimePickerFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import me.goldze.mvvmhabit.utils.ToastUtils;

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
                RxPermissions rxPermissions = new RxPermissions(AddCardActivity.this);
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                openLibrary(101);
                            } else {
                                ToastUtils.showShort("拍照权限被拒绝");
                            }
                        });
            }
        });
        binding.ivScan.setVisibility(View.GONE);
        binding.ivScan.setOnClickListener(view -> {
            RxPermissions rxPermissions = new RxPermissions(AddCardActivity.this);
            rxPermissions.request(Manifest.permission.CAMERA)
                    .subscribe(aBoolean -> {
                        if (aBoolean) {
                            scanCard();
                        } else {
                            ToastUtils.showShort("拍照权限被拒绝");
                        }
                    });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CardIOActivity.canReadCardWithCamera()) {
            ToastUtils.showShort("Scan a credit card with card.io");
        } else {
            ToastUtils.showShort("Enter credit card information");
        }
    }

    private void scanCard() {
        Intent scanIntent = new Intent(AddCardActivity.this, CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)
                .putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)//去除水印
                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true)//去除键盘
                .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "zh-Hans")//设置提示为中文
                .putExtra("debug_autoAcceptResult", true);

        startActivityForResult(scanIntent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 101) && data != null
                && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            CreditCard result = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
            if (result != null) {
                ToastUtils.showShort("Card number: " + result.cardNumber);
            }
        }
    }

    //选择图片
    private void openLibrary(int requestCode) {
        GalleryUtil.galleryConfig(this, new IHandlerCallBack() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(List<String> photoList) {
                String path = photoList.get(0);
                GlideUtil.display(AddCardActivity.this, path, binding.ivBankHold);
                viewModel.uploadImage(path, requestCode);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onError() {

            }
        });
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

}
