package com.itboye.cardmanage.retrofit;

import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.utils.RxUtils;

public class AppUtils {
    /**
     * @param observable
     * @param provider
     * @param onSubscribe
     * @param apiDisposableObserver
     * @param <T>
     */
    public static <T> void requestData(Observable<T> observable, LifecycleProvider provider
            , Consumer<? super Disposable> onSubscribe
            , ApiDisposableObserver apiDisposableObserver) {
        observable.compose(RxUtils.schedulersTransformer())
                .compose(RxUtils.bindToLifecycle(provider))
                .compose(RxUtils.exceptionTransformer())
                .doOnSubscribe(onSubscribe)
                .subscribe(apiDisposableObserver);
    }
}
