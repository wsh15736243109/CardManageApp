package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.List;

public class CardManageItemAdapter extends BaseQuickAdapter<CardManageModel, BaseViewHolder> {


    private OnMyItemClickListener onMyItemClickListener;

    public CardManageItemAdapter(@Nullable List<CardManageModel> data, OnMyItemClickListener onMyItemClickListener) {
        super(R.layout.item_card_manage, data);
        this.onMyItemClickListener = onMyItemClickListener;
    }

    @Override
    protected void convert(BaseViewHolder helper, CardManageModel item) {
        helper.setBackgroundRes(R.id.iv_card_manage_icon, getBankResource(0));
        helper.setText(R.id.tv_card_manage_bank, item.getBranch_bank());
        helper.setText(R.id.tv_card_manage_type, "普通卡");
        helper.setText(R.id.item_card_manage_master, item.getMaster() == 0 ? "设为主卡" : "主卡");
        helper.setText(R.id.tv_card_manage_no, item.getCard_no());
        helper.addOnClickListener(R.id.cl_root);
        helper.setOnClickListener(R.id.item_card_manage_daikou, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyItemClickListener.onItemClick(view, helper.getAdapterPosition(), item);
            }
        });
        helper.setOnClickListener(R.id.item_card_manage_daifu, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyItemClickListener.onItemClick(view, helper.getAdapterPosition(), item);
            }
        });
        helper.setOnClickListener(R.id.item_card_manage_master, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMyItemClickListener.onItemClick(view, helper.getAdapterPosition(), item);
            }
        });
    }

    private int getBankResource(int bankType) {
//        switch (bankType) {
//            case 0:
//                break;
//        }
        return R.drawable.bank_zhongguo;
    }
}
