package xin.com.funtrek.mvp.collect;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.http.bean.UpPic;
import xin.com.funtrek.mvp.main.Main_model;
import xin.com.funtrek.mvp.main.Main_view;

/**
 * date:2018/1/19  14:33
 * author:Mr.XIn💕
 */


public class Collect_presenter extends BasePresenter<Collect_view> {
    @Inject
  Collect_model mCollect_model;
    @Inject
    public Collect_presenter() {
    }
}
