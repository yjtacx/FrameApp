package yjt.frameapp.frg;

import android.os.Bundle;
import yjt.frameapp.dialog.WaitDialogHelper;
import yjt.frameapp.http.HttpInter;
import yjt.frameapp.http.HttpWork;
import yjt.frameapp.utils.ToastUtil;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public abstract class BaseWebFrg extends BaseFrg implements HttpInter {
    protected HttpWork httptool = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        httptool = new HttpWork(this);
    }
    @Override
    public void netError(int requestcode) {
        ToastUtil.showToastShort("没有检查到网络连接");
    }

    @Override
    public void doBefore(int requestcode, boolean showdialog, String dialogmsg) {
        if (showdialog) {
            //显示对话框
            WaitDialogHelper.showWaitDialg(getActivity(),dialogmsg);
        }
    }

    @Override
    public void doAfter(int requestcode) {
        WaitDialogHelper.cancelWaitDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        httptool.cancelRequest();
    }

}
