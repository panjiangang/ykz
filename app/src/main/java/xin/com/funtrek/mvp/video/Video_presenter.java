package xin.com.funtrek.mvp.video;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.bean.HotBean;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.recommend.Recommend_model;
import xin.com.funtrek.mvp.recommend.Recommend_view;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class Video_presenter extends BasePresenter<Video_view> {
    @Inject
    public Video_presenter() {

        DaggerIComponent.builder().iModule(new IModule()).build().injectVideo_presenter(this);

    }
    @Inject
    video_model mVideo_model;

    public void gethttp() {
        mVideo_model.getRetrofit(new Consumer<HotBean>() {
            @Override
            public void accept(HotBean hotBean) throws Exception {

                getView().HotSuccess(hotBean);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Video_presenterthrowable = " + throwable);
            }
        });
    }
}
