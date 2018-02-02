package xin.com.funtrek.http;

import android.util.Log;

import io.reactivex.observers.DisposableObserver;

/**
 * @author ddy
 */
public class BaseDisposableObserver<B> extends DisposableObserver<B> {
    @Override
    public void onNext(B b) {

    }

    @Override
    public void onError(Throwable e) {
        Log.e("BaseDisposableObserver", e.toString());
    }

    @Override
    public void onComplete() {

    }
}
