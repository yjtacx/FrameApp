package yjt.frameapp.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import yjt.frameapp.R;
import yjt.frameapp.config.Constants;
import yjt.frameapp.tools.DoubleClickExitHelper;
import yjt.frameapp.view.titlebar.TitleBarView;

/**
 * Created by yujiangtao on 2015/12/30.
 */
public class MainActivity extends BaseActivity {
    private DoubleClickExitHelper mDoubleClickExitHelper;
    public TitleBarView titlebarview = null;

    @Override
    protected void initControl(Bundle savedInstanceState) {
        titlebarview = (TitleBarView) findViewById(R.id.titlebar);
        titlebarview.setTitleBarCall(this);
        // 左侧按钮式否点击触发返回
        titlebarview.setBackable(false);
        mDoubleClickExitHelper = new DoubleClickExitHelper(this);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.act_main;
    }

    public View getTitlbearView() {
        return titlebarview;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 是否退出应用
            if (Constants.KeyDoubleExit) {
                return mDoubleClickExitHelper.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void titlebarlefttvclick() {
        super.titlebarlefttvclick();
        //按下面这种方式去写
//        if (getCurrentFragment() instanceof Scene2Frg) {
//            ((Scene2Frg) getCurrentFragment()).doEdit(titlebarview.getTitleBarLeftTv());
//        }
    }


    @Override
    protected void titlebarrifhtimgclick() {
        // TODO Auto-generated method stub
        super.titlebarrifhtimgclick();
        //按下面这种方式去写
        // toSubPage(Sub.SET);
    }

    @Override
    protected void titlebarrighttvclick() {
        // TODO Auto-generated method stub
        super.titlebarrighttvclick();
        //按下面这种方式去写
//        if (getCurrentFragment() instanceof BillFrg) {
//            Bundle bundle = ((BillFrg) getCurrentFragment()).getBundle();
//            if (bundle == null) return;
//            toSubPage(Sub.BILLHISTORY, Constants.Animate_left_right, bundle);
//        } else if (getCurrentFragment() instanceof DeviceFrg) {
//            ((DeviceFrg) getCurrentFragment()).rightclick();
//        } else if (getCurrentFragment() instanceof Scene2Frg) {
//            ((Scene2Frg) getCurrentFragment()).rightclick();
//        }
    }

    @Override
    protected void titlebarleftimgclick() {
        // TODO Auto-generated method stub
        super.titlebarleftimgclick();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag("");
    }
}
