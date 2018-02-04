package xin.com.funtrek.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import xin.com.funtrek.R;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.SessionXQBean;


public class DetailsActivity extends AppCompatActivity {


//    @BindView(R.id.tup)
//    ImageView tup;
//    @BindView(R.id.fanhui)
//    ImageView fanhui;
//    @BindView(R.id.textView)
//    TextView textView;
    @BindView(R.id.share)
    ImageView share;
//    @BindView(R.id.news)
//    ImageView news;
    @BindView(R.id.text)
    TextView text;
//    @BindView(R.id.button_Follow)
//    Button buttonFollow;
//    @BindView(R.id.textView2)
//    TextView textView2;
//    @BindView(R.id.relativeLayout)
//    RelativeLayout relativeLayout;
//    @BindView(R.id.head_sdv)
//    SimpleDraweeView headSdv;
//    @BindView(R.id.works_rec)
//    RecyclerView worksRec;
    private int uid;
    private String imgUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_up);
//        setContentView(R.layout.activity_session_xq);
        ButterKnife.bind(this);

        uid = getIntent().getIntExtra("uid", 106);
        imgUrls = getIntent().getStringExtra("imgUrls");
        getData();

    }

    public void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid + "");
        map.put("page", 1 + "");

        ApiServce apiServce = RetrofitUtils.getInstance().getApiService("https://www.zhaoapi.cn/", ApiServce.class);
        Flowable<SessionXQBean> flowable = apiServce.sessionXqUrl(map);
        DisposableSubscriber<SessionXQBean> subscriber = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SessionXQBean>() {
                    @Override
                    public void onNext(SessionXQBean sessionXQBean) {
//                        worksRec.setLayoutManager(new LinearLayoutManager(SessionXQActivity.this));
//                        worksRec.setAdapter(new SessionXQAdapter(SessionXQActivity.this, sessionXQBean,imgUrls));
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
