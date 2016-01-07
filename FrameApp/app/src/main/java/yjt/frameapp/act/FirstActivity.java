package yjt.frameapp.act;

import android.os.Bundle;
import yjt.frameapp.R;
import yjt.frameapp.config.Constants;
import yjt.frameapp.enums.First;
import yjt.frameapp.utils.FragmentUtil;
import yjt.frameapp.utils.IntentUtil;
import yjt.frameapp.utils.SharePrefUtil;

/**
 * Created by yujiangtao on 2016/1/5.
 */
public class FirstActivity extends BaseActivity {

    @Override
    protected void initControl(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (savedInstanceState == null) {
            if(SharePrefUtil.getLoginStatus()){
                IntentUtil.start_activity(this, MainActivity.class,
                        null, true, Constants.Animate_left_right);
            }else
                FragmentUtil.toFragmentPush(getFrgManager(),First.LOGIN, false,Constants.Animate_none);
        }
    }

    @Override
    protected int getLayoutID() {
        // TODO Auto-generated method stub
        return R.layout.container_layout;
    }
}

