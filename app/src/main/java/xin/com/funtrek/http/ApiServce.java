package xin.com.funtrek.http;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import xin.com.funtrek.bean.HotBean;

/**
 * date:2018/1/19  14:21
 * author:Mr.XIn💕
 */


public interface ApiServce {

    @GET("/quarter/getVideos")
    Observable<HotBean> hot(@Query("uid") String i,@Query("type") String i1,@Query("page") String i3);

}
