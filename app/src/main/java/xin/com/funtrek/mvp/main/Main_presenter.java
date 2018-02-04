package xin.com.funtrek.mvp.main;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.http.bean.UpPic;

/**
 * date:2018/1/19  14:33
 * author:Mr.XIn💕
 */


public class Main_presenter extends BasePresenter<Main_view> {
    @Inject
    Main_model mMain_model;
    @Inject
    public Main_presenter() {
    }
    public void getData(String mytoken, String myuid, File file){
        Flowable<UpPic> flowable = mMain_model.getData(mytoken,myuid,file);
       flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<UpPic>() {
                    @Override
                    public void onNext(UpPic bean) {
                        getView().showData(bean);
                    }

                    @Override
                    public void onError(Throwable t) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });


    }
}
