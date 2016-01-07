package yjt.frameapp.managers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.v4.app.FragmentTransaction;

import yjt.frameapp.R;
import yjt.frameapp.act.BaseActivity;
import yjt.frameapp.config.Constants;
import yjt.frameapp.utils.IntentUtil;

/**
 * fragment管理类，用于管理fragment入栈出栈，切换效果等操作。
 * @version 1.0
 * @author yujiangtao
 *
 */
public class FrgManager implements OnBackStackChangedListener {
	private FragmentActivity activity;
	private FragmentManager manager;

	private Fragment currentFragment = null;
	public Fragment getCurrentFrg(){
		return currentFragment;
	}

	public FrgManager(FragmentActivity act) {
		this.activity = act;
		manager = activity.getSupportFragmentManager();
		manager.addOnBackStackChangedListener(this);
	}

	public void pushFragment(Fragment frg, Bundle bundle) {
		pushFragment(frg, bundle, true);
	}

	public void pushFragment(Fragment frg, boolean backable) {
		pushFragment(frg, null, backable);
	}

	public void pushFragment(Fragment frg, Bundle bundle, boolean backable) {
		pushFragment(frg, bundle, backable, Constants.Animate_left_right);
	}

	public void addFragment(Fragment frg, Bundle bundle, boolean backable,
							int animatetype) {
		FragmentTransaction trasaction = manager.beginTransaction();
		setAnimation(trasaction, animatetype);
		frg.setArguments(bundle);
		trasaction
				.add(R.id.container_view, frg, frg.getClass().getSimpleName())
		;
		if (backable) {
			trasaction.addToBackStack(null);
		}
		trasaction.commit();
		currentFragment = frg;
	}

	public void pushFragment(Fragment frg, Bundle bundle, boolean backable,
							 int animatetype) {
		FragmentTransaction trasaction = manager.beginTransaction();
		setAnimation(trasaction, animatetype);
		frg.setArguments(bundle);
		trasaction.replace(R.id.container_view, frg,
				frg.getClass().getSimpleName());
		if (backable) {
			trasaction.addToBackStack(null);
		}
		trasaction.commit();
		currentFragment = frg;
	}

	private void setAnimation(FragmentTransaction trasanction, int atype) {
		switch (atype) {
			case Constants.Animate_left_right:
				trasanction.setCustomAnimations(R.anim.push_left_in,
						R.anim.push_left_out, R.anim.push_right_in,
						R.anim.push_right_out);
				break;
			case Constants.Animate_bottom_up:
				trasanction.setCustomAnimations(R.anim.pop_bottom_in,
						R.anim.pop_bottom_out, R.anim.pop_bottom_in,
						R.anim.pop_bottom_out);
				break;
			case Constants.Animate_apha_out:
				trasanction.setCustomAnimations(R.anim.alpha_nomal,
						R.anim.alpha_out, R.anim.alpha_nomal, R.anim.alpha_out);

				break;
			default:
//			trasanction.setCustomAnimations(R.anim.push_left_in,
//					R.anim.push_left_out, R.anim.push_left_in,
//					R.anim.push_left_out);
				break;

		}
	}

	public void pop() {
		if (manager.getBackStackEntryCount() > 1) {
			currentFragment = manager.getFragments().get(
					manager.getFragments().size() - 2);
			manager.popBackStack();
		} else {
			AppManager.getAppManager().finishActivity();
			IntentUtil.popAnimation(activity, ((BaseActivity) activity).animatetype);
		}
	}

	public void popto(String tag) {
		Fragment frg = manager.findFragmentByTag(tag);
		if (frg != null) {
			manager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			currentFragment = frg;
		}
	}

	public void exist(){
		manager.popBackStack(null, 0);
		AppManager.getAppManager().finishActivity();
	}


	@Override
	public void onBackStackChanged() {
		// TODO Auto-generated method stub
//		List<Fragment> mlist= manager.getFragments();
//		if(mlist!=null && mlist.size()>0)
//		currentFragment = manager.getFragments().get(
//				manager.getFragments().size() - 1);
	}

//	public Stack<Fragment> getFragments() {
//		return fragments;
//	}
}
