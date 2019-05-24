package com.itboye.cardmanage.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.R;
import me.goldze.mvvmhabit.base.BaseViewModel;

public class LoginModel extends BaseViewModel {

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("我是启动页的button");
    public ObservableField<Integer> res = new ObservableField<>();
    public ObservableField<String> resD = new ObservableField<>("https://www.baidu.com/img/dong_96c3c31cae66e61ed02644d732fcd5f8.gif");

    public LoginModel(@NonNull Application application) {
        super(application);
        userName.set("启动页的bugtton呢？---");
        res.set(R.mipmap.test);
        resD.set("https://www.baidu.com/img/dong_96c3c31cae66e61ed02644d732fcd5f8.gif");
    }
}
