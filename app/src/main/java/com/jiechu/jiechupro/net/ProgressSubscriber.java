package com.jiechu.jiechupro.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * 用于在http请求开始时，自动显示一个加载框
 * 请求结束时关闭
 * Created by allen on 2017/8/24.
 */

public class ProgressSubscriber<T> extends Subscriber<T> {
    //是否显示加载框
    private boolean showProgress = true;
    //软引用回调接口
    private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
    //软引用防止内存泄漏
    private SoftReference<RxAppCompatActivity> mActivity;
    //fragment软引用
    private SoftReference<RxFragment> mFragment;
    //加载框对象——可以自定义
    private ProgressDialog dialog;
    //请求的封装数据
    private BaseApi baseApi;
    //是否是Activity
    private boolean isActivity;

    public ProgressSubscriber(BaseApi baseApi, boolean isActivity) {
        this.isActivity = isActivity;
        this.baseApi = baseApi;
        this.mSubscriberOnNextListener = baseApi.getListener();
        if (isActivity) {
            this.mActivity = new SoftReference<RxAppCompatActivity>(baseApi.getRxAppCompatActivity());
        } else {
            this.mFragment = new SoftReference<RxFragment>(baseApi.getRxFragment());
        }
        setShowProgress(baseApi.isShowProgress());
        if (baseApi.isShowProgress()) {   //设置加载框显示的时候，初始化加载框
            initProgressDialog(baseApi.isCanCancelProgress());
        }
    }

    /**
     * 初始化加载框
     *
     * @param canCancelProgress 是否能取消显示加载框
     */
    private void initProgressDialog(boolean canCancelProgress) {
        Context context = null;
        if (isActivity) {
            context = mActivity.get();
        } else {
            context = mFragment.get().getContext();
        }
        if (dialog == null && context != null) {
            dialog = new ProgressDialog(context);
            dialog.setCancelable(canCancelProgress);
            if (canCancelProgress) {
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        //触发联网取消动作
                        if (mSubscriberOnNextListener.get() != null) {
                            mSubscriberOnNextListener.get().onCancel();
                        }
                        onCancelProgress();
                    }
                });
            }
        }
    }

    /**
     * 取消加载框的时候取消对Observable的订阅，同时也取消了Http请求
     */
    private void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        //没有设置显示进度框的话直接返回
        if (!isShowProgress()) return;
        Context context = null;
        if (isActivity) {
            context = mActivity.get();
        } else {
            context = mFragment.get().getContext();
        }
        if (dialog == null || context == null) return;
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏加载框
     */
    private void dismissProgressDialog() {
        if (!isShowProgress()) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 订阅开始时调用
     * 显示加载框
     */
    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        dealError(e);
    }

    /**
     * 将onNext方法中返回的结果交给Activity或者Fragment自己处理
     *
     * @param t 创建Subscriber时候的泛型类型
     */
    @Override
    public void onNext(T t) {
        //触发请求结果回调
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onNext(t);
        }
    }

    /**
     * 统一处理异常信息
     *
     * @param e 异常
     */
    private void dealError(Throwable e) {
        Context context = null;
        if (isActivity) {
            context = mActivity.get();
        } else {
            context = mFragment.get().getContext();
        }
        if (context == null) return;
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
        }
        //触发错误回调
        if (mSubscriberOnNextListener.get() != null) {
            mSubscriberOnNextListener.get().onError(e);
        }
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }
}
