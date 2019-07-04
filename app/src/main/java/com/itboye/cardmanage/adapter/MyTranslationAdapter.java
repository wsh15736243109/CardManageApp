package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.TranslationBean;
import com.yancy.imageselector.utils.TimeUtils;

import java.util.List;

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

        helper.setText(R.id.tv_translation_content, Html.fromHtml(content));
        helper.setText(R.id.tv_translation_date, TimeUtils.timeFormat(item.getCreate_time() * 1000, "yyy-MM-dd HH:mm:ss"));
        helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_shouru);
    }
}
