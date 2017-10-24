package AuthUser;

/**
 * Created by edison on 2017/10/22.
 * 用户信息,设置为静态，全局都能用
 */

public class User {
    public static String name;
    public static int phone_number;
    public static String image;
    public static String intro;
    public static int age;
    public static String head_url;
    public static int code=0;
    public static String password;
    public Boolean Auth(){
        return false;
    }
    public String Encrypt(String pass)
    {
        return pass;
    }
}
