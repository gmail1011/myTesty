package test.sql.com.myapplication.bean;

/**
 * Created by Administrator on 2015/6/11.
 */
public class Person {

    private String name;
    private String password;
    private  int number;

    public Person(String name, String password,  int number) {
        this.name = name;
        this.password = password;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  int getNumber() {
        return number;
    }

    public void setNumber( int number) {
        this.number = number;
    }
}
