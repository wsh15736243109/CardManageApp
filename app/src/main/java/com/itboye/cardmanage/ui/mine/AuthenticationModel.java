package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class AuthenticationModel extends BaseViewModel {

    public ObservableField<String> bankReservePhone = new ObservableField<>("");//银行预留手机
    public ObservableField<String> branchBankName = new ObservableField<>("");//支行名称
    public ObservableField<String> bankName = new ObservableField<>("");//银行名称
    public ObservableField<String> bankNumberAgain = new ObservableField<>("");//再次银行卡账号
    public ObservableField<String> bankNumber = new ObservableField<>("");//银行预留手机
    public ObservableField<String> realName = new ObservableField<>("");//姓名
    public ObservableField<String> idnumber = new ObservableField<>("");//身份证号

    public AuthenticationModel(@NonNull Application application) {
        super(application);
    }

    public void next() {
        ToastUtils.showShort("下一步");

    }
}
