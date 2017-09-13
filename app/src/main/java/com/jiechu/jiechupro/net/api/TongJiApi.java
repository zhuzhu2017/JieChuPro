package com.jiechu.jiechupro.net.api;

import com.jiechu.jiechupro.Constants;
import com.jiechu.jiechupro.net.BaseApi;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by allen on 2017/9/13.
 */

public class TongJiApi extends BaseApi {

    private String w_sc;

    public String getW_sc() {
        return w_sc;
    }

    public void setW_sc(String w_sc) {
        this.w_sc = w_sc;
    }

    public TongJiApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCanCancelProgress(true);
        setBaseUrl(Constants.BASE_URL);
        setConnTimeout(10);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        JSONObject object = new JSONObject();
        try {
            object.put("w_sc", "100");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return httpPostService.tongJi(object);
    }

    @Override
    public Object call(Object o) {
        return o;
    }
}
