package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.util.TimeUtils;

import java.util.Calendar;
import java.util.List;

import static com.itboye.cardmanage.util.TimeUtils.getStrFormat;
import static com.itboye.cardmanage.util.TimeUtils.parseTime2Long;

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
            String outPattern = "yyyy-MM-dd";
            String inPattern = "yyyyMMdd";
            int repaymentDay = item.getCard_repayment_day();
            int billDay = item.getCard_bill_day();
            int cuDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int cuMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
            int cuYear = Calendar.getInstance().get(Calendar.YEAR);
            String repaymentDateStr, billDateStr;
            //还款日如果小于账单日，则往下推一个月
            if (repaymentDay < billDay) {
                repaymentDateStr = TimeUtils.parseTime(cuYear + getStrFormat(cuMonth + 1 + "", 2) + getStrFormat(repaymentDay + "", 2), inPattern, outPattern);
            } else {
                repaymentDateStr = TimeUtils.parseTime(cuYear + getStrFormat(cuMonth + "", 2) + getStrFormat(repaymentDay + "", 2), inPattern, outPattern);
            }
            //如果账单日小于当前日期，则账单日往下推一个月+billday
            if (billDay < cuDay) {
                billDateStr = TimeUtils.parseTime(cuYear + getStrFormat(cuMonth + 1 + "", 2) + getStrFormat(billDay + "", 2), inPattern, outPattern);
            } else {
                billDateStr = TimeUtils.parseTime(cuYear + getStrFormat(cuMonth + "", 2) + getStrFormat(billDay + "", 2), inPattern, outPattern);
            }
            //计算剩余的天数
            String timeStart = TimeUtils.parseTime(cuYear + getStrFormat(cuMonth + "", 2) + getStrFormat(cuDay + "", 2), inPattern, outPattern);
            String end = billDateStr;
            int remainDay = (int) ((parseTime2Long(end, outPattern) - parseTime2Long(timeStart, outPattern)) / 1000 / 60 / 60 / 24);
            helper.setText(R.id.tv_repayment_date, Html.fromHtml("<font color='black'>" + repaymentDateStr + "</font><br /><br />还款时间"));
            helper.setText(R.id.tv_repaid_amount, Html.fromHtml("<font color='red'>" + item.getWithdraw_money() / 100 + "</font><br /><br />已还金额"));
            helper.setText(R.id.tv_bill_amount, Html.fromHtml("<font color='black'>" + billDateStr + "</font><br /><br />账单日"));
            helper.setText(R.id.tv_remain_days, ("剩余" + remainDay + "天"));
        }
        helper.setText(R.id.tv_card_no, item.getCard_no());
    }
}
