package com.itboye.cardmanage.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.app.App;
import com.itboye.cardmanage.bean.BannerBean;
import com.itboye.cardmanage.bean.HomeBean;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.model.CardManageModel;
import com.itboye.cardmanage.util.GlideUtil;

import java.util.List;

public class BannerAdapter extends BaseQuickAdapter<HomeBean.ApplyCardBean, BaseViewHolder> {
    private final OnMyItemClickListener onMyClickLisenter;

    public BannerAdapter(@Nullable List<HomeBean.ApplyCardBean> data, OnMyItemClickListener onMyClickLisenter) {
        super(R.layout.item, data);
        this.onMyClickLisenter = onMyClickLisenter;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ApplyCardBean item) {
//        helper.setText(R.id.tv_card_no, item.getCard_no());
//        helper.setText(R.id.tv_home_top, Html.fromHtml(item.get() + "<br />"));
        GlideUtil.display(App.getInstance(), item.getImg_url(),  helper.getView(R.id.iv_home_top));
//        helper.setBackgroundRes(R.id.tv_check_status, item.isCheck() ? R.drawable.ic_checked : R.drawable.ic_uncheck);
//        helper.setOnClickListener(R.id.tv_check_status, (View.OnClickListener) view -> onMyClickLisenter.onItemClick(helper.getAdapterPosition(),item));
    }
}
