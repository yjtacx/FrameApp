package yjt.frameapp.enums;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public interface BaseEnum {
    Class<?> getClz();
    int getIdx();
    int getResName();
    BaseEnum getPageByValue(int val);
}
