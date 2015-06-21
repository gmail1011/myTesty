package test.sql.com.myapplication.volley;

import android.content.Context;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import test.sql.com.myapplication.manager.App;
import test.sql.com.myapplication.net.Request;
import test.sql.com.myapplication.utils.NetworkUtils;

/**
 * Created by Administrator on 2015/6/21.
 */
public class VolleyCenter  {
    private RequestQueue requestQueue;
    private Context context;
    private VolleyCenter(){}
    private String baseUrl = "http://tapi.clejw.com/mobile/interface.do?";
    /*
    * 私有化构造方法
    * */
    public static VolleyCenter getVolleyCenter(){

        return  instanceHodler.volleyCenter;

    }

    public void init(Context context){
        this.context=context;
        requestQueue= Volley.newRequestQueue(context);



    }

    private static  class  instanceHodler{
        public static VolleyCenter volleyCenter;
        static {
        volleyCenter=new VolleyCenter();

        }

    }
    public void addGetRequst(Request request,VolleyListenr listenr){
     if(requestQueue==null){
         return;
     }
    if(NetworkUtils.isDisconnected(context)){
        App.i().showToast("网络连接异常");
        return;
    }


        StringBuffer urlBuffer = new StringBuffer(baseUrl);
        urlBuffer.append("content=");


        try {
            String Content= URLEncoder.encode(request.toJson().toString(),"utf-8");

            urlBuffer.append(Content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = urlBuffer.toString();
        VolleyRequst volleyRequest = new VolleyRequst(com.android.volley.Request.Method.POST, url,listenr);
        requestQueue.add(volleyRequest);


    }
}
