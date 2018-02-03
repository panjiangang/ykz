package xin.com.funtrek.http.bean;

import java.util.List;

/**
 * date:2018/1/22
 * author:张伟(Administrator)
 * desc:段子列表的JavaBean
 */

public class SessionBean {

    /**
     * msg : 获取段子列表成功
     * code : 0
     * data : [{"commentNum":null,"content":"93335521","createTime":"2018-01-20T08:57:24","imgUrls":null,"jid":1044,"praiseNum":null,"shareNum":null,"uid":1703,"user":{"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1514641278805head.jpg","nickname":"我的世界","praiseNum":"null"}},{"commentNum":null,"content":"好好学习","createTime":"2018-01-19T11:03:12","imgUrls":null,"jid":1043,"praiseNum":null,"shareNum":null,"uid":1059,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"哈哈","createTime":"2018-01-19T10:56:24","imgUrls":null,"jid":1042,"praiseNum":null,"shareNum":null,"uid":11788,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"墨尔本ぃ雨","praiseNum":"null"}},{"commentNum":null,"content":"好好学习","createTime":"2018-01-19T10:55:33","imgUrls":null,"jid":1041,"praiseNum":null,"shareNum":null,"uid":1059,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":null,"praiseNum":"null"}},{"commentNum":null,"content":"来啊跌破坡头母鸡7k7k怕评论母鸡","createTime":"2018-01-19T10:55:11","imgUrls":null,"jid":1040,"praiseNum":null,"shareNum":null,"uid":11788,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"墨尔本ぃ雨","praiseNum":"null"}},{"commentNum":null,"content":"考虑诺克1测lolKKK去咯","createTime":"2018-01-19T10:46:01","imgUrls":null,"jid":1039,"praiseNum":null,"shareNum":null,"uid":11788,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"墨尔本ぃ雨","praiseNum":"null"}},{"commentNum":null,"content":"跨界石打开连接阿斯利康","createTime":"2018-01-18T20:53:06","imgUrls":null,"jid":1038,"praiseNum":null,"shareNum":null,"uid":11788,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"墨尔本ぃ雨","praiseNum":"null"}},{"commentNum":null,"content":"吴希瑞！！八维最帅经理！！！！！没有之一！！！！！！！！啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊！！！！！","createTime":"2018-01-18T20:52:28","imgUrls":null,"jid":1037,"praiseNum":null,"shareNum":null,"uid":3625,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"张心卓大美女哈哈哈哈","praiseNum":"null"}},{"commentNum":null,"content":"吴希瑞！！！！最帅啦！！！八维最帅经理！！！！！啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊！！！！！","createTime":"2018-01-18T20:51:58","imgUrls":null,"jid":1036,"praiseNum":null,"shareNum":null,"uid":3625,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"张心卓大美女哈哈哈哈","praiseNum":"null"}},{"commentNum":null,"content":"吴希瑞！！！！最帅啦！！！八维最帅经理！！！！！啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊！！！！！","createTime":"2018-01-18T20:51:41","imgUrls":null,"jid":1035,"praiseNum":null,"shareNum":null,"uid":3625,"user":{"age":null,"fans":"null","follow":false,"icon":null,"nickname":"张心卓大美女哈哈哈哈","praiseNum":"null"}}]
     */

    public String msg;
    public String code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * commentNum : null
         * content : 93335521
         * createTime : 2018-01-20T08:57:24
         * imgUrls : null
         * jid : 1044
         * praiseNum : null
         * shareNum : null
         * uid : 1703
         * user : {"age":null,"fans":"null","follow":false,"icon":"https://www.zhaoapi.cn/images/1514641278805head.jpg","nickname":"我的世界","praiseNum":"null"}
         */

        public Object commentNum;
        public String content;
        public String createTime;
        public Object imgUrls;
        public int jid;
        public Object praiseNum;
        public Object shareNum;
        public int uid;
        public UserBean user;

        public static class UserBean {
            /**
             * age : null
             * fans : null
             * follow : false
             * icon : https://www.zhaoapi.cn/images/1514641278805head.jpg
             * nickname : 我的世界
             * praiseNum : null
             */

            public Object age;
            public String fans;
            public boolean follow;
            public String icon;
            public String nickname;
            public String praiseNum;
        }
    }
}
