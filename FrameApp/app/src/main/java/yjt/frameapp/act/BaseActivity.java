package yjt.frameapp.act;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import java.io.Serializable;

import yjt.frameapp.config.Constants;
import yjt.frameapp.enums.Sub;
import yjt.frameapp.managers.AppManager;
import yjt.frameapp.managers.FrgManager;
import yjt.frameapp.managers.SystemBarTintManager;
import yjt.frameapp.utils.IntentUtil;

/**
 * FragmentActivity基类，
 * @version 1.0
 * @author yujiangtao
 *
 */
public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(final Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		AppManager.getAppManager().addActivity(this);
		frgmanager = new FrgManager(this);
		setContentView(getLayoutID());
		getWindow().setBackgroundDrawable(null);
		// 通过注解绑定控件
		initSystemBar(this);
		if(bundle!=null) {
		}
		initControl(bundle);
	}

	public FrgManager frgmanager;
	public int animatetype;

	protected abstract void initControl(Bundle savedInstanceState);

	protected abstract int getLayoutID();

	protected void titlebarleftimgclick() {
	}
	protected void titlebarlefttvclick(){
	}
	protected void titlebarrifhtimgclick(){
	}
	protected void titlebarrighttvclick() {
	}

	public SystemBarTintManager tintManager=null;
	public void initSystemBar(Activity activity) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(activity, true);
		}
		tintManager = new SystemBarTintManager(activity);
		tintManager.setStatusBarTintEnabled(true);
		// 使用颜色资源
//        tintManager.setStatusBarTintResource(Color.parseColor("#0190D2"));
	}

	@TargetApi(19)
	private static void setTranslucentStatus(Activity activity, boolean on) {
		Window win = activity.getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		} else if (KeyEvent.KEYCODE_HOME == keyCode) {
			return super.onKeyDown(keyCode, event);
		}
		return true;
	}
	
	public FrgManager getFrgManager(){
		return this.frgmanager;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

	}

	public void toSubPage(Sub sub,Bundle bundle) {
		bundle.putSerializable("Sub", sub);
		IntentUtil.start_activity(this, SubpageActivity.class, bundle, false,
				Constants.Animate_left_right);
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		// 当 API Level > 11 调用这个方法可能导致奔溃（android.os.Build.VERSION.SDK_INT > 11）
	}
}
