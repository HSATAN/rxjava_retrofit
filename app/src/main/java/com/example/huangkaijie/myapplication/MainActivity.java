package com.example.huangkaijie.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import DataAdapter.Movie;
import DataAdapter.Subjects;
import api.ApiService;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public Button button;
    public TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        button = (Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        initOkHttp();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                request();
            }
        });
    }
    OkHttpClient.Builder httpBuilder;
    private void initOkHttp() {
        httpBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpBuilder.addInterceptor(httpLoggingInterceptor);
        }
       // httpBuilder.addInterceptor(new ResponseInterceptor());
        httpBuilder.connectTimeout(15, TimeUnit.SECONDS);
    }
    public void request()
    {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getTopMovie(0,10).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("FragmentActivity", "onSubscrive");
                    }

                    @Override
                    public void onNext(@NonNull Movie movie) {
                        Log.d("movie ", movie.getTitle());
                        List<Movie.SubjectsBean> list = movie.getSubjects();
                        for(Movie.SubjectsBean sub:list)
                        {
                            Log.d("subbean",sub.getTitle());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("error :", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("down :", "onComplete: Over!");
                    }
                });


    }
}
