package AuthUser;

/**
 * Created by edison on 2017/10/22.
 * 用户信息,设置为静态，全局都能用
 */

public class User {
    public static String name="huangkaijie ";
    public static int id=123456;
    public static String image="http:test.com";
    public static int number=999;

    public Boolean Auth(){
        return false;
    }
}
