package com.itboye.cardmanage.ui.home;

import android.app.AlertDialog;
import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.DatePicker;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.retrofit.*;
import com.itboye.cardmanage.util.UserUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;

public class AddCardModel extends BaseViewModel {
    public ObservableField<String> cardOwner = new ObservableField<>("周波");//持卡人姓名
    public ObservableField<String> idnumber = new ObservableField<>("330327198407040039");//身份证号
    public ObservableField<String> cardNumber = new ObservableField<>("6222081202007602202");//银行卡号
    public ObservableField<String> cardNumberRe = new ObservableField<>("6222081202007602202");//确认银行卡
    public ObservableField<String> bankName = new ObservableField<>("中国工商银行");//开户行名称
    public ObservableField<String> branchBankName = new ObservableField<>("杭州");//支行名称
    public ObservableField<String> reservedPhone = new ObservableField<>("13858066033");//预留手机号

    public ObservableField<String> bill_date = new ObservableField<>("20201010");//账单日
    public ObservableField<String> repayment_date = new ObservableField<>("20201010");//还款日
    public String branchNo = "";
    public int type = 0;

    String bank_card_id = "";
    public ArrayList<BranchBankBean> branchBankBeanArrayList = new ArrayList<>();

    public AddCardModel(@NonNull Application application) {
        super(application);
    }

    public void submit() {
        //确认添加卡
        if (cardOwner.get().isEmpty()) {
            ToastUtils.showShort("请输入持卡人姓名");
            return;
        }
        if (idnumber.get().isEmpty()) {
            ToastUtils.showShort("请输入身份证号码");
            return;
        }
        if (cardNumber.get().isEmpty()) {
            ToastUtils.showShort("请输入银行卡卡号");
            return;
        }
        if (cardNumberRe.get().isEmpty()) {
            ToastUtils.showShort("请再次输入银行卡卡号");
            return;
        }
        if (cardNumber.get().equals(cardNumberRe)) {
            ToastUtils.showShort("两次银行卡卡号不一致");
            return;
        }
        if (bankName.get().isEmpty()) {
            ToastUtils.showShort("请输入开户行名称");
            return;
        }
        if (cardOwner.get().isEmpty()) {
            ToastUtils.showShort("请输入支行名称");
            return;
        }

        if (reservedPhone.get().isEmpty()) {
            ToastUtils.showShort("请输入银行卡预留手机号码");
            return;
        }
        if (type == 0) {
            //添加支付卡
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).addPaymentCard(cardNumber.get(), bankName.get(), reservedPhone.get(), cardNumber.get().substring(cardNumber.get().length() - 3), "20201010", bill_date.get(), repayment_date.get(), "by_UserBankCard_bindPayCard"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    ToastUtils.showShort(msg + "====" + o);
                }


                @Override
                public void onError(int code, String msg) {

                }

                @Override
                public void dialogDismiss() {
                    dismissDialog();
                }
            });
        } else if (type == 1) {
            //添加结算卡
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).addSettlementCard(cardNumber.get(), bankName.get(), reservedPhone.get(), "by_UserBankCard_bindDebitCard"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    ToastUtils.showShort(msg + "====" + o);
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

    UIChangeListener ui = new UIChangeListener();

    public class UIChangeListener {
        ObservableBoolean searchBranch = new ObservableBoolean(false);
        ObservableBoolean showDate = new ObservableBoolean(false);
    }


    //搜索支行信息
    public void branchBankInfoSearch() {
        if (branchBankName.get().isEmpty()) {
            ToastUtils.showShort("请输入支行关键词");
            return;
        }
        AppUtils.upload(RetrofitClient.getInstance().create(CardAPI.class).getBranchInfo(
                cardNumber.get(),
                branchBankName.get()),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        branchBankBeanArrayList = (ArrayList<BranchBankBean>) o;
                        ui.searchBranch.set(!ui.searchBranch.get());
                        ToastUtils.showShort(msg);
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

    //搜索银行卡关联的银行信息
    public void bankInfoSearch() {

    }

    public int chooseType = 0;

    public void showDate(int type) {
        chooseType = type;
        ui.showDate.set(!ui.showDate.get());
    }
}
