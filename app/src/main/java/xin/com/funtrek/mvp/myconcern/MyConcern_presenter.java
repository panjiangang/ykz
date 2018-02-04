package xin.com.funtrek.mvp.myconcern;

import android.util.Log;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.http.bean.ConcernBean;
import xin.com.funtrek.http.bean.UpPic;
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

    public void getData(String mytoken, String myuid) {
        Flowable<ConcernBean> flowable = mMyoncerrn_model.getData(mytoken, myuid);
        flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ConcernBean>() {
                    @Override
                    public void onNext(ConcernBean bean) {
                        getView().showData(bean);
                        Log.e("XXXXXX",bean.getMsg());

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("XXXXXX", "onError:");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("XXXXXX", "complete:");

                    }
                });
    }
    }
