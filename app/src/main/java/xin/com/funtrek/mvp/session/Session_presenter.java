package xin.com.funtrek.mvp.session;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.http.bean.SessionBean;

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

    public void getData() {
        Flowable<SessionBean> flowable = mSession_model.getData();

        DisposableSubscriber<SessionBean> subscriber = flowable
                .subscribeOn(Schedulers.io())
                //指定观察者所在 主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SessionBean>() {
                    @Override
                    public void onNext(SessionBean sessionBean) {
                        getView().showData(sessionBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        add(subscriber);
    }

}
