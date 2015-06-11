package test.sql.com.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    @Override
    protected void setUp() throws Exception {
      super.setUp();
        System.out.print("-----setUp()------------>");
    }

    public ApplicationTest() {

        super(Application.class);


    }

    public  void  test(){


        System.out.print("----test-------");
    }
}