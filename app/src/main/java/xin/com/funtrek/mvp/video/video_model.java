package xin.com.funtrek.mvp.video;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class video_model {

    @Inject
    public video_model() {

    }

    public void  getRetrofit(Consumer<HotBean> consumer,Consumer<Throwable> throwable){

        RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn", ApiServce.class)
                .hot(108+"",1+"",1+"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer,throwable);
    }
}
