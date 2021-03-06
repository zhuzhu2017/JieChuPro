package com.jiechu.jiechupro.net;

import com.jiechu.jiechupro.Constants;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle.components.support.RxFragment;

import org.json.JSONObject;

import java.lang.ref.SoftReference;

import retrofit2.Retrofit;
import rx.Observable;
import rx.functions.Func1;

/**
 * 请求数据统一封装类
 * Created by allen on 2017/8/23.
 */

public abstract class BaseApi<T> implements Func1<T, T> {
    /*rx与activity绑定，管理生命周期，软引用防止内存泄漏*/
    private SoftReference<RxAppCompatActivity> rxAppCompatActivity;
    /*rx与Fragment绑定*/
    private SoftReference<RxFragment> rxFragment;
    /*请求结果回调*/
    private SoftReference<HttpOnNextListener> listener;
    /*是否能取消加载框*/
    private boolean canCancelProgress;
    /*是否显示加载框*/
    private boolean showProgress;
    /*是否需要缓存处理*/
    private boolean cacheNeeded;
    /*基础URL*/
    private String baseUrl;
    /*方法--如果设置需要缓存，这个参数必须设置，否则不需要*/
    private String methodName;
    /*联网超时时间*/
    private int connTimeout = 10;   //默认10秒
    /*有网络情况下本地缓存时间默认60秒，60秒后重新请求接口并保存*/
    private int cookieNetWorkTime = 60;
    /*无网络情况下本地缓存默认30天，30天后失效，并清空本地保存的数据*/
    private int cookieNoNetWorkTime = 30 * 24 * 60 * 60;
    /*失败后重试的次数*/
    private int retryCount = 1;
    /*失败后retry的延迟时间--过去对应时间后再走一次请求*/
    private int retryDelay = 100;
    /*失败后retry叠加延迟--失败一次延迟请求时间叠加一次*/
    private int retryIncreaseDelay = 10;
    /*缓存URL--可手动设置*/
    private String cacheUrl;

    /*构造函数RxAppCompatActivity*/
    public BaseApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        setListener(listener);
        setRxAppCompatActivity(rxAppCompatActivity);
        /*默认显示加载框和需要缓存处理*/
        setShowProgress(true);
        setCanCancelProgress(true);
        setBaseUrl("http://" + Constants.BASE_IP + "/Api/");
        setConnTimeout(10);
    }

    /*构造函数RxFragment*/
    public BaseApi(HttpOnNextListener listener, RxFragment rxFragment) {
        setListener(listener);
        setRxFragment(rxFragment);
        /*默认显示加载框和需要缓存处理*/
        setShowProgress(true);
        setCanCancelProgress(true);
        setBaseUrl("http://" + Constants.BASE_IP + "/Api/");
        setConnTimeout(10);
    }

    @Override
    public T call(T t) {
        return t;
    }

    public RxFragment getRxFragment() {
        return rxFragment.get();
    }

    public void setRxFragment(RxFragment rxFragment) {
        this.rxFragment = new SoftReference<RxFragment>(rxFragment);
    }

    /**
     * 根据Retrofit对象设置Observable
     *
     * @param retrofit 创建的Retrofit对象
     * @return Observable对象
     */
    public abstract Observable getObservable(Retrofit retrofit);

    /*获取当前rx生命周期*/
    public RxAppCompatActivity getRxAppCompatActivity() {
        return rxAppCompatActivity.get();
    }

    public void setRxAppCompatActivity(RxAppCompatActivity rxAppCompatActivity) {
        this.rxAppCompatActivity = new SoftReference<RxAppCompatActivity>(rxAppCompatActivity);
    }

    public SoftReference<HttpOnNextListener> getListener() {
        return listener;
    }

    public void setListener(HttpOnNextListener listener) {
        this.listener = new SoftReference<HttpOnNextListener>(listener);
    }

    public boolean isCanCancelProgress() {
        return canCancelProgress;
    }

    public void setCanCancelProgress(boolean canCancelProgress) {
        this.canCancelProgress = canCancelProgress;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    public void setShowProgress(boolean showProgress) {
        this.showProgress = showProgress;
    }

    public boolean isCacheNeeded() {
        return cacheNeeded;
    }

    public void setCacheNeeded(boolean cacheNeeded) {
        this.cacheNeeded = cacheNeeded;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public int getConnTimeout() {
        return connTimeout;
    }

    public void setConnTimeout(int connTimeout) {
        this.connTimeout = connTimeout;
    }

    public int getCookieNetWorkTime() {
        return cookieNetWorkTime;
    }

    public void setCookieNetWorkTime(int cookieNetWorkTime) {
        this.cookieNetWorkTime = cookieNetWorkTime;
    }

    public int getCookieNoNetWorkTime() {
        return cookieNoNetWorkTime;
    }

    public void setCookieNoNetWorkTime(int cookieNoNetWorkTime) {
        this.cookieNoNetWorkTime = cookieNoNetWorkTime;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getRetryDelay() {
        return retryDelay;
    }

    public void setRetryDelay(int retryDelay) {
        this.retryDelay = retryDelay;
    }

    public int getRetryIncreaseDelay() {
        return retryIncreaseDelay;
    }

    public void setRetryIncreaseDelay(int retryIncreaseDelay) {
        this.retryIncreaseDelay = retryIncreaseDelay;
    }

    public String getCacheUrl() {
        return cacheUrl;
    }

    public void setCacheUrl(String cacheUrl) {
        this.cacheUrl = cacheUrl;
    }

    /*在没有手动设置URL的情况下，简单拼接*/
    public String getUrl() {
        if (null == getCacheUrl() || "".equals(getCacheUrl())) {
            return getBaseUrl() + getMethodName();
        }
        return getCacheUrl();
    }
}
