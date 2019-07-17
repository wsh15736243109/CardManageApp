package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.bean.UserBalanceBean;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.ArrayList;

public class MyWalletModel extends BaseViewModel {

    public ObservableField<String> banlance = new ObservableField<>("账户余额<br />￥0");
    public ObservableField<String> withdrawAmount = new ObservableField<>("0");
    public ObservableField<String> arrivalBank = new ObservableField<>("到账结算卡");
    private String withdraw_card_id = "";
    private UserBalanceBean userBalanceBean;

    public MyWalletModel(@NonNull Application application) {
        super(application);
    }

    public void submit() {
        if (userBalanceBean == null) {
            ToastUtils.showShort("数据异常，请稍后或退出重试");
            return;
        }

        if (withdrawAmount.get().isEmpty()) {
            ToastUtils.showShort("请输入提现金额");
            return;
        }
        if (Double.parseDouble(withdrawAmount.get()) > userBalanceBean.getZmf_wallet()) {
            ToastUtils.showShort("账户余额不足");
            return;
        }
        if (Double.parseDouble(withdrawAmount.get()) < 10 || Double.parseDouble(withdrawAmount.get()) > 1000) {
            ToastUtils.showShort("提现金额不能低于10元或高于1000元");
            return;
        }
        //提交提现申请
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).createWithdrawOrder(withdrawAmount.get(), withdrawAmount.get() + "元提现", "by_CbOrder_createUserWithdrawOrder"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ToastUtils.showShort(o + "");
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

    public void getUserBalance() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getUserBalance("by_CbUserWallet_info"), getLifecycleProvider(), disposable -> showDialog("获取余额中"), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                userBalanceBean = (UserBalanceBean) o;
                banlance.set("账户余额<br />￥" + userBalanceBean.getZmf_wallet());
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

    //获取到账结算卡主卡
    public void getMasterArrivalCard() {
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).cardList("2", "1", "10", "by_UserBankCard_query"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
            @Override
            public void onResult(Object o, String msg, int code) {
                ArrayList<CardManageModel> ar = (ArrayList<CardManageModel>) o;
                if (ar != null) {
                    for (CardManageModel model : ar) {
                        if (model.getMaster() == 1) {
                            withdraw_card_id = model.getId();//结算卡账户id
                            arrivalBank.set(model.getBranch_bank());//结算卡账户
                            break;
                        }
                    }
                }
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
