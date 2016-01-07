package yjt.frameapp.db;

/**
 * Created by yujiangtao on 2015/8/13.
 */
public class DBConstant {
    public static final int VERSION = 1;
    public static final String DB_NAME = "sqlite-enn.db";

    /**
     * msg table
     * msgid 消息id,userid 用户token，msgtype 消息类型（0-通知 1-公告）,content 消息内容,title 标题
     * img 图片或图标名称或地址，extraa 额外字段，extrab 额外字段，time 接收的时间，readable 是否已读。
     * */

    public static final String TABLE_NAME_MSG = "test_tb";
    public static final String SQL_MSG_CREATE = "create table " + TABLE_NAME_MSG + "(_id integer primary key autoincrement," +
            "msgid integer,userid text,msgtype text,content text,title text,img text,extraa text,extrab text,time text,readable integer)";
    public static final String SQL_MSG_DROP = "drop table if exists "+TABLE_NAME_MSG;

}
