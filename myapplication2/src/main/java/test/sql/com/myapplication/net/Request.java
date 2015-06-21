package test.sql.com.myapplication.net;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/6/21.
 */
public abstract  class Request  {
    private String type;

    public Request(String type){
        this.type=type;
    }

    public String getType(){
        return  type;
    }

    public JSONObject toJson(){
        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("type",type);
            parseJson(jsonObject);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    return  jsonObject;
    }
    public abstract void parseJson(JSONObject json);
}
