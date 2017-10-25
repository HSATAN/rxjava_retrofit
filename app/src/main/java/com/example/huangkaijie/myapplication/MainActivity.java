package com.example.huangkaijie.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import DataAdapter.Movie;
import DataAdapter.User;
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

import static AuthUser.User.phone_number;

public class MainActivity extends Activity {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.userinfo_button)
    Button userinfoButton;
    @BindView(R.id.userinfo_post_button)
    Button userinfoPostButton;
    @BindView(R.id.current_user_button)
    Button currentUserButton;
    long exitTime = System.currentTimeMillis();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("name").toString() + "\n" + intent.getStringExtra("password").toString());
        userinfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                request();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecylerActivity.class);
                startActivity(intent);
            }
        });
        userinfoPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
        currentUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userinfo= "";
                userinfo = userinfo+"\n 用户名："+ AuthUser.User.name;
                userinfo = userinfo+"\n id:"+ phone_number;
                textView.setText(userinfo);
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //连按两次返回键退出app
        if(keyCode == KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN)
        {
            if(System.currentTimeMillis()-exitTime>1000)//两次按键的时间
            {
                //Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                moveTaskToBack(false);
                exitTime = System.currentTimeMillis();
            }
            else{
                finish();

                System.exit(0);
            }
            return false;

        }
        return super.onKeyDown(keyCode, event);
    }
    public void post() {
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getUserInfoPost("1", "123456")
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Log.d("id: ", user.phone_number);
                        Log.d("name :", user.name);
                        Log.d("password: ", user.password);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void get() {

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        ApiService api = retrofit.create(ApiService.class);
        api.getUserInfo("http://47.93.24.159/ajax/userinfo/", AuthUser.User.phone_number, "password")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Log.d("name :", user.name);
                        String userinfo= "";
                        userinfo = userinfo+"\n 用户名："+ user.name;
                        textView.setText(userinfo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void request() {

        Retrofit retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getTopMovie(0, 10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DouBanObserver(this));


    }
}

class DouBanObserver implements Observer<Movie> {

    private MainActivity activity;

    public DouBanObserver(MainActivity mainActivity) {
        this.activity = mainActivity;

    }

    public void onSubscribe(@NonNull Disposable d) {
        Log.d("FragmentActivity", "onSubscrive");
    }


    public void onNext(@NonNull Movie movie) {
        Log.d("movie ", movie.getTitle());
        List<Movie.SubjectsBean> list = movie.getSubjects();
        String name = "";
        for (Movie.SubjectsBean sub : list) {
            Log.d("subbean", sub.getTitle());
            name += sub.getTitle() + "\n";

        }

        activity.textView.setText(name);

    }


    public void onError(@NonNull Throwable e) {
        Log.e("error :", "onError: " + e.getMessage());
    }


    public void onComplete() {
        Log.d("down :", "onComplete: Over!");
    }

}