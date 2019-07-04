package com.itboye.cardmanage.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.itboye.cardmanage.R;
import me.goldze.mvvmhabit.utils.KLog;

import java.io.ByteArrayOutputStream;
import java.security.MessageDigest;

import static com.itboye.cardmanage.util.SizeUtils.dip2px;
import static com.itboye.cardmanage.util.SizeUtils.getScreenWidth;

/**
 * Glide 加载工具
 */

public class GlideUtil {
    public static String TAG = "image";

    public static void display(Context context, String url, ImageView imageView) {
        display(context, url, imageView, DiskCacheStrategy.DATA, R.drawable.ic_empty, true, false);
        KLog.v(TAG, "图片路径==" + url);
    }

    public static void displayForCircle(Context context, String url, ImageView imageView) {
        display(context, url, imageView, DiskCacheStrategy.DATA, R.drawable.ic_empty, true, true);
    }

    public static void displayForCircle(Context context, String url, int defaultImg, ImageView imageView) {
        display(context, url, imageView, DiskCacheStrategy.DATA, defaultImg, true, true);
    }

    public static void display(Context context, String url, ImageView imageView, @DrawableRes int defaultImg) {
        display(context, url, imageView, DiskCacheStrategy.DATA, defaultImg, true, false);
    }

    public static void display(Context context, String url, ImageView imageView, boolean centerCrop) {
        display(context, url, imageView, DiskCacheStrategy.DATA, R.drawable.ic_empty, centerCrop, false);
    }

    public static void display(Context context, String url, ImageView imageView, int defaultImage, boolean centerCrop) {
        display(context, url, imageView, DiskCacheStrategy.DATA, defaultImage, centerCrop, false);
    }

    public static void display(Context context, String url, ImageView imageView, DiskCacheStrategy diskCacheStrategy) {
        display(context, url, imageView, diskCacheStrategy, R.drawable.ic_empty, true, false);
    }

    public static void display(Context context, String url, ImageView imageView, DiskCacheStrategy diskCacheStrategy, boolean centerCrop) {
        display(context, url, imageView, diskCacheStrategy, R.drawable.ic_empty, centerCrop, false);
    }

    public static void display(Context context, String url, ImageView imageView, DiskCacheStrategy diskCacheStrategy, @DrawableRes int defaultImg) {
        display(context, url, imageView, diskCacheStrategy, defaultImg, true, false);
    }

    public static void display(Context context, int resource, ImageView imageView, boolean isCircle) {
        display(context, resource, imageView, true, false);
    }

    public static void display(Context context, String url, ImageView imageView, DiskCacheStrategy diskCacheStrategy, @DrawableRes int defaultImg, boolean centerCrop, boolean isCircle) {
        RequestOptions options;
        if (centerCrop) {
            options = new RequestOptions().error(defaultImg).placeholder(defaultImg).diskCacheStrategy(diskCacheStrategy).centerCrop();
        } else {
            options = new RequestOptions().error(defaultImg).placeholder(defaultImg).diskCacheStrategy(diskCacheStrategy);
        }
        if (isCircle) {
            options = options.transform(new GlideCircleTransform());
        }
        Glide.with(context).load(url).apply(options).into(imageView);
        Log.v("image_url", "glide load:" + url);
    }

    public static void display(Context context, int url, ImageView imageView, boolean centerCrop, boolean isCircle) {
        RequestOptions options;
//        if (centerCrop) {
//            options = new RequestOptions().error(defaultImg).placeholder(defaultImg).diskCacheStrategy(diskCacheStrategy).centerCrop();
//        } else {
//            options = new RequestOptions().error(defaultImg).placeholder(defaultImg).diskCacheStrategy(diskCacheStrategy);
//        }
//        if (isCircle) {
//            options=options.transform(new GlideCircleTransform());
//        }
        Glide.with(context).load(url).into(imageView);
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        int options = 100;
        int maxkb = 30 * 1024;
        while (output.toByteArray().length > maxkb && options != 10) {
            output.reset(); //清空output
            bmp.compress(Bitmap.CompressFormat.JPEG, options, output);//这里压缩options%，把压缩后的数据存放到output中
            options -= 10;
        }
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void displayRound(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions().transform(new GlideRoundTransform(context, 6));
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    public static void displayBitmap(Context context, String url, boolean isCircle, final View view) {
        RequestOptions options;
        options = new RequestOptions().centerCrop().transform(new Transformation<Bitmap>() {
            @NonNull
            @Override
            public Resource<Bitmap> transform(@NonNull Context context, @NonNull Resource<Bitmap> resource, int outWidth, int outHeight) {
                if (view instanceof TextView) {
                    ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(new BitmapDrawable(resource.get()), null, null, null);
                }
                return resource;
            }

            @Override
            public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

            }
        });
        Glide.with(context).load(url).apply(options);
    }

    public static void displayRatio(final Context context, String url, final ImageView imageView) {
        RequestOptions options = new RequestOptions().transform(new GlideRoundTransform(context, 6));
        Glide.with(context).applyDefaultRequestOptions(options).asBitmap().load(url)
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    /**
                     * The method that will be called when the resource load has finished.
                     *
                     * @param resource   the loaded resource.
                     * @param transition
                     */
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        int imageWidth = resource.getWidth();
                        int imageHeight = resource.getHeight();
                        int width = getScreenWidth(context) - dip2px(context, 24);//固定宽度
                        //宽度固定,然后根据原始宽高比得到此固定宽度需要的高度
                        int height = width * imageHeight / imageWidth;
                        ViewGroup.LayoutParams para = imageView.getLayoutParams();
                        para.height = height;
                        para.width = width;
                        imageView.setImageBitmap(resource);
                    }
                });
    }
}
