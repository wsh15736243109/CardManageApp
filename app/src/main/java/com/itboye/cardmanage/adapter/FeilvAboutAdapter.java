package com.itboye.cardmanage.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.app.App;
import com.itboye.cardmanage.bean.PayWaybean;
import com.itboye.cardmanage.interfaces.OnMyItemClickListener;
import com.itboye.cardmanage.util.TimeUtils;

import java.util.List;

public class FeilvAboutAdapter extends BaseQuickAdapter<PayWaybean, BaseViewHolder> {


    public FeilvAboutAdapter(@Nullable List<PayWaybean> data, OnMyItemClickListener onMyItemClickListener) {
        super(R.layout.item_feilv, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayWaybean item) {
        helper.setBackgroundRes(R.id.iv_service_logo, R.drawable.ic_feilv_baihuo_logo);
        helper.setText(R.id.tv_service_name, item.getTitle());
        helper.setText(R.id.tv_jiaoyi_time, "交易时间：" + TimeUtils.parseTime(TimeUtils.getStrFormat(item.getDay_time_start(), 4), "HHmm", "HH:mm") + "-" + TimeUtils.parseTime(TimeUtils.getStrFormat(item.getDay_time_end(), 4), "HHmm", "HH:mm"));
        if (item.get_grade() != null && item.get_grade().size() > 0) {
            TextView tv = helper.getView(R.id.tv_vip_normal);
            helper.setText(R.id.tv_vip_normal, item.get_grade().get(0).getTitle() + "交易手续费：" + String.format("%.2f", item.get_grade().get(0).getFee_per() * 100) + "%+" + item.get_grade().get(0).getFixed_fee() + " " + TimeUtils.parseTime(TimeUtils.getStrFormat(item.get_grade().get(0).getDay_time_start(), 4), "HHmm", "HH:mm") + "-" + TimeUtils.parseTime(TimeUtils.getStrFormat(item.get_grade().get(0).getDay_time_end(), 4), "HHmm", "HH:mm") + "交易");
//            helper.setText(R.id.tv_xiaofei, item.get_grade().get(0).getStart_amt());
            Drawable drawable = App.getInstance().getResources().getDrawable(R.drawable.ic_service_vip_normal);
            tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        if (item.get_grade() != null && item.get_grade().size() > 1) {
            TextView tv = helper.getView(R.id.tv_vip);
            helper.setText(R.id.tv_vip, item.get_grade().get(1).getTitle() + "交易手续费：" + String.format("%.2f", item.get_grade().get(1).getFee_per() * 100) + "%+" + item.get_grade().get(1).getFixed_fee() + " " + TimeUtils.parseTime(TimeUtils.getStrFormat(item.get_grade().get(1).getDay_time_start(), 4), "HHmm", "HH:mm") + "-" + TimeUtils.parseTime(TimeUtils.getStrFormat(item.get_grade().get(1).getDay_time_end(), 4), "HHmm", "HH:mm") + "交易");
            Drawable drawable = App.getInstance().getResources().getDrawable(R.drawable.ic_service_vip);
            tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        helper.setText(R.id.tv_xiaofei, Html.fromHtml("单笔≤" + item.getSingle_limit_money() + "元<br /><br />" +
                "单卡单日≤" + item.getSingle_card_day_limit_money() + "元<br /><br />" +
                "单日单账户≤" + item.getDay_limit_money() + "元"));
    }
}
