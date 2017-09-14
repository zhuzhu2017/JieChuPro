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
 * Created by allen on 2017/9/14.
 */

public class XCPicApi extends BaseApi {

    private String gzpid;
    private String mid;

    public String getGzpid() {
        return gzpid;
    }

    public void setGzpid(String gzpid) {
        this.gzpid = gzpid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public XCPicApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        JSONObject object = new JSONObject();
        try {
            object.put("gzpid", getGzpid());
            object.put("mid", getMid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return httpPostService.getXCPicData(object);
    }

    @Override
    public Object call(Object o) {
        return o;
    }
}
