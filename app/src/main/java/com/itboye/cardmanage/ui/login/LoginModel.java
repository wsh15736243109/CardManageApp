package com.itboye.cardmanage.ui.login;

import android.app.Application;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import com.itboye.cardmanage.MainActivity;
import com.itboye.cardmanage.R;
import com.itboye.cardmanage.bean.UserInfoBean;
import com.itboye.cardmanage.retrofit.API;
import com.itboye.cardmanage.retrofit.ApiDisposableObserver;
import com.itboye.cardmanage.retrofit.AppUtils;
import com.itboye.cardmanage.retrofit.RetrofitClient;
import com.itboye.cardmanage.util.UserUtil;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.ToastUtils;

public class LoginModel extends BaseViewModel {

    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("15736243111");
    public ObservableField<String> userPassword = new ObservableField<>();
    public ObservableField<String> confirm_userPassword = new ObservableField<>();
    public ObservableField<String> userYzm = new ObservableField<>();
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
            if (isEmpty(userName)) {
                ToastUtils.showShort("手机号为空");
                return;
            }
            ToastUtils.showShort(OPERTA_TYPE+"");
            //分为验证码登录和密码登录
            if (OPERTA_TYPE == 1) {
                if (isEmpty(userYzm)) {
                    ToastUtils.showShort("验证码为空");
                    return;
                }
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).loginByCode(userName.get(), "token", "android", "android", userYzm.get(), "by_UserLoginSession_loginByMobileCode"),
                        getLifecycleProvider(), disposable -> showDialog(),

                        new ApiDisposableObserver() {
                            @Override
                            public void onResult(Object o, String msg) {
                                UserUtil.saveUser((UserInfoBean) o);
                                ToastUtils.showShort(msg);
                                startActivity(MainActivity.class);
                                finish();
                            }

                            @Override
                            public void dialogDismiss() {
                                dismissDialog();
                            }
                        });
            } else if (OPERTA_TYPE == 2) {
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).loginByPwd(userName.get(), "token", "android", "android", userPassword.get(), "by_UserLoginSession_loginByMobilePassword"),
                        getLifecycleProvider(), disposable -> showDialog(),

                        new ApiDisposableObserver() {
                            @Override
                            public void onResult(Object o, String msg) {
                                ToastUtils.showShort(msg);
                                startActivity(MainActivity.class);
                                finish();
                            }

                            @Override
                            public void dialogDismiss() {
                                dismissDialog();
                            }
                        });
            } else if (OPERTA_TYPE == 3) {
                if (isEmpty(userYzm)) {
                    ToastUtils.showShort("请输入验证码");
                    return;
                }
                if (isEmpty(userPassword)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }

                if (isEmpty(confirm_userPassword)) {
                    ToastUtils.showShort("请再次输入密码");
                    return;
                }
                if (isEqual(userPassword, confirm_userPassword)) {
                    ToastUtils.showShort("两次密码不一致");
                    return;
                }
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).register(userName.get(), confirm_userPassword.get(), userYzm.get(), "86", userPassword.get(), "by_UserLoginSession_registerByMobileCode"),
                        getLifecycleProvider(), disposable -> showDialog(),

                        new ApiDisposableObserver() {
                            @Override
                            public void onResult(Object o, String msg) {
                                ToastUtils.showShort(msg);
                                startActivity(MainActivity.class);
                                finish();
                            }

                            @Override
                            public void dialogDismiss() {
                                dismissDialog();
                            }
                        });
            } else if (OPERTA_TYPE == 4) {
                if (isEmpty(userYzm)) {
                    ToastUtils.showShort("验证码为空");
                    return;
                }
                if (isEmpty(userPassword)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                if (isEmpty(confirm_userPassword)) {
                    ToastUtils.showShort("请再次输入密码");
                    return;
                }
                if (isEqual(userPassword, confirm_userPassword)) {
                    ToastUtils.showShort("两次密码不一致");
                    return;
                }
                AppUtils.requestData(RetrofitClient.getInstance().create(API.class).forgetPasswordByCode(userName.get(), confirm_userPassword.get(), userYzm.get(), "86", userPassword.get(), "by_UserLoginSession_registerByMobileCode"),
                        getLifecycleProvider(), disposable -> showDialog(),

                        new ApiDisposableObserver() {
                            @Override
                            public void onResult(Object o, String msg) {
                                ToastUtils.showShort(msg);
                                startActivity(MainActivity.class);
                                finish();
                            }

                            @Override
                            public void dialogDismiss() {
                                dismissDialog();
                            }
                        });
            }

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
                rlLoginYzm.set(View.GONE);
                rlLoginPsd.set(View.VISIBLE);
                setGetOperateType(2);
            } else {
                rlLoginYzm.set(View.VISIBLE);
                rlLoginPsd.set(View.GONE);
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

    public UIChangeObser uc = new UIChangeObser();

    public class UIChangeObser {
        public ObservableBoolean registerCountDown = new ObservableBoolean(false);
        public ObservableBoolean forgetPasswordCountDown = new ObservableBoolean(false);
        public ObservableBoolean bindPhoneCountDown = new ObservableBoolean(false);
    }

    private void setGetOperateType(int value) {
        OPERTA_TYPE = value;
        visibleValue.set(View.GONE);
        switch (OPERTA_TYPE) {
            case 1:
                getOperateType.set("登录");
                visibleValue.set(View.VISIBLE);
                loginType.set("密码登录>>");
                break;
            case 2:
                getOperateType.set("登录");
                visibleValue.set(View.VISIBLE);
                loginType.set("验证码登录>>");
                break;
            case 3:
                getOperateType.set("立即注册");
                break;
            case 4:
                getOperateType.set("确认");
                break;
        }
    }

    //获取验证码
    public void getYzm() {
        if (isEmpty(userName)) {
            ToastUtils.showShort("请输入手机号码");
            return;
        }
        String codeType = "5";
        switch (OPERTA_TYPE) {
            case 1:
                codeType = "5";
                break;
            case 3:
                codeType = "1";
                break;
            case 4:
                codeType = "2";
                break;
        }
        AppUtils.requestData(RetrofitClient.getInstance().create(API.class).getSMSCode(userName.get(), codeType, "86", "by_SecurityCode_createAndSend"),
                getLifecycleProvider(), disposable -> showDialog(),

                new ApiDisposableObserver() {
                    @Override
                    public void onResult(Object o, String msg) {
                        ToastUtils.showShort(msg);
                        //获取验证码
                        uc.registerCountDown.set(true);
                    }

                    @Override
                    public void dialogDismiss() {
                        dismissDialog();
                    }
                });
    }

    public boolean isEmpty(ObservableField<String> value) {
        if (value.get() == null) {
            return true;
        }
        return value.get().isEmpty();
    }

    public boolean isEqual(ObservableField<String> value1, ObservableField<String> value2) {
        return value1.get().equals(value2);
    }

}
