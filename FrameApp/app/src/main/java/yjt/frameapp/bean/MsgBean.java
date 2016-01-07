package yjt.frameapp.bean;

import java.io.Serializable;

/**
 * Created by yujiangtao on 2015/9/12.
 * msgid integer,userid text,msgtype text,content text,title text,
 * img text,extraa text,extrab text,time text,readable integer
 */
public class MsgBean implements Serializable{
    private int msgtype;
    private String title="";
    private String content="";
    private String img;
    private String extraa="";
    private String extrab="";
    private String time="";
    private int readable=0;
    private int msgid;
    private String hedmec="";

    public String getHedmec() {
        return hedmec;
    }

    public void setHedmec(String hedmec) {
        this.hedmec = hedmec;
    }


    public int getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(int msgtype) {
        this.msgtype = msgtype;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getExtraa() {
        return extraa;
    }

    public void setExtraa(String extraa) {
        this.extraa = extraa;
    }

    public String getExtrab() {
        return extrab;
    }

    public void setExtrab(String extrab) {
        this.extrab = extrab;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReadable() {
        return readable;
    }

    public void setReadable(int readable) {
        this.readable = readable;
    }

    public int getMsgid() {
        return msgid;
    }

    public void setMsgid(int msgid) {
        this.msgid = msgid;
    }
}
