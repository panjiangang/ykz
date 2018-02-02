package xin.com.funtrek.mvp.recommend;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author ddy
 */
public abstract class DdyPresenter<I> {
    private WeakReference<I> weakReference;
    private CompositeDisposable compositeDisposable;

    public void attach(I iview) {
        if (weakReference == null) {
            weakReference = new WeakReference<>((I) iview);
        }
    }

    //判断是否关联
    private boolean isAttach() {
        return weakReference != null && weakReference.get() != null;
    }

    I getIView() {
        if (isAttach()) {
            return weakReference.get();
        }
        return null;
    }

    public void add(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    private void dispose() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    public void detach() {
        if (isAttach()) {
            dispose();
            weakReference.clear();
            weakReference = null;
        }
    }
}
