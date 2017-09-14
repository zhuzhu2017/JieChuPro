package com.jiechu.jiechupro.net.api;

import com.jiechu.jiechupro.net.BaseApi;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * 第一种工作票详情APi
 * Created by allen on 2017/9/14.
 */

public class PiaoDetailsApi extends BaseApi {

    private String keyValue;

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public PiaoDetailsApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        JSONObject object = new JSONObject();
        try {
            object.put("keyValue", getKeyValue());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return httpPostService.getPiaoDetails(object);
    }

    @Override
    public Object call(Object o) {
        return o;
    }
}
