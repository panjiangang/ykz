package xin.com.funtrek.mvp;


import android.app.Fragment;

import dagger.Component;
import xin.com.funtrek.activitys.MainActivity;
import xin.com.funtrek.framgments.Picture;
import xin.com.funtrek.framgments.Recommend;
import xin.com.funtrek.framgments.Session;
import xin.com.funtrek.framgments.Video;
import xin.com.funtrek.framgments.videofragments.HotFragment;
import xin.com.funtrek.mvp.video.Video_presenter;

/**
 * date:2017/12/12  23:40
 * author:Mr.XIn💕
 */
@Component(modules = IModule.class)
public interface IComponent {
    void injectMain(MainActivity mainActivity);
    void injectRecommend(Recommend recommend);
    void injectSession(Session session);
    void injectVideo(Video video);
    void injectPicture(Picture picture);
    void injectHotFragment(HotFragment hotFragment);
    void injectVideo_presenter(Video_presenter video_presenter);
}
