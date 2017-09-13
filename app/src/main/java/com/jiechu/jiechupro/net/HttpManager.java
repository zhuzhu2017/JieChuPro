package com.jiechu.jiechupro.net;

import com.trello.rxlifecycle.android.ActivityEvent;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 网络请求工具类
 * Created by allen on 2017/9/12.
 */

public class HttpManager {

    /*HttpManager对象*/
    private volatile static HttpManager INSTANCE;

    /*构造私有方法*/
    private HttpManager() {
    }

    /*获取单例对象*/
    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 网络请求，处理http请求
     *
     * @param baseApi 封装的请求数据
     */
    public void connToServer(BaseApi baseApi) {
        /*手动创建一个OkHttpClient并设置超时时间*/
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(baseApi.getConnTimeout(), TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .build();
                return chain.proceed(request);
            }
        });

        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseApi.getBaseUrl())
                .addConverterFactory(JsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        /*rxjava处理*/
        //加载框设置
        ProgressSubscriber subscriber = new ProgressSubscriber(baseApi);
        Observable observable = baseApi.getObservable(retrofit)
                /*生命周期管理*/
                .compose(baseApi.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.PAUSE))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(AndroidSchedulers.mainThread())
                /*结果判断*/
                .map(baseApi);
        //链接式对象返回
        SoftReference<HttpOnNextListener> httpOnNextListener = baseApi.getListener();
        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
            httpOnNextListener.get().onNext(observable);
        }
        //数据回调
        observable.subscribe(subscriber);
    }

}
