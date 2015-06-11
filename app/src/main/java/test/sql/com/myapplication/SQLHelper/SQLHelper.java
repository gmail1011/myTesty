package test.sql.com.myapplication.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import test.sql.com.myapplication.bean.Person;

/**
 * Created by Administrator on 2015/6/11.
 */
public class SQLHelper {
    Context context;
    SQLiteOpen sqLiteOpen;
    SQLiteDatabase  databae;

    public  SQLHelper(Context context) {
        this.context=context;
        sqLiteOpen=new SQLiteOpen(context);

    }


    public void  insert(final Person person) {

        databae = sqLiteOpen.getWritableDatabase();
        if (databae.isOpen()) {
            databae.execSQL("insert into man(?,?)values", new Object[]{
                    person.getName(), person.getNumber()
            });
        }

    databae.close();
    }

}
