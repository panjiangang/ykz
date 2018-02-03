package xin.com.funtrek.utils;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.Release_Success_Bean;

/**
 * Created by lenovo on 2018/02/02.
 */

public interface Retrofit_Interface {
//    https://www.zhaoapi.cn/user/login?mobile=13020005705&password=123455
    @GET("/user/login")
    Call<Login_Success_Bean> login_success(@Query("mobile") String mobile, @Query("password") String password);

    //        https://www.zhaoapi.cn/quarter/publishJoke?uid=12229&content=发布的内容&token=E881C1A97438C6D81EE8FD14D6F7234A&source=android&appVersion=101
    @GET("/quarter/publishJoke")
    Call<Release_Success_Bean> release_success(@Query("uid") String uid, @Query("content") String content, @Query("token") String token, @Query("source") String source, @Query("appVersion") String appVersion);

}
