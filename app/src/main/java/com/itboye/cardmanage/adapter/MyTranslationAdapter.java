package com.itboye.cardmanage.adapter;

import com.itboye.cardmanage.R;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.bean.TranslationBean;

import java.util.List;

public class MyTranslationAdapter extends BaseQuickAdapter<TranslationBean, BaseViewHolder> {
    public MyTranslationAdapter(@Nullable List<TranslationBean> data) {
        super(R.layout.item_translation_reconord, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TranslationBean item) {

    }
}
