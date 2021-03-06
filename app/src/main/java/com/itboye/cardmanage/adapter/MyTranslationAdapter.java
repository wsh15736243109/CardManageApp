package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.RelativeLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.TranslationBean;

import java.util.List;

import static com.itboye.cardmanage.util.TimeUtils.timeFormat;

public class MyTranslationAdapter extends BaseQuickAdapter<TranslationBean, BaseViewHolder> {
    public MyTranslationAdapter(@Nullable List<TranslationBean> data) {
        super(R.layout.item_translation_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TranslationBean item) {
        String titleType = "";
        String content;
        switch (item.getOrder_type()) {
            case "user_withdraw":
                helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_translation_withdraw);
                content = "<font color='#FF7E00'>+" + String.format("%.2f",item.getAmount() - item.getSys_fee()) + "</font>";
                titleType = "提现";
                break;
            default:
                double amount = item.getAmount();
                if (amount > 0) {
                    titleType = "支出";
                    content = "<font color='#31B70E'>-" + String.format("%.2f",item.getAmount()) + "</font>";
                    helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_zhichu);
                } else {
                    titleType = "收入";
                    content = "<font color='#FF7E00'>+" + String.format("%.2f",Math.abs(item.getAmount()) - item.getSys_fee()) + "</font>";
                    helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_shouru);
                }
        }

        helper.setText(R.id.tv_translation_title, item.get_card_name() + "(" + item.get_card_no() + ")" + titleType);
        helper.setText(R.id.tv_translation_content, Html.fromHtml(content));

        int status = item.get_status();
        String statusStr = "";
        switch (status) {
            case 1:
                statusStr = "交易成功";
                break;
            case 2:
                statusStr = "处理中";
                break;
            case 0:
                statusStr = "等待交易";
                break;
            case -1:
            default:
                statusStr = "<font color='red'>交易失败</font>";
        }


        helper.setText(R.id.tv_translation_status, Html.fromHtml(statusStr));
        helper.setText(R.id.tv_translation_fee, Html.fromHtml("手续费:￥" + String.format("%.2f",item.getSys_fee())));
//        ((RelativeLayout.LayoutParams) helper.getView(R.id.tv_translation_date).getLayoutParams()).removeRule(RelativeLayout.BELOW);
        if (item.getNotify_time() > 0) {
            helper.setText(R.id.tv_translation_date, timeFormat(item.getNotify_time() * 1000, "yyy-MM-dd HH:mm:ss"));
            helper.setGone(R.id.tv_translation_date, true);
//            ((RelativeLayout.LayoutParams) helper.getView(R.id.tv_translation_date).getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.tv_translation_date);
        } else {
            ((RelativeLayout.LayoutParams) helper.getView(R.id.tv_translation_date).getLayoutParams()).addRule(RelativeLayout.BELOW, R.id.tv_translation_title);
            helper.setGone(R.id.tv_translation_date, false);
        }

    }
}
