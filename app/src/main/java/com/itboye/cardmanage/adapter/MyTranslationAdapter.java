package com.itboye.cardmanage.adapter;

import android.text.Html;
import com.itboye.cardmanage.R;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.bean.TranslationBean;
import com.yancy.imageselector.utils.TimeUtils;

import java.net.HttpRetryException;
import java.util.List;

public class MyTranslationAdapter extends BaseQuickAdapter<TranslationBean, BaseViewHolder> {
    public MyTranslationAdapter(@Nullable List<TranslationBean> data) {
        super(R.layout.item_translation_reconord, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TranslationBean item) {
        helper.setText(R.id.tv_translation_title, item.getNote());
        String content;
        if (item.getAmount() >= 0) {
            content = "<font color='orange'>+" + item.getAmount() + "</font><br />到账成功";
        } else {
            content = "<font color='green'>-" + item.getAmount() + "</font><br />失败";

        }
        helper.setText(R.id.tv_translation_content, Html.fromHtml(content));
        helper.setText(R.id.tv_translation_date, TimeUtils.timeFormat(item.getCreate_time()*1000, "yyy-MM-dd HH:mm:ss"));
        helper.setBackgroundRes(R.id.iv_translation_icon, R.drawable.ic_shouru);
    }
}
