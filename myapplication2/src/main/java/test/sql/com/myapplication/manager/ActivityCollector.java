package test.sql.com.myapplication.manager;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/19.
 */
public class ActivityCollector {
        public static List<Activity> activityList=new ArrayList<Activity>();
/**
 * 添加Avtivity
 */
 public  static void addActivity(Activity activity){
     if(!activityList.contains(activity)){
         activityList.add(activity);


     }


 }
    /**
     * 删除Avtivity
     */
    public  static  void removeActivity(Activity activity){
        if(!activityList.contains(activity)){
        activityList.remove(activity);

        }


    }

    public  static  void finish(String activityName){
        int size=activityList.size();
        for (Activity activity:activityList){
            if(activityName.equals(activity.getClass().getSimpleName())){
                activity.finish();

            }


        }

    }
    /**
     * 关闭所有Avtivity
     */
    public static void finishAll(){

        for (Activity activity:activityList){

            activity.finish();

        }
        System.exit(0);
    }

}
