package xin.com.funtrek.mvp.recommend;

import javax.inject.Inject;

import xin.com.funtrek.http.BaseDisposableObserver;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;

/**
 * @author ddy
 */
public class RecHotPresenter extends DdyPresenter<RecHotIView> {

    @Inject
    RecHotModel recHotModel;
    int page = 1;

    @Inject
    RecHotPresenter() {
        DaggerIComponent.builder().iModule(new IModule()).build().inject(this);
    }

    public void getAd() {

        BaseDisposableObserver<RecBannerBean> observer = new BaseDisposableObserver<RecBannerBean>() {
            @Override
            public void onNext(RecBannerBean recBannerBean) {
                super.onNext(recBannerBean);
                getIView().successAd(recBannerBean);
            }
        };
        add(observer);

        recHotModel.getAd(observer);
    }

    public void getVideos() {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successVideos(recommendBean, page);
            }
        };
        add(observer);

        recHotModel.getVideos(observer, 1, page);
    }

    public void getVideos(String uid, String token) {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successVideos(recommendBean, page);
            }
        };
        add(observer);

        recHotModel.getVideos(observer, uid, token, 1, page);
    }

    public void getFavorites(String uid, String token) {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successVideos(recommendBean, 1);
            }
        };
        add(observer);

        recHotModel.getFavorites(observer, uid, token);
    }

    public void praise(int uid1, int wid, String token1) {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successToast(recommendBean);
            }
        };
        add(observer);

        recHotModel.praise(observer, uid1, wid, token1);
    }

    public void addFavorite(int uid1, int wid, String token1) {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successToast(recommendBean);
            }
        };
        add(observer);

        recHotModel.addFavorite(observer, uid1, wid, token1);
    }

    public void cancelFavorite(int uid1, int wid, String token1) {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successToast(recommendBean);
            }
        };
        add(observer);

        recHotModel.cancelFavorite(observer, uid1, wid, token1);
    }

    public void getUserInfo(int uid) {

        BaseDisposableObserver<Login_Success_Bean> observer = new BaseDisposableObserver<Login_Success_Bean>() {
            @Override
            public void onNext(Login_Success_Bean loginSuccessBean) {
                super.onNext(loginSuccessBean);
                getIView().successUser(loginSuccessBean);
            }
        };
        add(observer);

        recHotModel.getUserInfo(observer, uid);
    }

    public void getUserVideos(int uid) {

        BaseDisposableObserver<RecItemBean> observer = new BaseDisposableObserver<RecItemBean>() {
            @Override
            public void onNext(RecItemBean recommendBean) {
                super.onNext(recommendBean);
                getIView().successVideos(recommendBean, page);
            }
        };
        add(observer);

        recHotModel.getUserVideos(observer, uid, page);
    }

    public void onRefresh() {
        page = 1;
        getVideos();
    }

    public void onRefresh(String uid, String token) {
        page = 1;
        getVideos(uid, token);
    }

    public void onRefresh(int uid) {
        page = 1;
        getUserVideos(uid);
    }

    public void onLoadmore() {
        page++;
        getVideos();
    }

    public void onLoadmore(String uid, String token) {
        page++;
        getVideos(uid, token);
    }

    public void onLoadmore(int uid) {
        page = 1;
        getUserVideos(uid);
    }
}
