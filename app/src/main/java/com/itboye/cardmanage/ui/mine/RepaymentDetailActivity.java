package com.itboye.cardmanage.ui.mine;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.adapter.RepaymentCardListAdapter;
import com.itboye.cardmanage.adapter.RepaymentPreCardListAdapter;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.bean.RepaymentDetailBean;
import com.itboye.cardmanage.databinding.ActivityRepaymentDetailBinding;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.SizeUtils;
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

    CardManageModel model;

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
        initRepaymentCardListAdapter();//还款计划卡adapter
        initCreditCardListAdapter();//预存资金卡adapter
        if (type == 0) {
            setTitle("添加还款计划");
        } else {
            setRightText("删除");
            setTitle("计划详情");
            model = (CardManageModel) getIntent().getSerializableExtra("model");
            viewModel.id = model.getId();
            setRepaymentDetail();
        }
        viewModel.planType.set(type == 0 ? View.VISIBLE : View.GONE);
        viewModel.type.set(type == 0);
        registerRx();
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
            AlertDialog alertDialog = new AlertDialog.Builder(RepaymentDetailActivity.this, R.style.AlertDialogStyle).create();
            View view1 = View.inflate(RepaymentDetailActivity.this, R.layout.dialog_delete, null);
//            ColorDrawable dw = new ColorDrawable(0x00000000);
//            view1.setBackgroundDrawable(dw);
            TextView btn_cancel = view1.findViewById(R.id.btn_cancel);
            TextView btn_ok = view1.findViewById(R.id.btn_ok);
            TextView tv_content = view1.findViewById(R.id.tv_content);
            ImageView iv_close = view1.findViewById(R.id.iv_close);
            tv_content.setText(Html.fromHtml("<b>删除计划</b><br />删除后将在次日生效"));
            alertDialog.setView(view1);
            btn_cancel.setOnClickListener(view2 -> alertDialog.dismiss());
            btn_ok.setOnClickListener(view22 -> {
                deletePlan();
                alertDialog.dismiss();
            });
            iv_close.setOnClickListener(view22 -> alertDialog.dismiss());
            alertDialog.show();

            //这种设置宽高的方式也是好使的！！！-- show 前调用，show 后调用都可以！！！
            view1.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
                int height = v.getHeight();     //此处的view 和v 其实是同一个控件
                int contentHeight = view1.getHeight();
                int needHeight = (int) (SizeUtils.getScreenHeight(getApplicationContext()) / 2.5);
                int width = SizeUtils.getScreenWidth(getApplicationContext()) / 2;

//                if (contentHeight > needHeight) {
                //注意：这里的 LayoutParams 必须是 FrameLayout的！！
                view1.setLayoutParams(new FrameLayout.LayoutParams(width,
                        needHeight));
//                }
            });
        });
        binding.tvRestart.setVisibility(type == 1 ? View.VISIBLE : View.INVISIBLE);
    }

    private void setRepaymentDetail() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).queryPlanInfo(viewModel.id, "by_CbPlan_info"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                RepaymentDetailBean repaymentDetailBean = (RepaymentDetailBean) o;
                viewModel.setRepaymentDetail(repaymentDetailBean);
                //显示还款计划卡
                cardList.addAll(repaymentDetailBean.get_card());
                //显示预存资金卡
                cardList2.add(repaymentDetailBean.getPrestore_card_info());

                repaymentCardAdapter.notifyDataSetChanged();
                preCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int code, String msg) {

            }

            @Override
            public void dialogDismiss() {
                viewModel.dismissDialog();
            }
        });
    }

    private void deletePlan() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).deleteCdPlan("", "by_CbPlan_delete"), viewModel.getLifecycleProvider(), disposable -> viewModel.showDialog(), new ApiDisposableObserver() {
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
    }

    //预存资金卡
    private void initCreditCardListAdapter() {
        preCardAdapter = new RepaymentPreCardListAdapter(cardList2, type, new OnMyItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object item) {

            }

            @Override
            public void onItemClick(int position, Object item) {
                //更换预存资金卡
                viewModel.addRepaymentPlan(2, 1);
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
                            cardList2.clear();
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
                    getFee();

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
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getRepaymentFee(money, cardList.size() /*+ cardList2.size()*/, days, "by_CbPlan_getFee"), viewModel.getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) {
//                viewModel.showDialog();
            }
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                String value = String.format("%.2f", ((Double) o));
                viewModel.fee.set(value + "<br />手续费(元)");
                viewModel.feeValue = value;
                try {
                    viewModel.pre_store_money = String.format("%.2f", Double.parseDouble(viewModel.feeValue) + Double.parseDouble(viewModel.amount.get()) / Double.parseDouble(viewModel.days.get()));
                    viewModel.yucun.set(viewModel.pre_store_money + "<br />预存（元）");
                } catch (Exception e) {
                    viewModel.pre_store_money = null;
                    viewModel.yucun.set("0<br />预存（元）");

                }
            }

            @Override
            public void onError(int code, String msg) {
                viewModel.feeValue = "0";
                viewModel.fee.set("0<br />手续费(元)");
                viewModel.yucun.set("0<br />预存（元）");
                viewModel.pre_store_money = null;
            }

            @Override
            public void dialogDismiss() {
//                viewModel.dismissDialog();
            }
        });
    }
}
