package yjt.frameapp.dialog;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import yjt.frameapp.R;

/**
 * Created by yujiangtao on 2015/12/28.
 * 对话框帮助类
 */
public class DialogHelpser {
    /**
     * 创建一个等待进度框
     * @param activity
     * @param message
     * @return 进度框
     */
    public static WaitDialog getWaitDialog(Activity activity, String message) {
        WaitDialog dialog = null;
        try {
            dialog = new WaitDialog(activity, R.style.loading_dialog);
            if (TextUtils.isEmpty(message)) {
                 dialog.getMessageTV().setVisibility(View.GONE);
            } else {
                 dialog.getMessageTV().setVisibility(View.VISIBLE);
                 dialog.setMessage(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }
}
