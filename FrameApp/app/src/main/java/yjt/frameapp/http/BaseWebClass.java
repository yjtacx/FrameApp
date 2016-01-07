package yjt.frameapp.http;

/**
 * Created by yujiangtao on 2015/12/30.
 * 非fragment类访问网络的抽象基类
 */
public abstract class BaseWebClass implements HttpInter {
    protected HttpWork httptool = null;

    public BaseWebClass() {
        this.httptool = new HttpWork(this);
    }

    @Override
    public void netError(int requestcode) {
    }

    @Override
    public void doBefore(int requestcode, boolean showdialog, String dialogmsg) {
    }

    @Override
    public void doAfter(int requestcode) {
    }
}
