package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.UserUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AddCardModel extends BaseViewModel {
    public ObservableField<String> cardOwner = new ObservableField<>("www");//持卡人姓名
    public ObservableField<String> idnumber = new ObservableField<>("500228199501155555");//身份证号
    public ObservableField<String> cardNumber = new ObservableField<>("62106656956568963263");//银行卡号
    public ObservableField<String> cardNumberRe = new ObservableField<>("62106656956568963263");//确认银行卡
    public ObservableField<String> bankName = new ObservableField<>("杭州联合银行");//开户行名称
    public ObservableField<String> branchBankName = new ObservableField<>("下沙支行");//支行名称
    public ObservableField<String> reservedPhone = new ObservableField<>("15736243111");//预留手机号

    String bank_card_id = "";

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
        //添加结算卡
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).addSettlementCard(UserUtil.getUserInfo().getId() + "", bank_card_id, "by_Zmf_bindDebitCard"), getLifecycleProvider(), new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                showDialog();
            }
        }, new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg) {
                ToastUtils.showShort(msg + "====" + o);
            }

            @Override
            public void dialogDismiss() {
                dismissDialog();
            }
        });
    }
}
