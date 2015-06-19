package test.sql.com.myapplication.manager;

import android.app.Application;
import android.media.Image;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by Administrator on 2015/6/19.
 */
public class App  extends Application{
    private static App instance;
    public RequestQueue requestQueue;
    public ImageLoader loader;
}
