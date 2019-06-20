package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.Observable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.model.CardManageModel;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class CardDetailModel extends BaseViewModel {
    public ObservableField<String> billDate = new ObservableField<>("");//账单日
    public ObservableField<String> repaymentDate = new ObservableField<>("");//还款日
    public ObservableField<String> securityCode = new ObservableField<>("");//安全码
    public ObservableField<String> validDate = new ObservableField<>("");//信用卡有效日期
    public ObservableField<String> singleLimit = new ObservableField<>("");//单笔限额
    public ObservableField<String> oneDayLimit = new ObservableField<>("");//单日限额

    public ObservableField<String> bankName = new ObservableField<>("");//银行名称
    public ObservableField<String> cardNo = new ObservableField<>("");//卡号
    public CardManageModel detailModel;

    public ObservableBoolean billDateCLick = new ObservableBoolean(false);
    public ObservableBoolean repaymentDateCLick = new ObservableBoolean(false);
    public ObservableBoolean validDateCLick = new ObservableBoolean(false);

    public CardDetailModel(@NonNull Application application) {
        super(application);
    }


    public void setDetailModel(CardManageModel detailModel) {
        this.detailModel = detailModel;
        billDate.set(detailModel.getBill_date());
        repaymentDate.set(detailModel.getRepayment_date());
        securityCode.set(detailModel.getCvn2());
        validDate.set(detailModel.getExpire_date());
//        singleLimit.set(detailModel.get());
//        validDate.set(detailModel.getExpire_date());
        cardNo.set(detailModel.getCard_no());
        bankName.set(detailModel.getOpening_bank());
    }

    public CardManageModel getDetailModel() {
        return detailModel;
    }

    public void clickListen(int type) {
        switch (type) {
            case 1://账单日,
                billDateCLick.set(!billDateCLick.get());
                break;
            case 2://还款日
                repaymentDateCLick.set(!repaymentDateCLick.get());
                break;
            case 4://信用卡到期时间
                validDateCLick.set(!validDateCLick.get());
                break;
        }
    }
}
