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

public class RepaymentListAdapter extends BaseQuickAdapter<CardManageModel, BaseViewHolder> {
    private final OnMyItemClickListener onMyClickLisenter;

    public RepaymentListAdapter(@Nullable List<CardManageModel> data, OnMyItemClickListener onMyClickLisenter) {
        super(R.layout.item_repayment_plan, data);
        this.onMyClickLisenter = onMyClickLisenter;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardManageModel item) {
        helper.setText(R.id.tv_loan_text, "计划" + (helper.getAdapterPosition() + 1));
        helper.setText(R.id.tv_budget, Html.fromHtml(item.getMoney() / 100 + "<br />预算"));//预算
        helper.setText(R.id.tv_count, Html.fromHtml(item.getDays() + "<br />次数"));//次数
        helper.setText(R.id.tv_total_amount, Html.fromHtml(item.getPrestore_money() / 100 + "<br />预期还款总额"));//预期还款总额
        helper.setBackgroundRes(R.id.iv_repayment_status, item.getPlan_status().equals("running") ? R.drawable.ic_repayment_ing : R.drawable.ic_repayment_finish);
        helper.setOnClickListener(R.id.cl_root, (View.OnClickListener) view -> onMyClickLisenter.onItemClick(helper.getAdapterPosition(), item));
    }
}
