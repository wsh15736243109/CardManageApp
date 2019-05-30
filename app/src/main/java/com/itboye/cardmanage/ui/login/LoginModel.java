package com.itboye.cardmanage.ui.login;

import android.app.Application;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.MainActivity;
import com.itboye.cardmanage.R;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class LoginModel extends BaseViewModel {

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("请输入手机号码");
    public ObservableField<String> userPassword = new ObservableField<>("请输入密码");
    public ObservableField<String> confirm_userPassword = new ObservableField<>("确认密码");
    public ObservableField<String> userYzm = new ObservableField<>("请输入验证码");
    public ObservableField<String> loginType = new ObservableField<>("密码登录>>");
    public ObservableField<String> getOperateType = new ObservableField<>("登录");
    public ObservableField<Integer> res = new ObservableField<>();
    public ObservableField<String> resD = new ObservableField<>("https://www.baidu.com/img/dong_96c3c31cae66e61ed02644d732fcd5f8.gif");
    public ObservableInt rlLoginYzm = new ObservableInt(View.VISIBLE);
    public ObservableInt rlLoginPsd = new ObservableInt(View.GONE);
    public ObservableInt rlLoginConfirmPsd = new ObservableInt(View.GONE);
    public ObservableInt visibleValue = new ObservableInt(View.VISIBLE);
    public int OPERTA_TYPE = 1;//1：验证码登录 2：密码登录 3：注册 4找回密码

    public LoginModel(@NonNull Application application) {
        super(application);
        res.set(R.mipmap.test);
        resD.set("https://www.baidu.com/img/dong_96c3c31cae66e61ed02644d732fcd5f8.gif");
    }

    //登录或注册按钮
    public BindingCommand loginClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            startActivity(MainActivity.class);
        }
    });
    /**
     * 密码登录或验证码
     */
    public BindingCommand loginByPwdOrYzm = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            rlLoginConfirmPsd.set(View.GONE);
            if (OPERTA_TYPE == 1) {
                rlLoginYzm.set(View.VISIBLE);
                rlLoginPsd.set(View.GONE);
                setGetOperateType(2);
            } else {
                rlLoginYzm.set(View.GONE);
                rlLoginPsd.set(View.VISIBLE);
                setGetOperateType(1);
            }
        }
    });
    //注册按钮
    public BindingCommand onRegister = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            setGetOperateType(3);
            rlLoginYzm.set(View.VISIBLE);
            rlLoginPsd.set(View.VISIBLE);
            rlLoginConfirmPsd.set(View.VISIBLE);
        }
    });

    //忘记密码按钮
    public BindingCommand onForgetPassword = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            setGetOperateType(4);
            rlLoginYzm.set(View.VISIBLE);
            rlLoginPsd.set(View.VISIBLE);
            rlLoginConfirmPsd.set(View.VISIBLE);
        }
    });

    private void setGetOperateType(int value) {
        OPERTA_TYPE = value;
        visibleValue.set(View.GONE);
        switch (OPERTA_TYPE) {
            case 1:
                getOperateType.set("登录");
                visibleValue.set(View.VISIBLE);
                loginType.set("验证码登录>>");
                break;
            case 2:
                getOperateType.set("登录");
                visibleValue.set(View.VISIBLE);
                loginType.set("密码登录>>");
                break;
            case 3:
                getOperateType.set("立即注册");
                break;
            case 4:
                getOperateType.set("确认");
                break;
        }
    }
}
