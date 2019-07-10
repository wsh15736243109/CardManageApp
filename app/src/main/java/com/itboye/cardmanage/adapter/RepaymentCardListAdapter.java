package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;

import java.util.List;

public class RepaymentCardListAdapter extends BaseQuickAdapter<CardManageModel, BaseViewHolder> {
    private final int type;

    public RepaymentCardListAdapter(@Nullable List<CardManageModel> data, int type, OnMyItemClickListener onMyClickLisenter) {
        super(R.layout.item_repayment_huankuan_card, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardManageModel item) {
        if (type == 0) {
            helper.setGone(R.id.ll_content, false);
            helper.setGone(R.id.tv_remain_days, false);
            helper.setGone(R.id.tv_card_no, true);
        } else {
            helper.setGone(R.id.ll_content, true);
            helper.setGone(R.id.tv_remain_days, true);
            helper.setGone(R.id.tv_card_no, true);
//            helper.setText(R.id.tv_remain_days, item.get);
            helper.setText(R.id.tv_repayment_date, Html.fromHtml("<font color='black'>" + item.getCard_repayment_day() + "</font><br /><br />还款时间"));
            helper.setText(R.id.tv_repaid_amount, Html.fromHtml("<font color='red'>" + item.getWithdraw_money() / 100 + "</font><br /><br />已还金额"));
            helper.setText(R.id.tv_bill_amount, Html.fromHtml("<font color='black'>" + item.getCard_bill_day() + "</font><br /><br />账单日"));
            helper.setText(R.id.tv_remain_days, ("剩余" + (item.getDays() - item.getRepay_count()) + "天"));
        }
//        helper.setText(R.id.tv_card_no, item.getCard_no());
//        helper.setText(R.id.tv_bank_name, item.getBranch_bank());
        helper.setText(R.id.tv_card_no, item.getCard_no());
//        helper.setText(R.id.tv_card_no, item.getBranch_bank() + "(" + item.getCard_no() + ")");
    }
}
