package xin.com.funtrek.mvp;


import dagger.Component;
import xin.com.funtrek.activitys.MainActivity;
import xin.com.funtrek.activitys.MyConcern;
import xin.com.funtrek.framgments.Picture;
import xin.com.funtrek.framgments.Recommend;
import xin.com.funtrek.framgments.Session;
import xin.com.funtrek.framgments.Video;
import xin.com.funtrek.framgments.recommends.RecAttentionFramgent;
import xin.com.funtrek.framgments.recommends.RecHotFramgent;
import xin.com.funtrek.framgments.videofragments.HotFragment;
import xin.com.funtrek.framgments.videofragments.VearbyFragment;
import xin.com.funtrek.mvp.recommend.RecHotPresenter;
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

    void injectVearbyFragment(VearbyFragment vearbyFragment);

    void inject(RecHotFramgent reHotFramgent);

    void inject(RecHotPresenter recHotPresenter);

    void inject(RecAttentionFramgent reAttentionFramgent);
    void injectMyConcern(MyConcern myConcern);
}
