package xin.com.funtrek.mvp.recommend;

import javax.inject.Inject;

import xin.com.funtrek.base.BasePresenter;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class Recommend_presenter extends BasePresenter<Recommend_view> {
    @Inject
    public Recommend_presenter() {
    }
    @Inject
    Recommend_model mRecommend_model;
}
