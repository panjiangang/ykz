package xin.com.funtrek.framgments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import xin.com.funtrek.R;
import xin.com.funtrek.adapter.RecommendAdapter;
import xin.com.funtrek.framgments.recommends.DdyFragment;
import xin.com.funtrek.framgments.recommends.RecAttentionFramgent;
import xin.com.funtrek.framgments.recommends.RecHotFramgent;
import xin.com.funtrek.mvp.recommend.RecommendIView;
import xin.com.funtrek.mvp.recommend.RecommendPresenter;

/**
 * @author ddy
 */
public class Recommend extends DdyFragment<RecommendIView, RecommendPresenter> implements RecommendIView {
    @Inject
    RecommendPresenter recommendPresenter;
    @BindView(R.id.tl)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.framgent_recommend, container, false);
    }

    @Override
    protected void init() {

        RecHotFramgent reHotFramgent = new RecHotFramgent();
        RecAttentionFramgent reAttentionFramgent = new RecAttentionFramgent();

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(reHotFramgent);
        fragments.add(reAttentionFramgent);

        RecommendAdapter reAdapter = new RecommendAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(reAdapter);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("热门");
        tabLayout.getTabAt(1).setText("关注");
    }

    @Override
    protected void setListener() {

    }
}
