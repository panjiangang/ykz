package xin.com.funtrek.mvp.recommend;

import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;

/**
 * @author ddy
 */
public interface RecHotIView {

    void successAd(RecBannerBean recBannerBean);

    void successVideos(RecItemBean recItemBean);
}
