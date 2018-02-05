package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import xin.com.funtrek.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rec_hot);

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
    }
}
