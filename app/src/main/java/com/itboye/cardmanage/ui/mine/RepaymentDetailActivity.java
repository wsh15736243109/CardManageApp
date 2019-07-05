package com.itboye.cardmanage.ui.mine;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.icu.text.TimeZoneFormat;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.RepaymentCardListAdapter;
import com.itboye.cardmanage.adapter.RepaymentPreCardListAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityRepaymentDetailBinding;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.bus.RxSubscriptions;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;

public class RepaymentDetailActivity extends BaseMVVMActivity<ActivityRepaymentDetailBinding, RepaymentDetailModel> {

    private int type;
    private Disposable mSubscription;

    ArrayList<CardManageModel> cardList = new ArrayList<>();
    ArrayList<CardManageModel> cardList2 = new ArrayList<>();
    private RepaymentCardListAdapter repaymentCardAdapter;
    private RepaymentPreCardListAdapter preCardAdapter;

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_repayment_detail;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        type = getIntent().getIntExtra("type", 0);
        if (type == 0) {
            setTitle("添加还款计划");
        } else {
            setRightText("删除");
            setTitle("计划详情");
        }
        registerRx();
        initRepaymentCardListAdapter();//还款计划卡adapter
        initCreditCardListAdapter();//预存资金卡adapter
        binding.etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getFee();
            }
        });
        binding.etDays.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getFee();
            }
        });
        binding.titleBar.getTvRight().setOnClickListener(view -> {
            //删除计划
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).deleteCdPlan("", "by_CbPlan_delete"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) {
                    viewModel.showDialog();
                }
            }, new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    ToastUtils.showShort(msg);
                }

                @Override
                public void onError(int code, String msg) {

                }

                @Override
                public void dialogDismiss() {
                    viewModel.dismissDialog();
                }
            });
        });
    }

    //预存资金卡
    private void initCreditCardListAdapter() {
        preCardAdapter = new RepaymentPreCardListAdapter(cardList2, type, new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {

            }

            @Override
            public void onItemClick(int position, Object item) {

            }
        });
        binding.rvCreditCard.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCreditCard.setAdapter(preCardAdapter);

    }

    //还款计划卡
    private void initRepaymentCardListAdapter() {
        repaymentCardAdapter = new RepaymentCardListAdapter(cardList, type, new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {

            }

            @Override
            public void onItemClick(int position, Object item) {
                viewModel.addRepaymentPlan(2, 1);
            }
        });
        binding.rvRepaymentCard.setLayoutManager(new LinearLayoutManager(this));
        binding.rvRepaymentCard.setAdapter(repaymentCardAdapter);

    }

    private void registerRx() {
        mSubscription = RxBus.getDefault().toObservable(Bundle.class)
                .subscribe(s -> {
                    ArrayList<CardManageModel> temp = (ArrayList<CardManageModel>) s.getSerializable("array");
                    int usage = s.getInt("usage");
                    StringBuffer stringBuffer1 = new StringBuffer();
                    StringBuffer stringBuffer2 = new StringBuffer();
                    for (CardManageModel model : temp) {
                        if (usage == 1) {
                            stringBuffer1.append(model.getId() + ",");
                            cardList.add(model);
                        } else {
                            stringBuffer2.append(model.getId() + ",");
                            cardList2.add(model);
                        }
                    }
                    repaymentCardAdapter.notifyDataSetChanged();
                    preCardAdapter.notifyDataSetChanged();
                    if (usage == 1) {
                        viewModel.creditCardIds = stringBuffer1.substring(0, stringBuffer1.length() - 1);
                    } else {
                        viewModel.preStoreCardIds = stringBuffer2.substring(0, stringBuffer2.length() - 1);
                    }
                });
        //将订阅者加入管理站
        RxSubscriptions.add(mSubscription);
    }

    public void getFee() {
        int days = 0;
        double money = 0;

        if (!binding.etDays.getText().toString().isEmpty()) {
            days = Integer.parseInt(binding.etDays.getText().toString());
        }
        if (!binding.etAmount.getText().toString().isEmpty()) {
            money = Double.parseDouble(binding.etAmount.getText().toString());
        }

        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getRepaymentFee(money * 100, cardList.size(), days, "by_CbPlan_getFee"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
//                viewModel.showDialog();
            }
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                viewModel.fee.set("" + (o));
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
//                viewModel.dismissDialog();
            }
        });
    }
}
