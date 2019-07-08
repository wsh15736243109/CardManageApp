package com.itboye.cardmanage.ui.mine;

import android.Manifest;
import android.databinding.Observable;
import android.os.Bundle;
import com.itboye.cardmanage.BR;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.base.BaseMVVMActivity;
import com.itboye.cardmanage.databinding.ActivitySplashBinding;
import com.itboye.cardmanage.util.GalleryUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yancy.gallerypick.inter.IHandlerCallBack;
import me.goldze.mvvmhabit.utils.ToastUtils;

import java.util.List;

public class PersonDataActivity extends BaseMVVMActivity<ActivitySplashBinding, PersonDataModel> {


    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_person_data;
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

        viewModel.ui.photo.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                RxPermissions rxPermissions = new RxPermissions(PersonDataActivity.this);
                rxPermissions.request(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(aBoolean -> {
                            if (aBoolean) {
                                openLibrary(101);
                            } else {
                                ToastUtils.showShort("拍照权限被拒绝");
                            }
                        });

            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
//            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
//            String path = pathList.get(0);
//            viewModel.updateHead(path);
//        }
//    }

    //选择图片
    private void openLibrary(int requestCode) {
        GalleryUtil.galleryConfig(this, iHandlerCallBack);
    }

    IHandlerCallBack iHandlerCallBack = new IHandlerCallBack() {
        @Override
        public void onStart() {
        }

        @Override
        public void onSuccess(List<String> photoList) {
            String path = photoList.get(0);
            viewModel.updateHead(path);
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onFinish() {
        }

        @Override
        public void onError() {
        }
    };
}
