package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.interfaces.OnMyClickLisenter;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;

import java.util.HashMap;
import java.util.List;

public class CardListAdapter extends BaseQuickAdapter<CardManageModel, BaseViewHolder> {
    private final OnMyItemClickListener onMyClickLisenter;

    public CardListAdapter(@Nullable List<CardManageModel> data, OnMyItemClickListener onMyClickLisenter) {
        super(R.layout.item_card_list, data);
        this.onMyClickLisenter = onMyClickLisenter;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardManageModel item) {

        helper.setText(R.id.tv_card_no, item.getCard_no());
        helper.setText(R.id.tv_title, Html.fromHtml(item.getBranch_bank() + "<br />信用卡"));
        helper.setBackgroundRes(R.id.tv_check_status, item.isCheck() ? R.drawable.ic_checked : R.drawable.ic_uncheck);
        helper.setOnClickListener(R.id.tv_check_status, (View.OnClickListener) view -> onMyClickLisenter.onItemClick(helper.getAdapterPosition(), item));
        helper.setOnClickListener(R.id.cl_root, (View.OnClickListener) view -> onMyClickLisenter.onItemClick(helper.getAdapterPosition(), item));
    }
}
