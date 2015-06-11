package test.sql.com.myapplication.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2015/6/11.
 */
public class SQLiteOpen  extends SQLiteOpenHelper {
    public SQLiteOpen(Context context) {
        super(context, "person",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table man" +
                "(_id integer primary key,name varchar(20),age integer(10)); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
