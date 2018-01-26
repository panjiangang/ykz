package xin.com.funtrek.http.bean;

/**
 * Created by lenovo on 2018/01/25.
 */

public class Login_Register_Bean {
    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":"770f5b35194cb46c","appsecret":"DAF145434D87578718CC32A246C9F50D","createtime":"2017-11-07T18:36:43","email":null,"gender":null,"icon":null,"mobile":"18256189819","money":null,"nickname":null,"password":"8F669074CAF5513351A2DE5CC22AC04C","token":"ACC1909F8EC2AD669F9095D77C063381","uid":1648,"username":"18256189819"}
     */

    private String msg;
    private String code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
