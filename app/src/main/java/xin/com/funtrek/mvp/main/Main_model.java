package xin.com.funtrek.mvp.main;

import java.io.File;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import xin.com.funtrek.activitys.MainActivity;
import xin.com.funtrek.http.Api;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.UpPic;

/**
 * date:2018/1/19  14:32
 * author:Mr.XIn💕
 */


public class Main_model {
    @Inject
    public Main_model() {
    }
    public Flowable<UpPic> getData(String mytoken, String myuid, File file) {
        Map<String, String>  mMap = new HashMap<String, String>();
                mMap.put("source","android");
                mMap.put("appVersion","101");
                mMap.put("token",mytoken);
                mMap.put("uid",myuid);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);
        ApiServce service = RetrofitUtils.getInstance().getApiService(Api.baseUrl, ApiServce.class);
        Flowable<UpPic> flowable = service.upPicture(mMap,requestFile,body);
        return flowable;
    }
}
