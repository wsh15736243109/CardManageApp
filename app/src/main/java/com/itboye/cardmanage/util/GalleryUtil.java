package com.itboye.cardmanage.util;

import android.app.Activity;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

public class GalleryUtil {
    public static void galleryConfig(Activity activity, IHandlerCallBack iHandlerCallBack) {
        GalleryConfig galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlidLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider(activity.getPackageName() + ".fileprovider")   // provider (必填)
//                .pathList(a)                         // 记录已选的图片
                .multiSelect(false)                      // 是否多选   默认：false
                .multiSelect(false, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(false, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .build();
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(activity);
    }
}
