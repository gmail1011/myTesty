package test.sql.com.myapplication.activity.welcom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.zip.Inflater;

import test.sql.com.myapplication.BaseActivity;
import test.sql.com.myapplication.R;
import test.sql.com.myapplication.manager.App;

/**
 * Created by Administrator on 2015/6/21.
 */
public class MainActivity  extends Activity{
    private Animation animation;
    private boolean first;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view= LayoutInflater.from(this).inflate(R.layout.activity_main_my);
        setContentView(view);


    }

    @Override
    protected void onResume() {
        into();

        super.onResume();



    }

    public void into(){
        // if (netManager.isOpenNetwork()) {
        // 如果网络可用则判断是否第一次进入，如果是第一次则进入欢迎界面
        first = App.i().getBooleanSharePreference("First");
        // 设置动画效果是alpha，在anim目录下的alpha.xml文件中定义动画效果
        animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        // 给view设置动画效果
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            // 这里监听动画结束的动作，在动画结束的时候开启一个线程，这个线程中绑定一个Handler,并
            // 在这个Handler中调用goHome方法，而通过postDelayed方法使这个方法延迟500毫秒执行，达到
            // 达到持续显示第一屏500毫秒的效果
            @Override
            public void onAnimationEnd(Animation arg0) {
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        Intent intent;
                        // 如果第一次，则进入引导页WelcomeActivity
                        if (first) {
                            intent = new Intent(MainActivity.this,
                                    WelcomeActivity.class);
                        } else {
                            // intent = new
                            // Intent(MainActivity.this,com.sinzk.pkeggs.activities.MainActivityGroup.class);
                            intent = new Intent(
                                    MainActivity.this,
                                    MainFragmentActivity.class);
                        }
                        startActivity(intent);
                        // 设置Activity的切换效果
                        overridePendingTransition(R.anim.in_from_right,
                                R.anim.out_to_left);
                        MainActivity.this.finish();
                    }
                }, 1000);
            }
        });

    }
}
