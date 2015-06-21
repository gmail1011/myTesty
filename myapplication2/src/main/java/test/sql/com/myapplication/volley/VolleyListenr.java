package test.sql.com.myapplication.volley;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2015/6/21.
 */
public interface VolleyListenr  {
    /*
    * 解析成功接口
    * */
    void onParse(String response);
    /*
    解析失败接口
    * */
    void onError(VolleyError error);
    /*响应接口
    * */
    void onResponse();
}
