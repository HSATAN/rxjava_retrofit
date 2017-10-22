package api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by edison on 2017/10/21.
 */

public class RetrofitFactory {
   // public static String  baseUrl = "https://api.douban.com/v2/movie/";
    public static String  baseUrl ="http://192.168.0.106:9000/ajax/";
    public static Retrofit retrofit;
    static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    static OkHttpClient okHttpClient;
    public static Retrofit getRetrofit()
    {
        if(retrofit==null)
        {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
//不可以把retrofit作为单利模式，因为访问网络，可能同时有多个访问网络请求

}
