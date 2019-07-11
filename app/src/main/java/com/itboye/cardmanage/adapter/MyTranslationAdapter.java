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
        double amount = item.getAmount();
        if (amount <= 0) {
            helper.setText(R.id.tv_translation_title, item.get_card_name() + "(" + item.get_card_no() + ")支出");
        } else {
            helper.setText(R.id.tv_translation_title, item.get_card_name() + "(" + item.get_card_no() + ")收入");
        }
        String content;
        content = (item.getAmount() > 0 ? "<font color='#FF7E00'>" : "<font color='#31B70E'>") + item.getAmount() + "</font>";
        if (item.getSuc_proc_status() == 1) {//失败
            helper.setText(R.id.tv_translation_status, Html.fromHtml("交易成功"));
            helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_shouru);
        } else {
            helper.setText(R.id.tv_translation_status, Html.fromHtml("<font color='red'>交易失败</font>"));
            helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_zhichu);
        }
        helper.setText(R.id.tv_translation_content, Html.fromHtml(content));
        helper.setText(R.id.tv_translation_fee, Html.fromHtml("手续费:￥" + item.getSys_fee()));
        ((RelativeLayout.LayoutParams) helper.getView(R.id.tv_translation_date).getLayoutParams()).removeRule(RelativeLayout.BELOW);
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
