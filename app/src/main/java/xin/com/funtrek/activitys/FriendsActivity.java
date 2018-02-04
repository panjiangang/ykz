package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

//搜索好友
public class FriendsActivity extends AppCompatActivity implements RecHotIView, SpringView.OnFreshListener {

    @Inject
    RecHotPresenter reHotFramgentPresenter;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.sv)
    SpringView sv;
    Unbinder unbinder;
    @BindView(R.id.setting_back)
    LinearLayout mSettingBack;
    @BindView(R.id.title)
    TextView mTitle;
    private RecHotAdapter recHotAdapter;
    private String uid;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        DaggerIComponent.builder().iModule(new IModule()).build().inject(this);
        if (reHotFramgentPresenter != null) {
            reHotFramgentPresenter.attach(this);
        }

        unbinder = ButterKnife.bind(this);
        mTitle.setText("我的收藏");
        init();
        setListener();
    }

    protected void init() {

        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences = getSharedPreferences("SharedPreferences", MODE_APPEND);
        uid = sharedPreferences.getString("uid", "1730");
        token = sharedPreferences.getString("token", "75B3A34ABE0ABC6A6BD05725E244365B");

        sv.setHeader(new DefaultHeader(this));
        sv.setFooter(new DefaultFooter(this));

        recHotAdapter = new RecHotAdapter(reHotFramgentPresenter, Integer.parseInt(uid), token);
        lv.setAdapter(recHotAdapter);
        recHotAdapter.notifyDataSetChanged();

        reHotFramgentPresenter.getFavorites(uid, token);
    }

    protected void setListener() {
        sv.setListener(this);
    }

    @Override
    public void successAd(RecBannerBean recBannerBean) {
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
    public void successUser(Login_Success_Bean loginSuccessBean) {

    }

    @Override
    public void onRefresh() {
        sv.onFinishFreshAndLoad();
    }

    @Override
    public void onLoadmore() {
        sv.onFinishFreshAndLoad();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @OnClick(R.id.setting_back)
    public void onViewClicked() {
        finish();
    }
}
