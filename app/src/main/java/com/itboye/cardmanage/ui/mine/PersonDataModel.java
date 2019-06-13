package com.itboye.cardmanage.ui.mine;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import com.itboye.cardmanage.bean.UploadImageBean;
import com.itboye.cardmanage.bean.UserInfoBean;
import com.itboye.cardmanage.config.Global;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.UserUtil;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;

import static com.itboye.cardmanage.util.ImageCompress.compress;

public class PersonDataModel extends BaseViewModel {
    //用户名的绑定
    public ObservableField<String> nickName = new ObservableField<>(UserUtil.getUserInfo().getNickname());
    public ObservableField<String> headUrl = new ObservableField<>(UserUtil.getUserInfo().getAvatar());

    public PersonDataModel(@NonNull Application application) {
        super(application);
    }

    UIChange ui = new UIChange();

    public class UIChange {
        ObservableBoolean photo = new ObservableBoolean(false);
    }

    //更换头像
    public void updateHead(String path) {
        //先压缩
        path = compress(path);
        String temp = path;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);//表单类型
        builder.addFormDataPart("uid", UserUtil.getUserInfo().getId() + "");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("sid", UserUtil.getUserInfo().getSid());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("t", "avatar");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("image", file.getName(), body); //添加图片数据，body创建的请求体
        List<MultipartBody.Part> parts = builder.build().parts();
        AppUtils.upload(RetrofitClient.getInstance().createUpload(API.class).uploadImage(parts),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg) {
                        UploadImageBean uploadImageBean = (UploadImageBean) o;
                        //重新更新本地用户缓存
                        UserInfoBean userInfoBean = UserUtil.getUserInfo();
                        userInfoBean.setAvatar(Global.BASEURL + uploadImageBean.getRelative_path());
                        UserUtil.saveUser(userInfoBean);
                        ToastUtils.showShort(msg);
                        finish();
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });

    }

    public void changeHead() {
        ui.photo.set(!ui.photo.get());
    }

    //保存用户更改信息
    public void saveData() {
        if (nickName.get().isEmpty()) {
            ToastUtils.showShort("昵称为空");
            return;
        }

        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).updateUserInfo2(nickName.get(), "by_UserLoginSession_updateInfo"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg) {
                        //重新更新本地用户缓存
                        UserInfoBean userInfoBean = UserUtil.getUserInfo();
                        userInfoBean.setNickname(nickName.get());
                        UserUtil.saveUser(userInfoBean);
                        ToastUtils.showShort(msg);
                        finish();
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }
}
