package xin.com.funtrek.framgments.videofragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xin.com.funtrek.R;
import xin.com.funtrek.activitys.VideoActivity;
import xin.com.funtrek.adapter.HotAdapter;
import xin.com.funtrek.base.BaseFragment;
import xin.com.funtrek.base.BasePresenter;
import xin.com.funtrek.http.bean.HotBean;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.video.Video_presenter;
import xin.com.funtrek.mvp.video.Video_view;

/**
 * Created by D-H-F on 2018/01/25.
 */

public class VearbyFragment extends BaseFragment<Video_view, Video_presenter> implements Video_view {
    @Inject
    Video_presenter video_presenter;
    RecyclerView hot_rec;
    SpringView spring;
    private HotAdapter hotAdapter;
    List<HotBean.DataBean> list = new ArrayList<>();
    int i=1;

    @Override
    protected int setLayout() {
        return R.layout.video_vearbyfragment;
    }
    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectVearbyFragment(this);
    }

    @Override
    protected Video_presenter getPresenter() {
        return new Video_presenter();
    }

    @Override
    protected void initView(View mview) {
        hot_rec = mview.findViewById(R.id.hot_rec);
        hot_rec.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        spring = mview.findViewById(R.id.spring);
        spring.setHeader(new DefaultHeader(this.getActivity()));
        spring.setFooter(new DefaultFooter(this.getActivity()));
        spring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                if(list!=null){
                    list.clear();
                }
                i=1;
                presenter.gethttp(108,1,i);
            }
            @Override
            public void onLoadmore() {
                i++;
                presenter.gethttp(108,1,i);
            }
        });
    }

    @Override
    protected void logic() {
        hotAdapter = new HotAdapter(this);
        hot_rec.setAdapter(hotAdapter);
        hotAdapter.setonItemClickListener(new HotAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, List<HotBean.DataBean> list) {
                Intent intent = new Intent(VearbyFragment.this.getActivity(), VideoActivity.class);
                intent.putExtra("Vearbylist", (Serializable) list);
                intent.putExtra("Vearbyposition",position);
                startActivity(intent);
            }
        });
        presenter.gethttp(108,1,i);
    }
    @Override
    public void HotSuccess(HotBean hotBean) {
        list.addAll(hotBean.getData());
        hotAdapter.add(list);
        hotAdapter.notifyDataSetChanged();
        spring.onFinishFreshAndLoad();
    }
    @Override
    public void HotFail() {

    }
}
