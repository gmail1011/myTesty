package test.sql.com.myapplication.manager;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.text.TextUtils;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import test.sql.com.myapplication.volley.VolleyCenter;


/**
 * Created by Administrator on 2015/6/19.
 */
public class App  extends Application{
    private static App instance;
    public RequestQueue requestQueue;
    public ImageLoader loader;
    private Member user;
    public RequestQueue queue;
    public static  boolean needLogin;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        VolleyCenter.getVolleyCenter().init(this);
        queue= Volley.newRequestQueue(this);
        String userAccout=getFromSharedPreferences(Constant.sp.login_name);
        if(userAccout==null&&!TextUtils.isEmpty(userAccout)){
            user=new Member();
            user.setMemberId(getFromSharedPreferences(Constant.sp.user_id));
            user.setName(getFromSharedPreferences(Constant.sp.username));
        }



    }
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void logout() {
        user = null;
        saveToSharedPreferences(Constant.sp.login_name,
                null);
        saveToSharedPreferences(Constant.sp.password,
                null);

        saveToSharedPreferences(Constant.sp.user_id,
                null);
        saveToSharedPreferences(Constant.sp.username,
                null);
        saveToSharedPreferences(Constant.sp.user_head_icon,
                null);

        needLogin = true;
    }

    public void showToast(int textId) {
        showToast(getString(textId));
    }

    public static synchronized App i() {
        return instance;
    }

    public Member getUser() {
        if(user == null) {
            needLogin = true;
        }
        return user;
    }

    /** 保存Object到sp */
    public void saveToSharedPreferences(String key, Object value) {
        SharedPreferences sp = getSharedPreferences(
                getString(R.String.app_name), Context.MODE_PRIVATE);
        sp.edit().putString(key, value.toString()).commit();
    }
    /**
     * 储存用户信息
     */
    /** 从sp获取object */

    public String getFromSharedPreferences(String key) {
        SharedPreferences sp = getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }

    public void saveStringToSharedPreference(String Key, String value) {
        SharedPreferences share = getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        share.edit().putString(Key, value).commit();
    }

    public void saveBooleanToSharedPreference(String Key, Boolean value) {
        SharedPreferences share = getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        share.edit().putBoolean(Key, value).commit();
    }

    public void saveToObjectPreference(String Key, Object value) {
        SharedPreferences share = getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        share.edit().putString(Key, value.toString()).commit();
    }

    public String getStringSharePreference(String Key) {
        SharedPreferences share = getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        return share.getString(Key, null);
    }

    public boolean getBooleanSharePreference(String Key) {
        SharedPreferences share = getSharedPreferences(
                getString(R.string.app_name), Context.MODE_PRIVATE);
        return share.getBoolean(Key, false);
    }
}
