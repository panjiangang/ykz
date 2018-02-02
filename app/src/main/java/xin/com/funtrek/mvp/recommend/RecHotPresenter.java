package xin.com.funtrek.mvp.recommend;

import javax.inject.Inject;

import xin.com.funtrek.http.BaseDisposableObserver;
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
                getIView().successVideos(recommendBean);
            }
        };
        add(observer);

        recHotModel.getVideos(observer);
    }
}
