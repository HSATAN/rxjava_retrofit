package Func;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huangkaijie on 2017/10/25.
 */

public class Commom {

    public static boolean CheckPhoneNumber(String phone_number)
    {
        if (phone_number.length()!=11)
        {
            return false;
        }
        String reg = "\\d{11}";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(phone_number);
        return matcher.matches();
    }

}
