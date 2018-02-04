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
import xin.com.funtrek.http.bean.ConcernBean;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.http.bean.HotLikeBean;
import xin.com.funtrek.http.bean.Login_Success_Bean;
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

    @GET("quarter/getVideos")
    Observable<RecItemBean> getVideos(@Query("uid") String uid, @Query("token") String token, @Query("type") int type, @Query("page") int page);

    @GET("quarter/getJokes")
    Flowable<SessionBean> sessionUrl(@QueryMap Map<String, String> map);

    @GET("quarter/getFavorites")
    Observable<RecItemBean> getFavorites(@Query("uid") String uid, @Query("token") String token);

    @GET("quarter/praise")
    Observable<RecItemBean> praise(@Query("uid") int uid1, @Query("wid") int wid, @Query("token") String token1);

    @GET("quarter/addFavorite")
    Observable<RecItemBean> addFavorite(@Query("uid") int uid1, @Query("wid") int wid, @Query("token") String token1);

    @GET("quarter/cancelFavorite")
    Observable<RecItemBean> cancelFavorite(@Query("uid") int uid1, @Query("wid") int wid, @Query("token") String token1);

    //更改用户图片
    @Multipart
    @POST("file/upload")
    Flowable<UpPic> upPicture(@QueryMap Map<String, String> map,
                              @Part("file") RequestBody description,
                              @Part MultipartBody.Part file);

    //关注列表
//    https://www.zhaoapi.cn/quarter/getFollowUsers?source=android&appVersion=101&token=C625F5867FC5F65790AAD571784C748F&uid=12272
    @GET("quarter/getFollowUsers")
    Flowable<ConcernBean> myConcern(@QueryMap() Map<String, String> map);

    @GET("user/getUserInfo")
    Observable<Login_Success_Bean> getUserInfo(@Query("uid") int uid);

    @GET("quarter/getUserVideos")
    Observable<RecItemBean> getUserVideos(@Query("uid") int uid, @Query("page") int page);
}
