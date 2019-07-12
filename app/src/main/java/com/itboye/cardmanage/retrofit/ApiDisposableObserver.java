package com.itboye.cardmanage.retrofit;


import android.util.Log;
import com.google.gson.JsonSyntaxException;
import io.reactivex.observers.DisposableObserver;
import me.goldze.mvvmhabit.bus.RxBus;
import me.goldze.mvvmhabit.http.NetworkUtil;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.utils.ToastUtils;
import me.goldze.mvvmhabit.utils.Utils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import static com.itboye.cardmanage.retrofit.ApiDisposableObserver.CodeRule.CODE__1006;

/**
 * Created by goldze on 2017/5/10.
 * 统一的Code封装处理。该类仅供参考，实际业务逻辑, 根据需求来定义，
 */

public abstract class ApiDisposableObserver<T> extends DisposableObserver<T> {
    public abstract void onResult(T t, String msg, int code);

    public abstract void onError(int code, String msg);


    public abstract void dialogDismiss();

    public void onDataEmpty(String msg) {
    }

    @Override
    public void onComplete() {
        dialogDismiss();
    }

    @Override
    public void onError(Throwable e) {
        dialogDismiss();
        e.printStackTrace();
        KLog.e(e.getMessage());
        if (e.getCause() instanceof DataResultException) {
            DataResultException rError = (DataResultException) e.getCause();
            onError(rError.getCode(), rError.getMessage());
//            if (rError.getCode() != -2) {
            if (rError.getCode() == 1111) {
                //需要重新登录
                RxBus.getDefault().post(1111);
                ToastUtils.showShort("登录已过期，请重新登录");
                return;
            }
            if (rError.getMessage().equalsIgnoreCase("not exists")) {//未认证

            } else {
                ToastUtils.showShort(rError.getMessage());
            }
//            }
            return;
        } else if (e instanceof ResponseThrowable) {
            ResponseThrowable rError = (ResponseThrowable) e;
            if (rError.getCause() instanceof SocketTimeoutException) {
                ToastUtils.showShort("连接超时，请稍后重试");
                return;
            }
            if (rError.getCause() instanceof UnknownHostException) {
                ToastUtils.showShort("网络连接错误，请检查网络连接");
                return;
            }
            if (rError.getCause() instanceof JsonSyntaxException) {
                ToastUtils.showShort("数据解析失败");
                return;
            }
            ToastUtils.showShort(rError.getMessage());
            return;
        } else {

        }
        ToastUtils.showShort("网络异常");
    }

    @Override
    public void onStart() {
        super.onStart();
//        ToastUtils.showShort("http is start");
        // if  NetworkAvailable no !   must to call onCompleted
        if (!NetworkUtil.isNetworkAvailable(Utils.getContext())) {
            ToastUtils.showShort("无网络，读取缓存数据");
            onComplete();
        }
    }

    @Override
    public void onNext(Object o) {
        BaseResponse baseResponse = (BaseResponse) o;
        dialogDismiss();
        switch (baseResponse.getCode()) {
            //请求成功, 正确的操作方式
            case "200":
            case "0":
                if (baseResponse.getData() != null)
                    onResult((T) baseResponse.getData(), baseResponse.getMsg(), Integer.parseInt(baseResponse.getCode()));
                break;
            case CODE__1006:
                ToastUtils.showShort(baseResponse.getMsg());
                break;
//            case CodeRule.CODE__1:
//                // 请求成功, 正确的操作方式, 并消息提示
//                ToastUtils.showShort(baseResponse.getMeta().getMsg());
//                onDataEmpty(baseResponse.getMeta().getMsg());
//                break;
//            case CodeRule.CODE_300:
//                //请求失败，不打印Message
//                KLog.e("请求失败");
//                ToastUtils.showShort("错误代码:", baseResponse.getMeta().getCode());
//                break;
//            case CodeRule.CODE_330:
//                //请求失败，打印Message
//                ToastUtils.showShort(baseResponse.getMeta().getMsg());
//                break;
//            case CodeRule.CODE_500:
//                //服务器内部异常
//                ToastUtils.showShort("错误代码:", baseResponse.getMeta().getCode());
//                break;
//            case CodeRule.CODE_503:
//                //参数为空
//                KLog.e("参数为空");
//                break;
//            case CodeRule.CODE_502:
//                //没有数据
//                KLog.e("没有数据");
//                break;
//            case CodeRule.CODE_510:
//                //无效的Token，提示跳入登录页
//                ToastUtils.showShort("token已过期，请重新登录");
//                //关闭所有页面
//                AppManager.getAppManager().finishAllActivity();
//                //跳入登录界面
//                //*****该类仅供参考，实际业务Code, 根据需求来定义，******//
//                break;
//            case CodeRule.CODE_530:
//                ToastUtils.showShort("请先登录");
//                break;
//            case CodeRule.CODE_551:
//                ToastUtils.showShort("错误代码:", baseResponse.getMeta().getCode());
//                break;
//            case CodeRule.CODE__3:
//                onDataEmpty("-3");
//                break;
//            case CodeRule.CODE__4:
//                onDataEmpty("-4");
//                break;
            default:
                ToastUtils.showShort("错误代码:", baseResponse.getMsg());
                break;
        }
    }

    public static final class CodeRule {

        //请求成功, 正确的操作方式
        static final int CODE_200 = 200;
        static final int CODE_0 = 200;
        //请求成功, 消息提示
        static final int CODE__1 = -1;
        //请求失败，不打印Message
        static final int CODE_300 = 300;
        //请求失败，打印Message
        static final int CODE_330 = 330;
        //服务器内部异常
        static final int CODE_500 = 500;
        //参数为空
        static final int CODE_503 = 503;
        //没有数据
        static final int CODE_502 = 502;
        //无效的Token
        static final int CODE_510 = 510;
        //未登录
        static final int CODE_530 = 530;
        //请求的操作异常终止：未知的页面类型
        static final int CODE_551 = 551;
        static final String CODE__1006 = "1006";
    }
}