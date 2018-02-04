package xin.com.funtrek.mvp.recommend;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.BaseDisposableObserver;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.Login_Success_Bean;
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

    public void getVideos(BaseDisposableObserver<RecItemBean> observer, int type, int page) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.getVideos(type, page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void getVideos(BaseDisposableObserver<RecItemBean> observer, String uid, String token, int type, int page) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.getVideos(uid, token, type, page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void getFavorites(BaseDisposableObserver<RecItemBean> observer, String uid, String token) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.getFavorites(uid, token);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void praise(BaseDisposableObserver<RecItemBean> observer, int uid1, int wid, String token1) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.praise(uid1, wid, token1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void addFavorite(BaseDisposableObserver<RecItemBean> observer, int uid1, int wid, String token1) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.addFavorite(uid1, wid, token1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void cancelFavorite(BaseDisposableObserver<RecItemBean> observer, int uid1, int wid, String token1) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.cancelFavorite(uid1, wid, token1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void getUserInfo(BaseDisposableObserver<Login_Success_Bean> observer, int uid) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<Login_Success_Bean> observable = apiService.getUserInfo(uid);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }

    public void getUserVideos(BaseDisposableObserver<RecItemBean> observer, int uid, int page) {
        ApiServce apiService = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Observable<RecItemBean> observable = apiService.getUserVideos(uid, page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(observer);
    }
}
