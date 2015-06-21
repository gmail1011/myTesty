package test.sql.com.myapplication.volley;

import android.text.TextUtils;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import test.sql.com.myapplication.manager.App;
import test.sql.com.myapplication.net.Response;

/**
 * Created by Administrator on 2015/6/21.
 */
public abstract  class VolleyCenterImpl<T extends Response> implements  VolleyListenr {
    private T mResponse;
    public  VolleyCenterImpl(T t){
        this.mResponse=t;

    }
    @Override
    public void onParse(String response) {
        try {
            JSONObject jsonObject=new JSONObject(response);
            String flag=jsonObject.getString("flag");
            if(flag!=null&&flag.equals("success")){
            mResponse.setSuccess(true);

            }


            if(mResponse.isSuccess()){
                String contentType=mResponse.getContentTag();
                if(!TextUtils.isEmpty(contentType)){
                this.mResponse.parseContent(jsonObject.getString(contentType));


                }

                String[] contentTypes=mResponse.getContentTags();
                if(contentTypes!=null&&contentTypes.length>0){

                    for (int i = 0; i < contentTypes.length; i++) {
                        this.mResponse.parseContent(jsonObject
                                .getString(contentTypes[i]),i);
                    }

                }


            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {

    }

    @Override
    public void onResponse() {
        delverResponse(mResponse);
        if(!mResponse.isSuccess()){
          App.i().showToast(mResponse.getErrorMsg());
        }

    }
    public abstract  void delverResponse(Response response);
}
