package test.sql.com.myapplication.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/6/21.
 */
public class VolleyRequst extends StringRequest {
    private VolleyListenr volleyListener;
    public VolleyRequst(int method, String url, final VolleyListenr listenr) {
        super(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if(listenr!=null){
                    listenr.onResponse();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(listenr!=null){
                    listenr.onError(volleyError);
                }

            }
        });
        volleyListener=listenr;
    }

    @Override
    protected void deliverResponse(String response) {
        super.deliverResponse(response);
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String newstr;

        try {
            newstr =new String(response.data,"utf-8");
        } catch (UnsupportedEncodingException e) {
            newstr = new String(response.data);
        }
        if (volleyListener != null) {
            volleyListener.onParse(newstr);
        }

        return Response.success(newstr, HttpHeaderParser.parseCacheHeaders(response));
    }


}
