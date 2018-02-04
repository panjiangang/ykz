package xin.com.funtrek.activitys;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xin.com.funtrek.R;
import xin.com.funtrek.base.BaseActivity;
import xin.com.funtrek.http.bean.ConcernBean;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.myconcern.Concern_adapter;
import xin.com.funtrek.mvp.myconcern.MyConcern_presenter;
import xin.com.funtrek.mvp.myconcern.MyConcern_view;

/**
 * date:2018/2/3  10:11
 * author:Mr.XIn💕
 */

//我的关注页面
public class MyConcern extends BaseActivity<MyConcern_view, MyConcern_presenter> implements MyConcern_view {


    @Inject
    MyConcern_presenter mMyConcern_presenter;
    @BindView(R.id.setting_back)
    LinearLayout mSettingBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.concern_rv)
    RecyclerView mConcernRv;
    private String mUid;
    private String mToken;
    SharedPreferences sp;
    private Concern_adapter mConcern_adapter;

    @Override
    protected int setLayout() {
        return R.layout.myconcern_activity;
    }

    @Override
    protected void bridge() {
        DaggerIComponent.builder().iModule(new IModule()).build().injectMyConcern(this);

    }

    @Override
    protected MyConcern_presenter getPresenter() {
        return mMyConcern_presenter;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mTitle.setText("我的关注");
        mConcernRv.setLayoutManager(new LinearLayoutManager(this));
//        mConcern_adapter.setOnItemClickListener(new Concern_adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MyConcern.this, "详情页", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @SuppressLint("WrongConstant")
    @Override
    protected void logic() {
        sp = getSharedPreferences("SharedPreferences", MODE_APPEND);
        mUid = sp.getString("uid", "");
        // 12272
        Log.e("XXXXXXXXXXXX", mUid);
        mToken = sp.getString("token", "");
        //C625F5867FC5F65790AAD571784C748F
        Log.e("XXXXXXXXXXXX", mToken);
        mMyConcern_presenter.getData(mToken, mUid);
    }


    @OnClick(R.id.setting_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showData(ConcernBean bean) {
        List<ConcernBean.DataBean> data = bean.getData();
        mConcern_adapter = new Concern_adapter(this, data);
        mConcernRv.setAdapter(mConcern_adapter);
    }
}
