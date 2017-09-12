package com.jiechu.jiechupro.net.api;

import com.jiechu.jiechupro.JieChuApp;
import com.jiechu.jiechupro.net.BaseApi;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.HttpPostService;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by allen on 2017/9/12.
 */

public class LoginApi extends BaseApi {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCanCancelProgress(true);
        setBaseUrl(JieChuApp.BASE_URL);
        setConnTimeout(10);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        JSONObject object = new JSONObject();
        try {
            object.put("username", getUsername());
            object.put("password", getPassword());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return httpPostService.login(object);
    }

    @Override
    public Object call(Object o) {
        return o;
    }
}
