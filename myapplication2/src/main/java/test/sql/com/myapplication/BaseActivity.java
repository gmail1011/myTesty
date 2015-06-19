package test.sql.com.myapplication;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

/**
 * Created by Administrator on 2015/6/18.
 */
public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
//类加载器
    public LayoutInflater inflater;
    public FragmentManager manager;

}
