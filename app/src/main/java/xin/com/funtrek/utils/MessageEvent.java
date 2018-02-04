package xin.com.funtrek.utils;

/**
 * Created by lenovo on 2018/02/03.
 */

public class MessageEvent {
    public String tag;

    public MessageEvent(String tag){
        this.tag = tag;
    }

    public String isTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
