package com.itboye.cardmanage.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivityAuthenticationBinding;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;
import com.itboye.cardmanage.util.GlideUtil;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageLoader;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.io.File;
import java.util.List;

public class AuthenticationActivity extends BaseMVVMActivity<ActivityAuthenticationBinding, AuthenticationModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_authentication;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initViewObservable() {
        viewModel.ui.photo1.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                viewModel.ui.photo1.set(false);
                openLibrary(101);
            }
        });
        viewModel.ui.photo2.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                viewModel.ui.photo2.set(false);
                openLibrary(102);
            }
        });
        viewModel.ui.photo3.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                viewModel.ui.photo3.set(false);
                openLibrary(103);
            }
        });
        viewModel.ui.photo4.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
//                viewModel.ui.photo4.set(false);
                openLibrary(104);
            }
        });
    }

    //选择图片
    private void openLibrary(int requestCode) {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                GlideUtil.display(context, path, imageView);
            }
        })
                .steepToolBarColor(getApplication().getResources().getColor(R.color.white))
                .titleBgColor(getApplication().getResources().getColor(R.color.white))
                .titleSubmitTextColor(getApplication().getResources().getColor(R.color.white))
                .titleTextColor(getApplication().getResources().getColor(R.color.red))
                // (截图默认配置：关闭    比例 1：1    输出分辨率  500*500)
//                .crop(2, 1, 1000, 500)
                // 开启单选   （默认为多选）
                .requestCode(requestCode)
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ImageSelector/Pictures")
                .build();
        ImageSelector.open(this, imageConfig);   // 开启图片选择器
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            String path = pathList.get(0);
            switch (requestCode) {
                case 101:
                    GlideUtil.display(this, path, binding.riPhoto1);
                    break;
                case 102:
                    GlideUtil.display(this, path, binding.riPhoto2);
                    break;
                case 103:
                    GlideUtil.display(this, path, binding.riPhoto3);
                    break;
                case 104:
                    GlideUtil.display(this, path, binding.riPhoto4);
                    break;
            }
            viewModel.uploadImage(path, resultCode);
        }
    }
}
