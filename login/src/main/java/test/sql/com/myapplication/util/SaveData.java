package test.sql.com.myapplication.util;

import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2015/6/12.
 */
public class SaveData {
    void saveData(String name,String value){
        if(Environment.MEDIA_MOUNTED==Environment.getExternalStorageState()){
            FileOutputStream fileOutputStream;
            try {
               fileOutputStream=new FileOutputStream(Environment.getDataDirectory().getPath()+"sav.txt");
                String message=name+"##"+value;
                fileOutputStream.write(message.getBytes());

                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }finally {

            }


        }



    }



}
