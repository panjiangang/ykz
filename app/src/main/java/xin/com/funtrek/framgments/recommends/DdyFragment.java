package xin.com.funtrek.framgments.recommends;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import xin.com.funtrek.mvp.DaggerIComponent;
import xin.com.funtrek.mvp.IComponent;
import xin.com.funtrek.mvp.IModule;
import xin.com.funtrek.mvp.recommend.DdyPresenter;

/**
 * @author ddy
 */
public abstract class DdyFragment<I, P extends DdyPresenter<I>> extends Fragment {
    private P presenter = null;
    private Unbinder unbinder;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        init();
        setListener();
    }

    public IComponent getComponent() {
        return  DaggerIComponent.builder().iModule(new IModule()).build();
    }

    public void setPresenter(P presenter) {
        this.presenter = presenter;
        if (presenter != null) {
            presenter.attach((I) this);
        }
    }

    protected abstract void init();

    protected abstract void setListener();

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (presenter != null) {
            presenter.detach();
        }
    }
}
