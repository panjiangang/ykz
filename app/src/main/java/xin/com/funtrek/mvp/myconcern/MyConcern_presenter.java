package xin.com.funtrek.mvp.myconcern;

import javax.inject.Inject;

import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.mvp.picture.Picture_model;
import xin.com.funtrek.mvp.picture.Picture_view;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class MyConcern_presenter extends BasePresenter<MyConcern_view> {
    @Inject
    public MyConcern_presenter() {

    }

    @Inject
    Myoncerrn_model mMyoncerrn_model;
}
