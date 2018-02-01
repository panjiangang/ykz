package xin.com.funtrek.mvp.login;

/**
 * Created by lenovo on 2018/01/25.
 */

public class Login_presenter {
    MainPresenter mainPresenter;
    private Login_model loginModel;

    public Login_presenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        loginModel = new Login_model();
    }

    public void loginClick(String wz, String phone, String password) {
        loginModel.Login(wz, phone, password, new Login_model.ModelCallPresenter() {
            @Override
            public void success(String msg) {
                mainPresenter.lClick(msg);
            }
        });
    }

    public void regClick() {
        mainPresenter.rClick();
    }

    public interface MainPresenter {
        void lClick(String msg);

        void rClick();
    }
}
