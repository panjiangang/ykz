package xin.com.funtrek.mvp.video;

import javax.inject.Inject;

import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.mvp.recommend.Recommend_model;
import xin.com.funtrek.mvp.recommend.Recommend_view;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class Video_presenter extends BasePresenter<Video_view> {
    @Inject
    public Video_presenter() {
    }

    @Inject
    video_model mVideo_model;
}
