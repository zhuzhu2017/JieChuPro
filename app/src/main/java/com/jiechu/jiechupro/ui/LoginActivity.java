package com.jiechu.jiechupro.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jiechu.jiechupro.BaseActivity;
import com.jiechu.jiechupro.Constants;
import com.jiechu.jiechupro.JieChuApp;
import com.jiechu.jiechupro.R;
import com.jiechu.jiechupro.net.HttpManager;
import com.jiechu.jiechupro.net.HttpOnNextListener;
import com.jiechu.jiechupro.net.api.LoginApi;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 * Created by allen on 2017/9/7.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_ip)
    EditText etIp;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        String ipString = etIp.getText().toString();
        if (TextUtils.isEmpty(ipString)) {
            Constants.BASE_IP = etIp.getHint().toString();
        } else {
            Constants.BASE_IP = ipString;
        }
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            }
        } else {
            //开启登陆
            LoginApi loginApi = new LoginApi(loginResultListener, this);
            loginApi.setUsername(username);
            loginApi.setPassword(password);
            HttpManager.getInstance().connToServer(loginApi);
        }
    }

    HttpOnNextListener loginResultListener = new HttpOnNextListener<JSONObject>() {

        @Override
        public void onNext(JSONObject jsonObject) {
            if (jsonObject != null) {
                try {
                    String state = jsonObject.getString("state");
                    JieChuApp.token = "hd_loginuserkey_2016=" + jsonObject.getString("hd_loginuserkey_2016");
                    Toast.makeText(LoginActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    if (TextUtils.equals(state, Constants.REQUEST_SUCCESS)) {
                        //跳转到主页面
                        openActivity(MainActivity.class);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Toast.makeText(LoginActivity.this, "登录异常，请重试", Toast.LENGTH_SHORT).show();
        }
    };

}
