package com.itboye.cardmanage.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.itboye.cardmanage.bean.HomeBean;
import com.itboye.cardmanage.util.GlideUtil;

/**
 * ImageViewHolder
 * <p>
 * Created by Mr.w on 2018/3/17.
 * <p>
 * 版本      ${version}
 * <p>
 * 修改时间
 * <p>
 * 修改内容
 */


public class ImageViewHolder implements Holder<HomeBean.ApplyCardBean> {
    private ImageView imageView;

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, HomeBean.ApplyCardBean data) {
        GlideUtil.display(context, data.getImg_url(), imageView);
    }
}
