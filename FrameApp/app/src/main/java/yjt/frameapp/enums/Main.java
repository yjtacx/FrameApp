package yjt.frameapp.enums;

import yjt.frameapp.R;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public enum  Main implements BaseEnum{
    USERCENTER(0, R.string.title_main_usercenter, UserCenterFrg.class);

    private int idx;
    private int resName;
    private Class<?> clz;

    private Main(int idx, int resName, Class<?> clz) {
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
    public int getIdx() {
        return idx;
    }

    @Override
    public int getResName() {
        return resName;
    }

    @Override
    public Main getPageByValue(int val) {
        for (Main p : values()) {
            if (p.getIdx() == val)
                return p;
        }
        return null;
    }
}
