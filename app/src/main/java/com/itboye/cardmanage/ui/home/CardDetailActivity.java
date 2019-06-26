package com.itboye.cardmanage.ui.home;

import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityCardDetailBinding;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.widget.TimePickerFragment;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.ToastUtils;

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
        binding.titleBar.getTvRight().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //解除绑定
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).unbindCard(viewModel.getDetailModel().getId(), "by_UserBankCard_unbind"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        showDialog("解绑中");
                    }
                }, new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        ToastUtils.showShort("" + msg);
                        finish();
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
