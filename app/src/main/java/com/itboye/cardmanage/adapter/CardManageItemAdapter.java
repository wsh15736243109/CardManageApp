package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.app.App;
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
        helper.setText(R.id.tv_card_manage_type, item.getCard_type() == 1 ? "信用卡" : "储蓄卡");
        helper.setText(R.id.item_card_manage_master, item.getMaster() == 0 ? "设为主卡" : "主卡");
        helper.setTextColor(R.id.item_card_manage_master, item.getMaster() == 1 ?  ContextCompat.getColor(App.getInstance(), R.color.red) : ContextCompat.getColor(App.getInstance(), R.color.white));
        helper.setBackgroundColor(R.id.item_card_manage_master, item.getMaster() == 0 ? ContextCompat.getColor(App.getInstance(), R.color.gray_c6c6c6) : ContextCompat.getColor(App.getInstance(), R.color.white));
        helper.setText(R.id.tv_card_manage_no, item.getCard_no());
        helper.addOnClickListener(R.id.cl_root);

        helper.setGone(R.id.item_card_manage_daifu, false);//是否签约代付  //隐藏掉代付
//        helper.setGone(R.id.item_card_manage_daifu, item.getWithdraw_agree_id().isEmpty());//是否签约代付
        helper.setGone(R.id.item_card_manage_daikou, item.getPay_agree_id().isEmpty());//是否签约代扣
        helper.setOnClickListener(R.id.item_card_manage_daikou, (View.OnClickListener) view -> onMyItemClickListener.onItemClick(view, helper.getAdapterPosition(), item));
        helper.setOnClickListener(R.id.item_card_manage_daifu, (View.OnClickListener) view -> onMyItemClickListener.onItemClick(view, helper.getAdapterPosition(), item));
        helper.setOnClickListener(R.id.item_card_manage_master, (View.OnClickListener) view -> onMyItemClickListener.onItemClick(view, helper.getAdapterPosition(), item));
    }

    private int getBankResource(int bankType) {
//        switch (bankType) {
//            case 0:
//                break;
//        }
        return R.drawable.bank_zhongguo;
    }
}
