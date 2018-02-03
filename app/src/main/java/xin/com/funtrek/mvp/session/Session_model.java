package xin.com.funtrek.mvp.session;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.SessionBean;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class Session_model {
    @Inject
    public Session_model() {

//        Map<String, String> map = new HashMap<>();
//        map.put("page", "1");
//        Api.getInstance().sessionUrl(map, new Observer<SessionBean>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(SessionBean value) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });
    }

    public Flowable<SessionBean> getData() {

        Map<String, String> map = new HashMap<>();
        map.put("page", "1");

        ApiServce apiServce = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Flowable<SessionBean> flowable = apiServce.sessionUrl(map);
        return flowable;
    }

}
