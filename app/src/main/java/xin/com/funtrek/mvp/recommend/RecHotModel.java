package xin.com.funtrek.mvp.recommend;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.BaseDisposableObserver;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;

/**
 * @author ddy
 */
public class RecHotModel {

    @Inject
    RecHotModel() {
    }

    public void getAd(BaseDisposableObserver<RecBannerBean> observer) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecBannerBean> observable = apiService.getAd();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void getVideos(BaseDisposableObserver<RecItemBean> observer) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.getVideos(1, 1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
