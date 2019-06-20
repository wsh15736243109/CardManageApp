package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.model.CardManageModel;

import java.util.List;

public class CardManageItemAdapter extends BaseQuickAdapter<CardManageModel, BaseViewHolder> {
    public CardManageItemAdapter(@Nullable List<CardManageModel> data) {
        super(R.layout.item_card_manage, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardManageModel item) {
        helper.setBackgroundRes(R.id.iv_card_manage_icon, getBankResource(0));
        helper.setText(R.id.tv_card_manage_bank, item.getBranch_bank());
        helper.setText(R.id.tv_card_manage_type, "普通卡");
        helper.setText(R.id.item_card_manage_master, item.getMaster() == 0 ? "设为主卡" : "主卡");
        helper.setText(R.id.tv_card_manage_no, item.getCard_no());
        helper.addOnClickListener(R.id.cl_root);
    }

    private int getBankResource(int bankType) {
//        switch (bankType) {
//            case 0:
//                break;
//        }
        return R.drawable.bank_zhongguo;
    }
}
