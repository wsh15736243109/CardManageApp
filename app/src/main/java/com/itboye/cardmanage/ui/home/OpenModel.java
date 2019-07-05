package com.itboye.cardmanage.ui.home;

import android.app.Application;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class OpenModel extends BaseViewModel {
    public ObservableField<String> xieyi = new ObservableField<>("<font color='black'><b>代扣服务协议</b></font><br />本协议是用户与平台之间的法律协议， 是用户注" +
            "册账号并使用平台服务或非经注册程序直接使用平台服" +
            "务时的通用条款。 请您务必审慎阅读、充分理解本协议" +
            "各条款内容，特别是免除或者限制责任的条款、管辖与" +
            "法律适用条款。 限制、免责条款可能以黑体加粗或加下" +
            "划线的形式提示您重点注意。除非您已阅读并接受本协" +
            "议所有条款， 否则您无权使用平台提供的服务。您使用" +
            "平台的服务即视为您已阅读并同意本协议的约束。");
    public String bank_id;
    public String phone;
    public int type;

    public OpenModel(@NonNull Application application) {
        super(application);
    }

    public void setXieyi(int type) {
        if (type == 1) {
            xieyi.set("<font color='black'><b>代扣服务协议</b></font>代扣服务协议本协议是用户与平台之间的法律协议， 是用户注" +
                    "册账号并使用平台服务或非经注册程序直接使用平台服" +
                    "务时的通用条款。 请您务必审慎阅读、充分理解本协议" +
                    "各条款内容，特别是免除或者限制责任的条款、管辖与" +
                    "法律适用条款。 限制、免责条款可能以黑体加粗或加下" +
                    "划线的形式提示您重点注意。除非您已阅读并接受本协" +
                    "议所有条款， 否则您无权使用平台提供的服务。您使用" +
                    "平台的服务即视为您已阅读并同意本协议的约束。");
        } else {
            xieyi.set("本协议是用户与平台之间的法律协议， 是用户注" +
                    "册账号并使用平台服务或非经注册程序直接使用平台服" +
                    "务时的通用条款。 请您务必审慎阅读、充分理解本协议" +
                    "各条款内容，特别是免除或者限制责任的条款、管辖与" +
                    "法律适用条款。 限制、免责条款可能以黑体加粗或加下" +
                    "划线的形式提示您重点注意。除非您已阅读并接受本协" +
                    "议所有条款， 否则您无权使用平台提供的服务。您使用" +
                    "平台的服务即视为您已阅读并同意本协议的约束。");
        }
    }

    public void open() {
        Bundle bundle = new Bundle();
        bundle.putString("bank_id", bank_id);
        bundle.putString("phone", phone);
        bundle.putInt("type", type);
        startActivity(AuthMobileActivity.class, bundle);
    }


}
