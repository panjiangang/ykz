package xin.com.funtrek.mvp.recommend;

import xin.com.funtrek.http.bean.Login_Success_Bean;
import xin.com.funtrek.http.bean.RecBannerBean;
import xin.com.funtrek.http.bean.RecItemBean;

/**
 * @author ddy
 */
public interface RecHotIView {

    void successAd(RecBannerBean recBannerBean);

    void successVideos(RecItemBean recItemBean, int page);

    void successToast(RecItemBean recommendBean);

    void successUser(Login_Success_Bean loginSuccessBean);
}
