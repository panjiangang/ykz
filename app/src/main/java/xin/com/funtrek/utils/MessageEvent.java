package xin.com.funtrek.utils;

/**
 * Created by lenovo on 2018/02/03.
 */

public class MessageEvent {
    public boolean tag;

    public MessageEvent(boolean tag){
        this.tag = tag;
    }

    public boolean isTag() {
        return tag;
    }

    public void setTag(boolean tag) {
        this.tag = tag;
    }
}
