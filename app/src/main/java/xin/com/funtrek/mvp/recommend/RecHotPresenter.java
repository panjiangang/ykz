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

    public void getVideos(final int page) {

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

    public void onRefresh() {
        page = 1;
        getVideos(page);
    }

    public void onLoadmore() {
        page++;
        getVideos(page);
    }
}
