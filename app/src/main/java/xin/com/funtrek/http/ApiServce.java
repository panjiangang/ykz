package xin.com.funtrek.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;

/**
 * date:2018/1/19  14:21
 * author:Mr.XIn💕
 */


public interface ApiServce {

    @GET("/quarter/getVideos")
    Observable<HotBean> hot(@Query("uid") String i,@Query("type") String i1,@Query("page") String i3);

    @GET("quarter/getAd")
    Observable<RecBannerBean> getAd();

    @GET("quarter/getVideos")
    Observable<RecItemBean> getVideos(@Query("type") int type, @Query("page") int page);

}
