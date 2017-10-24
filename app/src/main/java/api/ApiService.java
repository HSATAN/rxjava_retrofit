package api;

import DataAdapter.Movie;
import DataAdapter.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

import static android.R.attr.id;

/**
 * Created by huangkaijie on 2017/10/20.
 */

public interface ApiService {
    @GET("douban")
    Observable<Movie> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET//获取用户信息，get方法,@Url字段，动态加载Url
    Observable<User> getUserInfo(@Url String url,@Query("id") String id, @Query("password") String password);


    @FormUrlEncoded
    @POST("userinfo_post")//post方法
    Observable<User> getUserInfoPost(@Field("id") String id, @Field("password") String password);


    @FormUrlEncoded
    @POST("auth")
    Observable<User> AuthUser(@Field("phone_number") String id, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Observable<Integer> Register(@Field("name") String name, @Field("phone_number") String phone_number, @Field("password") String password);
}
