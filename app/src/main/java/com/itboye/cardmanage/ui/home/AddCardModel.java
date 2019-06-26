package com.itboye.cardmanage.ui.home;

import android.app.AlertDialog;
import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.DatePicker;
import com.itboye.cardmanage.bean.BranchBankBean;
import com.itboye.cardmanage.bean.UploadImageBean;
import com.itboye.cardmanage.retrofit.*;
import com.itboye.cardmanage.util.UserUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.utils.ToastUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.itboye.cardmanage.util.ImageCompress.compress;

public class AddCardModel extends BaseViewModel {
    public ObservableField<String> cardOwner = new ObservableField<>("周波");//持卡人姓名
    public ObservableField<String> idnumber = new ObservableField<>("330327198407040039");//身份证号
    public ObservableField<String> cardNumber = new ObservableField<>("6222081202007602202");//银行卡号
    public ObservableField<String> cardNumberRe = new ObservableField<>("6222081202007602202");//确认银行卡
    public ObservableField<String> bankName = new ObservableField<>("中国工商银行");//开户行名称
    public ObservableField<String> branchBankName = new ObservableField<>("杭州");//支行名称
    public ObservableField<String> reservedPhone = new ObservableField<>("13858066033");//预留手机号

    public ObservableField<String> bill_date = new ObservableField<>("20201010");//账单日
    public ObservableField<String> repayment_date = new ObservableField<>("20201010");//还款日
    public ObservableField<String> safetyCode = new ObservableField<>("20201010");//安全码
    public ObservableField<String> validDate = new ObservableField<>("20201010");//有效日期
    public ObservableInt cardJieSuanKa = new ObservableInt(View.VISIBLE);//结算卡
    public ObservableInt cardZhiFuKa = new ObservableInt(View.GONE);//支付卡
    public String branchNo = "";
    public int index = 0;

    String bank_card_id = "";
    public ArrayList<BranchBankBean> branchBankBeanArrayList = new ArrayList<>();
    private String bank_img_id;

    public AddCardModel(@NonNull Application application) {
        super(application);
    }

    public void submit() {
        //确认添加卡
        if (cardOwner.get().isEmpty()) {
            ToastUtils.showShort("请输入持卡人姓名");
            return;
        }
        if (idnumber.get().isEmpty()) {
            ToastUtils.showShort("请输入身份证号码");
            return;
        }
        if (cardNumber.get().isEmpty()) {
            ToastUtils.showShort("请输入银行卡卡号");
            return;
        }
        if (cardNumberRe.get().isEmpty()) {
            ToastUtils.showShort("请再次输入银行卡卡号");
            return;
        }
        if (cardNumber.get().equals(cardNumberRe)) {
            ToastUtils.showShort("两次银行卡卡号不一致");
            return;
        }
        if (bankName.get().isEmpty()) {
            ToastUtils.showShort("请输入开户行名称");
            return;
        }
        if (cardOwner.get().isEmpty()) {
            ToastUtils.showShort("请输入支行名称");
            return;
        }

        if (reservedPhone.get().isEmpty()) {
            ToastUtils.showShort("请输入银行卡预留手机号码");
            return;
        }
        if (index == 0) {
            if (safetyCode.get().isEmpty()) {
                ToastUtils.showShort("请输入安全码后三位");
                return;
            }
            if (validDate.get().isEmpty()) {
                ToastUtils.showShort("请选择有效日期");
                return;
            }
            //添加支付卡
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).addPaymentCard(cardNumber.get(), bankName.get(), reservedPhone.get(), safetyCode.get(), validDate.get(), bill_date.get(), repayment_date.get(), "by_UserBankCard_bindPayCard"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    ToastUtils.showShort(msg + "====" + o);
                }


                @Override
                public void onError(int code, String msg) {

                }

                @Override
                public void dialogDismiss() {
                    dismissDialog();
                }
            });
        } else if (index == 1) {
            //添加结算卡
            AppUtils.requestData(RetrofitClient.getInstance().create(API.class).addSettlementCard(cardNumber.get(), bankName.get(), reservedPhone.get(), bank_img_id,"by_UserBankCard_bindDebitCard"), getLifecycleProvider(), disposable -> showDialog(), new ApiDisposableObserver() {
                @Override
                public void onResult(Object o, String msg, int code) {
                    ToastUtils.showShort(msg + "====" + o);
                }

                @Override
                public void onError(int code, String msg) {

                }

                @Override
                public void dialogDismiss() {
                    dismissDialog();
                }
            });
        }
    }

    UIChangeListener ui = new UIChangeListener();

    public void setAddCardType(int type) {
    }

    public void uploadImage(String path, int requestCode) {
        //先压缩
        path = compress(path);
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
                    public void onResult(Object o, String msg, int code) {
                        UploadImageBean uploadImageBean = (UploadImageBean) o;
                        bank_img_id = uploadImageBean.getOss_key();
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {

                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }

    public class UIChangeListener {
        ObservableBoolean searchBranch = new ObservableBoolean(false);
        ObservableBoolean showDate = new ObservableBoolean(false);
        ObservableBoolean choosePic = new ObservableBoolean(false);
    }


    //搜索支行信息
    public void branchBankInfoSearch() {
        if (branchBankName.get().isEmpty()) {
            ToastUtils.showShort("请输入支行关键词");
            return;
        }
        AppUtils.upload(RetrofitClient.getInstance().create(CardAPI.class).getBranchInfo(
                cardNumber.get(),
                branchBankName.get()),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg, int code) {
                        branchBankBeanArrayList = (ArrayList<BranchBankBean>) o;
                        ui.searchBranch.set(!ui.searchBranch.get());
                        ToastUtils.showShort(msg);
                    }

                    @Override
                    public void onError(int code, String msg) {

                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }

    //搜索银行卡关联的银行信息
    public void bankInfoSearch() {

    }

    public int chooseType = 0;

    public void showDate(int type) {
        chooseType = type;
        ui.showDate.set(!ui.showDate.get());
    }

    public void photo(int type) {
        switch (type) {
            case 1:
                ui.choosePic.set(!ui.choosePic.get());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
}
