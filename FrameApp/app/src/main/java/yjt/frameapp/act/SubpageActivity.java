package yjt.frameapp.act;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import yjt.frameapp.R;
import yjt.frameapp.config.Constants;
import yjt.frameapp.enums.Sub;
import yjt.frameapp.utils.FragmentUtil;

/**
 * Created by yujiangtao on 2016/1/6.
 * 二级Activity 除了主Acitivity和FirstActivity，其他的Fragment都用SubpageActivity来管理
 */
public class SubpageActivity extends BaseActivity{
    @Override
    protected void initControl(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Sub sub = (Sub) getIntent().getSerializableExtra("Sub");
            Fragment frg = Fragment.instantiate(this, sub.getClz().getName());
            FragmentUtil.toFragmentPush(frgmanager,sub,getIntent().getExtras(),true,Constants.Animate_none);
        } else {
            Sub sub = (Sub) savedInstanceState.getSerializable("Sub");
            Fragment frg = Fragment.instantiate(this, sub.getClz().getName());
            FragmentUtil.toFragmentPush(frgmanager,sub,getIntent().getExtras(),true,Constants.Animate_none);
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.container_layout;
    }

    @Override
    protected void titlebarrifhtimgclick() {
        // TODO Auto-generated method stub
        super.titlebarrifhtimgclick();
        //从此处回调具体fragment实例的顶部右侧Image的click方法
//        if (frgmanager.getCurrentFrg() instanceof DeviceDetailFrg) {
//            ((DeviceDetailFrg) frgmanager.getCurrentFrg()).rightImageClick();
//        }
    }
    @Override
    protected void titlebarrighttvclick() {
        // TODO Auto-generated method stub
        super.titlebarrighttvclick();
        //从此处回调具体fragment实例的顶部右侧tv的click方法
//        if (frgmanager.getCurrentFrg() instanceof PersonalInfoFrg) {
//            ((PersonalInfoFrg) frgmanager.getCurrentFrg()).rightTextClick();
//        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        try {
            outState.putSerializable("Sub", (Sub) getIntent().getSerializableExtra("Sub"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

}
