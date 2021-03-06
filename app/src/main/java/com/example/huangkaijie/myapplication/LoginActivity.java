package com.example.huangkaijie.myapplication;

/**
 * Created by edison on 2017/10/21.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import DataAdapter.User;
import Func.Commom;
import MyActivity.BarActivity;
import MyActivity.RegisterActivity;
import api.ApiService;
import api.RetrofitFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static AuthUser.User.name;
import static android.R.id.button3;
import static api.RetrofitFactory.retrofit;
import static com.example.huangkaijie.myapplication.R.id.bt_pwd_clear;
import static com.example.huangkaijie.myapplication.R.id.bt_username_clear;
import static com.example.huangkaijie.myapplication.R.id.progress_circular;
import static com.example.huangkaijie.myapplication.R.id.textView;

public class LoginActivity extends AppCompatActivity {

    Boolean auth = false;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.contry_sn)
    TextView contrySn;
    @BindView(bt_username_clear)
    Button btUsernameClear;
    @BindView(R.id.username_layout)
    FrameLayout usernameLayout;
    @BindView(R.id.password)
    EditText password;
    @BindView(bt_pwd_clear)
    Button btPwdClear;
    @BindView(R.id.usercode_layout)
    FrameLayout usercodeLayout;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.forgive_pwd)
    Button forgivePwd;
    @BindView(R.id.register)
    Button register;
    @BindView(R.id.login_layout)
    RelativeLayout loginLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);
        ButterKnife.bind(this);
        initUser();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  register_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(register_intent);

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if ("".equals(user) || "".equals(pwd)) {
                    login.setBackgroundColor(getResources().getColor(R.color.grey,getTheme()));
                    login.setEnabled(false);
                    // 用户名为空,设置按钮不可见
                } else {
                    // 用户名不为空，设置按钮可见
                    login.setEnabled(true);
                    //改变按钮背景颜色
                    login.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
                }
            }
        });

        //savedInstanceState.putString("name",editText2.getText().toString());
        //savedInstanceState.putString("password",editText3.getText().toString());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                post();

            }
        });
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String user = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                if ("".equals(user) || "".equals(pwd)) {
                    login.setBackgroundColor(getResources().getColor(R.color.grey,getTheme()));
                    login.setEnabled(false);
// 用户名为空,设置按钮不可见
                } else {
// 用户名不为空，设置按钮可见
                    login.setEnabled(true);
                    login.setBackgroundColor(getResources().getColor(R.color.colorPrimary,getTheme()));
                }
            }
        });
        //savedInstanceState.putString("name",editText2.getText().toString());
        //savedInstanceState.putString("password",editText3.getText().toString());
    }
    public void post() {
        String phone_number = username.getText().toString();
        String psword = password.getText().toString();
        if(!Commom.CheckPhoneNumber(phone_number))
        {
            Log.d("参数错误", phone_number);
            return;
        }
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.AuthUser(phone_number, psword)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onNext(@NonNull User user) {
                            if (user.code == 10000) {
                                auth = true;
                                AuthUser.User.phone_number = user.phone_number;
                                name = user.name;
                                AuthUser.User.age = user.age;
                                AuthUser.User.head_url = user.head_url;
                                AuthUser.User.intro = user.intro;
                                AuthUser.User.code = user.code;
                                Log.d("name :", user.name);
                                Log.d("age: ", user.phone_number);
                                saveUser(user);
                            } else {
                                Log.d("error-----", "发生错误");
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("network error",e.toString());
                    }

                    @Override
                    public void onComplete() {
                    Login();
                    }
                });
    }


    public void Login()
    {
        Log.d("auth-------------", Integer.toString(AuthUser.User.code));
        if(AuthUser.User.code==10000)
        {
            //activity传递参数的两种方法，一是放在buddle中，二是直接放在intent中

                Intent intent = new Intent(LoginActivity.this, BarActivity.class);
                intent.putExtra("name", username.getText().toString());
                intent.putExtra("password", password.getText().toString());
                startActivity(intent);
                finish();

        }
    }
    public void initUser()
    {
        //读取缓存的用户信息，
        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String phone_number = sharedPreferences.getString("phone_number","");
        String passw = sharedPreferences.getString("password","");
        Log.d("password", passw);
        Log.d("phone_number", phone_number);
        if(!phone_number.equals(""))
        {
            Log.d("password", passw);
            username.setText(phone_number);
            password.setText(passw);
        }
    }
    public void saveUser(User user)
    {
        //保存用户信息
        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("name", user.name);
        editor.putString("password",user.password);
        editor.putString("phone_number",user.phone_number);
        editor.commit();
    }
}

