package AuthUser;

/**
 * Created by edison on 2017/10/22.
 * 用户信息,设置为静态，全局都能用
 */

public class User {
    public static String name="";
    public static int id;
    public static String image;
    public static int number;

    public Boolean Auth(){
        return false;
    }
}
