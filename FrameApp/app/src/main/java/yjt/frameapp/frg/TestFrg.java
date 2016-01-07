package yjt.frameapp.frg;

import java.util.Map;

import yjt.frameapp.bean.BaseBean;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public class TestFrg extends BaseWebFrg {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {
        httptool.doPost();
    }


    @Override
    public void doError(int requestcode, Exception e) {

    }

    @Override
    public void doSuccess(int requestcode, BaseBean bean, String data) {

    }

    @Override
    public Class<?> getParserClass(int requestcode) {
        return null;
    }

    @Override
    public String getUrlSuffix(int requestcode) {
        return null;
    }

    @Override
    public Map<String, String> getParmas(int requestcode) {
        return null;
    }
}
