package yjt.frameapp.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import yjt.frameapp.config.Constants;
import yjt.frameapp.enums.BaseEnum;
import yjt.frameapp.enums.First;
import yjt.frameapp.managers.FrgManager;

/**
 * Created by yujiangtao on 2016/1/5.
 * fragment切换帮助类
 */
public class FragmentUtil {
    public static void toFragmentPush(FrgManager frgManager, BaseEnum enumcls, Boolean backable,int anim) {
        toFragmentPush(frgManager, enumcls, new Bundle(), backable, anim);
    }
    public static void toFragmentPush(FrgManager frgManager, BaseEnum enumcls, Bundle bundle, Boolean backable) {
        toFragmentPush(frgManager, enumcls, bundle, backable, Constants.Animate_left_right);
    }

    public static void toFragmentPush(FrgManager frgmanager, BaseEnum enumcls,
                                      Bundle bundle, Boolean backable, int anim) {
        try {
            Fragment fragment = (Fragment) enumcls.getClz().newInstance();
            frgmanager.pushFragment(fragment, bundle, backable, anim);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:");
        }
    }

    public static void toFragmentAdd(FrgManager frgManager, BaseEnum enumcls, Bundle bundle, Boolean backable) {
        toFragmentAdd(frgManager, enumcls, bundle, backable, Constants.Animate_left_right);
    }
    public static void toFragmentAdd(FrgManager frgManager, BaseEnum enumcls, Boolean backable,int anim) {
        toFragmentAdd(frgManager, enumcls, new Bundle(), backable, anim);
    }
    public static void toFragmentAdd(FrgManager frgmanager, BaseEnum enumcls, Bundle bundle, boolean backable, int anim) {
        try {
            Fragment fragment = (Fragment) enumcls.getClz().newInstance();
            frgmanager.addFragment(fragment, bundle, backable,anim);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(
                    "generate fragment error. by value:");
        }
    }

    public static void toFragmentPush(FrgManager frgmanager, First first, boolean backable) {
        toFragmentPush(frgmanager, first, new Bundle(), backable);
    }

    public static void toFragmentAdd(FrgManager frgmanager, First first, boolean backable) {
        toFragmentAdd(frgmanager, first, new Bundle(), backable);
    }
}
