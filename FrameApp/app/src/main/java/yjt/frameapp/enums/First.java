package yjt.frameapp.enums;

import yjt.frameapp.R;
import yjt.frameapp.frg.LoginFrg;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public enum First implements BaseEnum{
    LOGIN(0, R.string.title_first_login,  LoginFrg.class),

    REGISTER(1, R.string.title_first_register,
            LoginFrg.class);
//
//    FORGET(2,R.string.title_forget,ForgetpwdFrg.class),
//    FORGETSETNEWPWD(3,R.string.title_set_newpwd,SetNewPwdFrg.class);

    private int idx;
    private int resName;
    private Class<?> clz;

    private First(int idx, int resName, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.clz = clz;
    }

    @Override
    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    @Override
    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    @Override
    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
    @Override
    public First getPageByValue(int val) {
        for (First p : values()) {
            if (p.getIdx() == val)
                return p;
        }
        return null;
    }
}
