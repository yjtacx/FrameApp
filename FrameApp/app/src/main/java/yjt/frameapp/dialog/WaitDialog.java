package yjt.frameapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import yjt.frameapp.R;
import yjt.frameapp.tools.DeviceTool;

/***
 * 自定义等待对话框
 */
public class WaitDialog extends Dialog {

    private TextView _messageTv;

    public WaitDialog(Context context) {
        super(context);
        init(context);
    }

    public WaitDialog(Context context, int defStyle) {
        super(context, defStyle);
        init(context);
    }

    protected WaitDialog(Context context, boolean cancelable,
                         OnCancelListener listener) {
        super(context, cancelable, listener);
        init(context);
    }

    public static boolean dismiss(WaitDialog dialog) {
        if (dialog != null) {
            dialog.dismiss();
            return false;
        } else {
            return true;
        }
    }


    private void init(Context context) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        View view = LayoutInflater.from(context)
                .inflate(R.layout.process, null);
        setContentView(view, new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        _messageTv = (TextView) view.findViewById(R.id.progress_msg_tv);

    }

    public static boolean show(WaitDialog waitdialog) {
        boolean flag;
        if (waitdialog != null && !waitdialog.isShowing()) {
            waitdialog.show();
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (DeviceTool.isTablet()) {
            int i = (int) DeviceTool.dpToPixel(360F);
            if (i < DeviceTool.getScreenWidth()) {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.width = i;
                getWindow().setAttributes(params);
            }
        }
    }

    public void setMessage(int message) {
        _messageTv.setText(message);
    }

    public void setMessage(String message) {
        _messageTv.setText(message);
    }

    public TextView getMessageTV() {
        return _messageTv;
    }
}
