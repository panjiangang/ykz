package xin.com.funtrek.http;


import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.http.bean.HotLikeBean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.http.bean.SessionBean;
import xin.com.funtrek.http.bean.UpPic;

/**
 * date:2018/1/19  14:21
 * author:Mr.XIn💕
 */


public interface ApiServce {

    @GET("/quarter/getHotVideos")
    Observable<HotBean> hot(@Query("page") String i, @Query("token") String i1);

    @GET("/quarter/praise")
    Observable<HotLikeBean> hotlike(@Query("uid") String i, @Query("wid") String i1, @Query("token") String i2);

    @GET("/quarter/getVideos")
    Observable<HotBean> hot(@Query("uid") String i, @Query("type") String i1, @Query("page") String i3);
    @GET("quarter/getAd")
    Observable<RecBannerBean> getAd();
    @GET("quarter/getVideos")
    Observable<RecItemBean> getVideos(@Query("type") int type, @Query("page") int page);
    @GET("quarter/getJokes")
    Flowable<SessionBean> sessionUrl(@QueryMap Map<String, String> map);
    //更改用户图片
    @Multipart
    @POST("file/upload")
    Flowable <UpPic> upPicture(@QueryMap Map<String, String> map,
                               @Part("file") RequestBody description,
                               @Part MultipartBody.Part file);
}
