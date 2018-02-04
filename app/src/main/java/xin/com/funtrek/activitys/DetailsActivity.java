package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import xin.com.funtrek.R;
import xin.com.funtrek.http.ApiServce;
import xin.com.funtrek.http.RetrofitUtils;
import xin.com.funtrek.http.bean.SessionXQBean;
import xin.com.funtrek.adapter.RecHotAdapter;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.recommend.RecHotIView;
import xin.com.funtrek.mvp.recommend.RecHotPresenter;


public class DetailsActivity extends AppCompatActivity implements RecHotIView, SpringView.OnFreshListener {

    @Inject
    RecHotPresenter reHotFramgentPresenter;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.sv)
    SpringView sv;
    Unbinder unbinder;
    private RecHotAdapter recHotAdapter;
    private int uid;

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
        setContentView(R.layout.fragment_rec_hot);
        setContentView(R.layout.activity_details_up);
//        setContentView(R.layout.activity_session_xq);
        ButterKnife.bind(this);

        DaggerIComponent.builder().iModule(new IModule()).build().inject(this);
        if (reHotFramgentPresenter != null) {
            reHotFramgentPresenter.attach(this);
        }

        unbinder = ButterKnife.bind(this);
        init();
        setListener();
    }

    protected void init() {

        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 1730);

        sv.setHeader(new DefaultHeader(this));
        sv.setFooter(new DefaultFooter(this));

        recHotAdapter = new RecHotAdapter(reHotFramgentPresenter, this.uid, null);
        lv.setAdapter(recHotAdapter);
        recHotAdapter.notifyDataSetChanged();

        reHotFramgentPresenter.getUserInfo(uid);
    }

    protected void setListener() {
        sv.setListener(this);
    }

    @Override
    public void successAd(RecBannerBean recBannerBean) {
    }

    @Override
    public void successUser(Login_Success_Bean loginSuccessBean) {
        recHotAdapter.addUser(loginSuccessBean);
        recHotAdapter.notifyDataSetChanged();

        reHotFramgentPresenter.getUserVideos(uid);
    }

    @Override
    public void successVideos(RecItemBean recItemBean, int page) {
        recHotAdapter.addVideos(recItemBean, page);
        recHotAdapter.notifyDataSetChanged();
        sv.onFinishFreshAndLoad();
    }

    @Override
    public void successToast(RecItemBean recommendBean) {
        Toast.makeText(this, "" + recommendBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        reHotFramgentPresenter.onRefresh(uid);
    }

    @Override
    public void onLoadmore() {
        reHotFramgentPresenter.onLoadmore(uid);
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
