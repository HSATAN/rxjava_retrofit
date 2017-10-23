package MyActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.huangkaijie.myapplication.R;

import AuthUser.User;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangkaijie on 2017/10/23.
 */

public class PersonalActivity extends Activity {

    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.personal_layout);
        ButterKnife.bind(this);
        String userinfo = "";
        userinfo = userinfo  + "\n" + "用户名： " +  User.name;
        userinfo = userinfo  + "\n" + "ID： " + User.id;
        userinfo = userinfo  + "\n" + "图片地址： " + User.image;
        userinfo = userinfo  + "\n" + "number： " + User.number;
        textView2.setText(userinfo);
    }
}
