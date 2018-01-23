package xin.com.funtrek.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.ButterKnife;

/**
 * date:2018/1/19  14:05
 * author:Mr.XIn💕
 */


public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    T presenter;
    View mView;
    Unbinder unbinder;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            mView = inflater.inflate(setLayout(), container, false);
      unbinder = ButterKnife.bind(this, mView);

        bridge();
            presenter = getPresenter();
        if (presenter != null) {
            presenter.attch((V) this);
        }
        initView(mView);
        logic();
        return mView;
    }

    protected abstract int setLayout();

    protected abstract void bridge();

    protected abstract T getPresenter();

    protected abstract void initView(View mview);

    protected abstract void logic();

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.disAttch();
        unbinder.unbind();


    }}
