package com.example.huangkaijie.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                Intent intent = new Intent(MainActivity.this, RecyleActivity.class);
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
                userinfo = userinfo+"\n id:"+ AuthUser.User.id;
                textView.setText(userinfo);
            }
        });

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
                        Log.d("id: ", Integer.toString(user.id));
                        Log.d("name :", user.name);
                        Log.d("age: ", user.age);
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
        api.getUserInfo("http://192.168.0.106:9000/ajax/userinfo/", "1", "password")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Log.d("id: ", Integer.toString(user.id));
                        Log.d("name :", user.name);
                        Log.d("age: ", user.age);
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