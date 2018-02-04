package xin.com.funtrek.framgments.recommends;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.Unbinder;
import xin.com.funtrek.R;
import xin.com.funtrek.adapter.RecHotAdapter;
import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.mvp.recommend.RecHotIView;
import xin.com.funtrek.mvp.recommend.RecHotPresenter;

import static android.content.Context.MODE_APPEND;

/**
 * @author ddy
 */
public class RecAttentionFramgent extends DdyFragment<RecHotIView, RecHotPresenter> implements RecHotIView, SpringView.OnFreshListener {
    @Inject
    RecHotPresenter reHotFramgentPresenter;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.sv)
    SpringView sv;
    Unbinder unbinder;
    private RecHotAdapter recHotAdapter;
    private SharedPreferences sharedPreferences;
    private String uid;
    private String token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getComponent().inject(this);
        setPresenter(reHotFramgentPresenter);

        return inflater.inflate(R.layout.fragment_rec_hot, container, false);
    }

    @Override
    protected void init() {

        @SuppressLint("WrongConstant") SharedPreferences sharedPreferences = getContext().getSharedPreferences("SharedPreferences", MODE_APPEND);
        uid = sharedPreferences.getString("uid", "1730");
        token = sharedPreferences.getString("token", "75B3A34ABE0ABC6A6BD05725E244365B");

        sv.setHeader(new DefaultHeader(this.getActivity()));
        sv.setFooter(new DefaultFooter(this.getActivity()));

        recHotAdapter = new RecHotAdapter(reHotFramgentPresenter, Integer.parseInt(uid), token);
        lv.setAdapter(recHotAdapter);

        reHotFramgentPresenter.getAd();
        reHotFramgentPresenter.getVideos(uid, token);
    }

    @Override
    protected void setListener() {
        sv.setListener(this);
    }

    @Override
    public void successAd(RecBannerBean recBannerBean) {
        recHotAdapter.addAd(recBannerBean);
        recHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void successVideos(RecItemBean recItemBean, int page) {
        recHotAdapter.addVideos(recItemBean, page);
        recHotAdapter.notifyDataSetChanged();
        sv.onFinishFreshAndLoad();
    }

    @Override
    public void successToast(RecItemBean recommendBean) {
        Toast.makeText(getContext(), "" + recommendBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successUser(Login_Success_Bean loginSuccessBean) {

    }

    @Override
    public void onRefresh() {
        reHotFramgentPresenter.onRefresh(uid, token);
    }

    @Override
    public void onLoadmore() {
        reHotFramgentPresenter.onLoadmore(uid, token);
    }
}
