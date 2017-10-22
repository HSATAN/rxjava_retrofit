package api;

import DataAdapter.User;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static android.R.attr.id;

/**
 * Created by edison on 2017/10/20.
 */

public interface Api {
    @GET("userinfo")//获取用户信息，get方法
    Observable<User> getUserInfo(@Query("id") String id, @Query("password") String password);

    @FormUrlEncoded
    @POST("userinfo_post")
    Observable<User> getUserInfoPost(@Field("id") String id,@Field("password") String password);

}
