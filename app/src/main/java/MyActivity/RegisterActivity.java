package MyActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.huangkaijie.myapplication.LoginActivity;
import com.example.huangkaijie.myapplication.R;

import api.ApiService;
import api.RetrofitFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static api.RetrofitFactory.retrofit;
import static com.example.huangkaijie.myapplication.R.id.login;
import static com.example.huangkaijie.myapplication.R.id.username;

/**
 * Created by edison on 2017/10/24.
 */

public class RegisterActivity extends Activity {


    @BindView(username)
    EditText usernameEdit;
    @BindView(R.id.bt_username_clear)
    Button btUsernameClear;
    @BindView(R.id.username_layout)
    FrameLayout usernameLayout;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;
    @BindView(R.id.bt_phone_clear)
    Button btPhoneClear;
    @BindView(R.id.phone_layout)
    FrameLayout phoneLayout;
    @BindView(R.id.password)
    EditText passwordEdit;
    @BindView(R.id.bt_pwd_clear)
    Button btPwdClear;
    @BindView(R.id.password_layout)
    FrameLayout passwordLayout;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login_layout)
    RelativeLayout loginLayout;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.register_layout);
        ButterKnife.bind(this);
        usernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String user = usernameEdit.getText().toString().trim();
                String pwd = passwordEdit.getText().toString().trim();
                String phone = phoneEdit.getText().toString().trim();
                if ("".equals(user) || "".equals(pwd) || "".equals(phone)) {
                    register.setBackgroundColor(getResources().getColor(R.color.grey,getTheme()));
                    register.setEnabled(false);
                    // 用户名为空,设置按钮不可见
                } else {
                    // 用户名不为空，设置按钮可见
                    register.setEnabled(true);
                    //改变按钮背景颜色
                    register.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
                }
            }
        });
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String user = usernameEdit.getText().toString().trim();
                String pwd = passwordEdit.getText().toString().trim();
                String phone = phoneEdit.getText().toString().trim();
                if ("".equals(user) || "".equals(pwd) || "".equals(phone)) {
                    register.setBackgroundColor(getResources().getColor(R.color.grey,getTheme()));
                    register.setEnabled(false);
                    // 用户名为空,设置按钮不可见
                } else {
                    // 用户名不为空，设置按钮可见
                    register.setEnabled(true);
                    //改变按钮背景颜色
                    register.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
                }
            }
        });
        phoneEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String user = usernameEdit.getText().toString().trim();
                String pwd = passwordEdit.getText().toString().trim();
                String phone = phoneEdit.getText().toString().trim();
                if ("".equals(user) || "".equals(pwd) || "".equals(phone)) {
                    register.setBackgroundColor(getResources().getColor(R.color.grey,getTheme()));
                    register.setEnabled(false);
                    // 用户名为空,设置按钮不可见
                } else {
                    // 用户名不为空，设置按钮可见
                    register.setEnabled(true);
                    //改变按钮背景颜色
                    register.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = usernameEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                String phone_number = phoneEdit.getText().toString();
                if(name.equals("") || password.equals("") || phone_number.equals(""))
                {

                }
                Retrofit retrofit = RetrofitFactory.getRetrofit();
                ApiService apiService = retrofit.create(ApiService.class);
                apiService.Register(name,phone_number,password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull Integer integer) {
                                if (integer==10000)
                                {
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });
    }
}
