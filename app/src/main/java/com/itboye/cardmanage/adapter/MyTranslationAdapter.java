package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.TranslationBean;

import java.util.List;

import static com.itboye.cardmanage.util.TimeUtils.timeFormat;

public class MyTranslationAdapter extends BaseQuickAdapter<TranslationBean, BaseViewHolder> {
    public MyTranslationAdapter(@Nullable List<TranslationBean> data) {
        super(R.layout.item_translation_reconord, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TranslationBean item) {
        helper.setText(R.id.tv_translation_title, item.getNote());
        String content;
        int withdraw_status = item.getWithdraw_status();
        if (withdraw_status == 1) {
            content = "" + (item.getAmount() > 0 ? "<font color='#FF7E00'>+" : "<font color='#31B70E'>-") + item.getAmount() / 100 + "</font><br />" + (item.getAmount() < 0 ? "支出成功" : "到账成功");
        } else {
            content = "" + (item.getAmount() > 0 ? "<font color='#FF7E00'>+" : "<font color='#31B70E'>-") + item.getAmount() / 100 + "</font><br />" + (item.getAmount() < 0 ? "支出失败" : "到账失败");
//            content = "<font color='#31B70E'>" + (item.getAmount() > 0 ? "+" : "-") + item.getAmount() / 100 + "</font><br />到账失败";
        }
        //withdraw_status 提现状态 notify_status 支付状态 -1 是失败
        if (item.getWithdraw_status() == -1) {//提现失败

        } else {

        }
        if (item.getNotify_status() == -1) {//支付失败

        } else {

        }
        helper.setText(R.id.tv_translation_content, Html.fromHtml(content));
        helper.setText(R.id.tv_translation_date, timeFormat(item.getCreate_time() * 1000, "yyy-MM-dd HH:mm:ss"));
        helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_shouru);
    }
}
