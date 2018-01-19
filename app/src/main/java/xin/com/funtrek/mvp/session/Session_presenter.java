package xin.com.funtrek.mvp.session;

import javax.inject.Inject;

import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.mvp.recommend.Recommend_model;
import xin.com.funtrek.mvp.recommend.Recommend_view;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class Session_presenter extends BasePresenter<Session_view> {
    @Inject
    public Session_presenter() {
    }
    @Inject
    Session_model mSession_model;
}
