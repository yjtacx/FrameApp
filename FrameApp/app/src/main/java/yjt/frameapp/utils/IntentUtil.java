package yjt.frameapp.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import yjt.frameapp.R;
import yjt.frameapp.act.BaseActivity;
import yjt.frameapp.config.Constants;
import yjt.frameapp.managers.AppManager;

/**
 * Intent工具，处理Activity跳转，及跳转动画，数据传递等
 * @author yujiangtao
 *
 */
public class IntentUtil {
	public static void start_activityForResult(Fragment frg, Class<?> cls,
											   Bundle bundle, int request, int animabletype) {
		((BaseActivity)frg.getActivity()).animatetype=animabletype;
		Intent intent = new Intent();
		intent.setClass(frg.getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		frg.startActivityForResult(intent, request);
		setAnimation(frg.getActivity(), animabletype);
	}
	public static void start_activityForResult(Activity activity, Class<?> cls,
											   Bundle bundle, int request, int animabletype) {
		((BaseActivity)activity).animatetype=animabletype;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivityForResult(intent, request);
		setAnimation(activity, animabletype);
	}

	public static void start_activity(Activity activity, Class<?> cls,
									  Bundle bundle, boolean finishable, int animabletype) {
		((BaseActivity)activity).animatetype=animabletype;
		Intent intent = new Intent();
		intent.setClass(activity, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		activity.startActivity(intent);
		setAnimation(activity, animabletype);
		if (finishable) {
			AppManager.getAppManager().finishActivity(activity);
		}
	}


	public static void setAnimation(Activity activity, int atype) {
		switch (atype) {
			case Constants.Animate_left_right:
				activity.overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
				break;
			case Constants.Animate_bottom_up:
				activity.overridePendingTransition(R.anim.pop_bottom_in,
						R.anim.alpha_nomal);
				break;
			case Constants.Animate_apha_out:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.alpha_out);

				break;
			case Constants.Animate_right_left:
				activity.overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
				break;
			default:
				// trasanction.setCustomAnimations(R.anim.push_left_in,
				// R.anim.push_left_out, R.anim.push_left_in,
				// R.anim.push_left_out);
				break;

		}

	}
	public static void popAnimation(Activity activity, int atype) {
		switch (atype) {
			case Constants.Animate_left_right:
				activity.overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
				break;
			case Constants.Animate_bottom_up:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.pop_bottom_out);
				break;
			case Constants.Animate_apha_out:
				activity.overridePendingTransition(R.anim.alpha_nomal,
						R.anim.alpha_out);

				break;
//			case Constants.Animate_right_left:
//				activity.overridePendingTransition(R.anim.push_left_in,
//						R.anim.push_left_out);
//				break;
			default:
				// trasanction.setCustomAnimations(R.anim.push_left_in,
				// R.anim.push_left_out, R.anim.push_left_in,
				// R.anim.push_left_out);
				break;

		}
	}


}
