package xin.com.funtrek.framgments.videofragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.adapter.HotAdapter;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.video.Video_presenter;
import xin.com.funtrek.mvp.video.Video_view;

/**
 * Created by D-H-F on 2018/01/25.
 */

public class HotFragment extends BaseFragment<Video_view, Video_presenter> implements Video_view{
    @Inject
    Video_presenter video_presenter;
    RecyclerView hot_rec;
    private HotAdapter hotAdapter;

    @Override
    protected int setLayout() {

        return R.layout.video_hotfragment;

    }


    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectHotFragment(this);

    }

    @Override
    protected Video_presenter getPresenter() {
        return new Video_presenter();
    }

    @Override
    protected void initView(View mview) {
        hot_rec = mview.findViewById(R.id.hot_rec);
        hot_rec.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    protected void logic() {
        hotAdapter = new HotAdapter(this);
        hot_rec.setAdapter(hotAdapter);
        presenter.gethttp();
    }
    @Override
    public void HotSuccess(HotBean hotBean) {
        hotAdapter.add(hotBean.getData());
        hotAdapter.notifyDataSetChanged();
    }
    @Override
    public void HotFail() {

    }

}
