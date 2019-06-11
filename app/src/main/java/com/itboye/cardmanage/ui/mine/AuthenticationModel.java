package com.itboye.cardmanage.ui.mine;

import android.content.Context;
import android.databinding.ObservableBoolean;
import android.widget.ImageView;
import com.itboye.cardmanage.R;

import android.app.Application;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.GlideUtil;
import com.itboye.cardmanage.util.UserUtil;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageLoader;
import com.yancy.imageselector.ImageSelector;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.List;

import static com.itboye.cardmanage.ui.mine.AuthenticationModel.Status.PHOTO_CARD;
import static com.itboye.cardmanage.ui.mine.AuthenticationModel.Status.PHOTO_HAND_IDENTITY;
import static com.itboye.cardmanage.ui.mine.AuthenticationModel.Status.PHOTO_IDENTITY;
import static com.itboye.cardmanage.util.ImageCompress.compress;

public class AuthenticationModel extends BaseViewModel {

    public ObservableField<String> bankReservePhone = new ObservableField<>("");//银行预留手机
    public ObservableField<String> branchBankName = new ObservableField<>("");//支行名称
    public ObservableField<String> bankName = new ObservableField<>("");//银行名称
    public ObservableField<String> bankNumberAgain = new ObservableField<>("");//再次银行卡账号
    public ObservableField<String> bankNumber = new ObservableField<>("");//银行卡号

    public ObservableField<String> realName = new ObservableField<>("");//姓名
    public ObservableField<String> idnumber = new ObservableField<>("");//身份证号
    public ObservableField<String> addr = new ObservableField<>("");//地址
    public ObservableField<String> email = new ObservableField<>("");//邮箱
    public ObservableField<String> zipCode = new ObservableField<>("");//邮编
    public ObservableField<String> label = new ObservableField<>("");//提示语

    public ObservableField<Integer> photoIdentity = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> authCard = new ObservableField<>(View.GONE);

    public ObservableField<Integer> photo1 = new ObservableField<>(View.VISIBLE);
    public ObservableField<Integer> photo3 = new ObservableField<>(View.GONE);
    public ObservableField<Integer> photo4 = new ObservableField<>(View.GONE);

    public ObservableField<Drawable> status1 = new ObservableField<>();
    public ObservableField<Drawable> status2 = new ObservableField<>();
    public ObservableField<Drawable> status3 = new ObservableField<>();


    Status status = PHOTO_IDENTITY;

    public AuthenticationModel(@NonNull Application application) {
        super(application);
        status1.set(application.getResources().getDrawable(R.drawable.ic_status));
        status2.set(application.getResources().getDrawable(R.drawable.ic_status));
        status3.set(application.getResources().getDrawable(R.drawable.ic_status));
    }

    public void next() {
        setCurrentItem();
    }

    private void setCurrentItem() {
        switch (status) {
            case PHOTO_IDENTITY:
                if (path1 == null || path2 == null) {
                    ToastUtils.showShort("请先上传身份证正反面");
                    return;
                }

                if (realName.get().isEmpty()) {
                    ToastUtils.showShort("请输入姓名");
                    return;
                }
                if (idnumber.get().isEmpty()) {
                    ToastUtils.showShort("请输入身份证号");
                    return;
                }
                if (idnumber.get().isEmpty()) {
                    ToastUtils.showShort("请输入地址");
                    return;
                }
                if (email
                        .get().isEmpty()) {
                    ToastUtils.showShort("请输入邮箱");
                    return;
                }
                if (zipCode.get().isEmpty()) {
                    ToastUtils.showShort("请输入邮编");
                    return;
                }
                label.set("手持身份证正面照");
                status1.set(getApplication().getResources().getDrawable(R.drawable.ic_status_check));
                photoIdentity.set(View.GONE);
                authCard.set(View.GONE);
                photo1.set(View.GONE);
                photo3.set(View.VISIBLE);
                status = PHOTO_HAND_IDENTITY;
                break;
            case PHOTO_HAND_IDENTITY:
                if (path1 == null || path2 == null) {
                    ToastUtils.showShort("请先上传手持身份证正面照");
                    return;
                }
                label.set("银行卡正面照");
                status = PHOTO_CARD;
                authCard.set(View.VISIBLE);
                photo3.set(View.GONE);
                photo4.set(View.VISIBLE);
                status2.set(getApplication().getResources().getDrawable(R.drawable.ic_status_check));
                break;
            case PHOTO_CARD:
                if (path1 == null || path2 == null) {
                    ToastUtils.showShort("请先上传银行卡正面照");
                    return;
                }
                if (bankNumber.get().isEmpty()) {
                    ToastUtils.showShort("请输入银行卡号");
                    return;
                }
                if (bankNumberAgain.get().isEmpty()) {
                    ToastUtils.showShort("请再次输入银行卡号");
                    return;
                }
                if (bankName.get().isEmpty()) {
                    ToastUtils.showShort("开户行名称获取失败");
                    return;
                }
                if (branchBankName.get().isEmpty()) {
                    ToastUtils.showShort("支行名称获取失败");
                    return;
                }
                if (bankReservePhone.get().isEmpty()) {
                    ToastUtils.showShort("请输入银行预留手机号");
                    return;
                }

                status3.set(getApplication().getResources().getDrawable(R.drawable.ic_status_check));
                ToastUtils.showShort("准备提交");
                break;
        }
    }

    UIChangeListener ui = new UIChangeListener();

    String path1, path2, path3, path4;

    public void uploadImage(String path, final int type) {
        //先压缩
        path = compress(path);
        String temp = path;
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//表单类型
        File file = new File(path);
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);//表单类型
        builder.addFormDataPart("uid", UserUtil.getUserInfo().getId() + "");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("sid", UserUtil.getUserInfo().getSid());//传入服务器需要的key，和相应value值
        builder.addFormDataPart("t", "id_card");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("oss_type", "zmf");//传入服务器需要的key，和相应value值
        builder.addFormDataPart("image", file.getName(), body); //添加图片数据，body创建的请求体
        List<MultipartBody.Part> parts = builder.build().parts();
        AppUtils.upload(RetrofitClient.getInstance().createUpload(API.class).uploadImage(parts),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg) {
                        switch (type) {
                            case 1:
                                path1 = temp;
                                break;
                            case 2:
                                path2 = temp;
                                ui.photo2.set(true);
                                break;
                            case 3:
                                path3 = temp;
                                ui.photo3.set(true);
                                break;
                            case 4:
                                path4 = temp;
                                ui.photo4.set(true);
                                break;
                        }
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });

    }

    public class UIChangeListener {
        ObservableBoolean photo1 = new ObservableBoolean(false);
        ObservableBoolean photo2 = new ObservableBoolean(false);
        ObservableBoolean photo3 = new ObservableBoolean(false);
        ObservableBoolean photo4 = new ObservableBoolean(false);
    }

    public void photo(int type) {
        switch (type) {
            case 1:
                ui.photo1.set(true);
                break;
            case 2:
                ui.photo2.set(true);
                break;
            case 3:
                ui.photo3.set(true);
                break;
            case 4:
                ui.photo4.set(true);
                break;
        }
    }

    enum Status {
        PHOTO_IDENTITY,
        PHOTO_HAND_IDENTITY,
        PHOTO_CARD
    }


}
