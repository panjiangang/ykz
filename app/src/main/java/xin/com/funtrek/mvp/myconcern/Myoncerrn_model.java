package xin.com.funtrek.mvp.myconcern;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import xin.com.funtrek.http.Api;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.ConcernBean;
import xin.com.funtrek.http.bean.UpPic;

/**
 * date:2018/1/19  14:17
 * author:Mr.XIn💕
 */


public class Myoncerrn_model {

    private Map<String, String> mMap;

    @Inject
    public Myoncerrn_model() {
    }
    public Flowable<ConcernBean> getData(String mytoken, String myuid) {
        mMap = new HashMap<String, String>();
//        mMap.put("source","android");
//        mMap.put("appVersion","101");
        mMap.put("token",mytoken);
        mMap.put("uid",myuid);
        ApiServce service = RetrofitUtils.getInstance().getApiService(Api.baseUrl, ApiServce.class);
        Flowable<ConcernBean> flowable = service.myConcern(mMap);
        return flowable;
    }

}
