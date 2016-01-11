package yjt.frameapp.enums;

import yjt.frameapp.R;
import yjt.frameapp.frg.SystemsetFrg;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public enum Sub implements BaseEnum{
    SYSTEMSET(0,  R.string.title_main_usercenter, SystemsetFrg.class);

    private int idx;
    private int resName;
    private Class<?> clz;

    private Sub(int idx, int resName, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.clz = clz;
    }


    public void setIdx(int idx) {
        this.idx = idx;
    }


    public void setResName(int resName) {
        this.resName = resName;
    }


    public void setClz(Class<?> clz) {
        this.clz = clz;
    }


    @Override
    public Class<?> getClz() {
        return clz;
    }

    @Override
    public BaseEnum getPageByValue(int val) {
        for (Sub p : values()) {
            if (p.getIdx() == val)
                return p;
        }
        return null;
    }

    @Override
    public int getIdx() {
        return idx;
    }

    @Override
    public int getResName() {
        return resName;
    }

}
