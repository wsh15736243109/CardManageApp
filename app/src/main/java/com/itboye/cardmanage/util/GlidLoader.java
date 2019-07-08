package com.itboye.cardmanage.util;

import android.app.Activity;
import android.content.Context;
import com.yancy.gallerypick.inter.ImageLoader;
import com.yancy.gallerypick.widget.GalleryImageView;
import com.itboye.cardmanage.R;

public class GlidLoader implements ImageLoader {
    private final static String TAG = "GlideImageLoader";

    @Override
    public void displayImage(Activity activity, Context context, String path, GalleryImageView galleryImageView, int width, int height) {
        GlideUtil.display(context,path,galleryImageView,R.drawable.ic_logo);
    }

    @Override
    public void clearMemoryCache() {

    }
}
