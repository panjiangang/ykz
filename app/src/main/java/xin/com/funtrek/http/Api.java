package xin.com.funtrek.http;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * date:2018/1/19  14:20
 * author:Mr.XIn💕
 */


public class Api extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }
}
