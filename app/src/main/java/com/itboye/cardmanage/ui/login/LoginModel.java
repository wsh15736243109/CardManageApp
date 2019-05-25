package com.itboye.cardmanage.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import com.itboye.cardmanage.R;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class LoginModel extends BaseViewModel {

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("请输入手机号码");
    public ObservableField<Integer> res = new ObservableField<>();
    public ObservableField<String> resD = new ObservableField<>("https://www.baidu.com/img/dong_96c3c31cae66e61ed02644d732fcd5f8.gif");

    public LoginModel(@NonNull Application application) {
        super(application);
        res.set(R.mipmap.test);
        resD.set("https://www.baidu.com/img/dong_96c3c31cae66e61ed02644d732fcd5f8.gif");
    }


    public BindingCommand loginClick = new BindingCommand(new BindingAction() {
                @Override
                public void call() {
                    ToastUtils.showShort("登录拉" + userName.get());
                }
            });
}
