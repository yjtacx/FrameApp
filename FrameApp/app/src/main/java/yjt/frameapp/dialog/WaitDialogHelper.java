package yjt.frameapp.dialog;

import android.app.Activity;

/**
 * Created by yujiangtao on 2015/12/30.
 * 自定义对话框显示和隐藏帮助类
 */
public class WaitDialogHelper {

    private static WaitDialog waitDialg = null;

    //    @Override
    public static void showWaitDialg(Activity act, String msg) {
        if (waitDialg == null) {
            waitDialg = DialogHelpser.getWaitDialog(act, msg);
        } else if (waitDialg.isShowing()) return;
        waitDialg.show();
    }

    //    @Override
    public static void cancelWaitDialog() {
        if (waitDialg != null && waitDialg.isShowing()) waitDialg.dismiss();
        waitDialg = null;
    }
}
