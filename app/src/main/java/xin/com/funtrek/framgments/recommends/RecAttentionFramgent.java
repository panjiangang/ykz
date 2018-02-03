package xin.com.funtrek.framgments.recommends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import javax.inject.Inject;

import butterknife.BindView;
import xin.com.funtrek.R;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;
import xin.com.funtrek.mvp.recommend.RecHotIView;
import xin.com.funtrek.mvp.recommend.RecHotPresenter;

/**
 * @author ddy
 */
public class RecAttentionFramgent extends DdyFragment<RecHotIView, RecHotPresenter> implements RecHotIView {
    @Inject
    RecHotPresenter reHotFramgentPresenter;
    @BindView(R.id.lv)
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getComponent().inject(this);
        setPresenter(reHotFramgentPresenter);

        return inflater.inflate(R.layout.fragment_rec_attention, container, false);
    }

    @Override
    protected void init() {
        reHotFramgentPresenter.getAd();
        reHotFramgentPresenter.getVideos(1);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void successAd(RecBannerBean recBannerBean) {

    }

    @Override
    public void successVideos(RecItemBean recItemBean, int page) {

    }
}
