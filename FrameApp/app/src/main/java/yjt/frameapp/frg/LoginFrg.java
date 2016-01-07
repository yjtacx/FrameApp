package yjt.frameapp.frg;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import java.util.HashMap;
import java.util.Map;
import yjt.frameapp.R;
import yjt.frameapp.act.BaseActivity;
import yjt.frameapp.act.FirstActivity;
import yjt.frameapp.act.MainActivity;
import yjt.frameapp.bean.BaseBean;
import yjt.frameapp.bean.LoginBean;
import yjt.frameapp.config.Constants;
import yjt.frameapp.enums.First;
import yjt.frameapp.http.URLS;
import yjt.frameapp.managers.CacheManager;
import yjt.frameapp.utils.FragmentUtil;
import yjt.frameapp.utils.IntentUtil;
import yjt.frameapp.utils.SharePrefUtil;
import yjt.frameapp.utils.StringUtils;
import yjt.frameapp.utils.ToastUtil;
import yjt.frameapp.view.ResizeLayout;

/**
 * 登陆页面
 *
 * @author yujiangt
 * @created 2015-06-12
 */
public class LoginFrg extends BaseWebFrg implements OnClickListener {
    EditText namelogin_;
    EditText pwdlogin_;
    private String username;
    private String pwd;
    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;

    int flag = 0;
    private int screenheight;

    @Override
    public void doError(int requestcode, Exception e) {

    }

    @Override
    public void doSuccess(int requestcode, BaseBean bean, String data) {
        LoginBean loginres = null;
        if (bean != null) {
            loginres = (LoginBean) bean;
        }
        if (loginres == null) {
            ToastUtil.showToastShort("解析数据失败");
        } else {
            if (loginres.getRetCode() == 0) {
                ToastUtil.showToastShort("登陆成功");
                if (loginres.getUserid() == null) {
                    ToastUtil.showToastShort("该接口还没有发布");
                }
                SharePrefUtil.setTocken(loginres.getUserid());
                SharePrefUtil.setUserPWD(null);
                if (!username.equals(SharePrefUtil.getUserName())) {
                    SharePrefUtil.setUserName(username);
                }
                Bundle bundle = new Bundle();
                bundle.putString("FROM", FirstActivity.class.getName());
                IntentUtil.start_activity(getActivity(), MainActivity.class,
                        bundle, true, Constants.Animate_left_right);
            } else {
                ToastUtil.showToastShort(loginres.getMsg());
            }
        }
    }

    @Override
    public Class<?> getParserClass(int requestcode) {
        return LoginBean.class;
    }

    @Override
    public String getUrlSuffix(int requestcode) {
        return URLS.URL_LOGIN;
    }

    @Override
    public Map<String, String> getParmas(int requestcode) {
        username = namelogin_.getText().toString().trim();
        pwd = pwdlogin_.getText().toString().trim();
        if (StringUtils.isEmpty(username)) {
            ToastUtil.showToastShort(getResources().getString(
                    R.string.name_not_empty));
            return null;
        }
        if (StringUtils.isEmpty(pwd)) {
            ToastUtil.showToastShort(getResources().getString(
                    R.string.pwd_not_empty));
            return null;
        }
        Map<String, String> param = new HashMap<String, String>();
        param.put("username", username);
        param.put("password", pwd);
        return param;
    }


    class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESIZE: {
                    if (msg.arg1 == BIGGER) {
                        findViewById(R.id.bottom_login_linear).setVisibility(View.VISIBLE);
//                        findViewById(R.id.register_login).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(R.id.bottom_login_linear).setVisibility(View.GONE);
//                        findViewById(R.id.register_login).setVisibility(View.GONE);
                    }
                }
                break;

                default:
                    break;
            }
            super.handleMessage(msg);
        }
    }

    private InputHandler mHandler = new InputHandler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setTitileBarHas(false);
//		findViewById(R.id.loginscrollview).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }


    @Override
    protected int getLayoutId() {
        // TODO Auto-generated method stub
        return R.layout.frg_login;
    }

    @Override
    protected void initView() {
        // TODO Auto-generated method stub
        new Thread(new Runnable() {
            @Override
            public void run() {
                CacheManager.get(getActivity()).clear();
            }
        }).start();
        namelogin_ = (EditText) findViewById(R.id.name_login);
        pwdlogin_ = (EditText) findViewById(R.id.pwd_login);
        namelogin_.setText(SharePrefUtil.getUserName());
        pwdlogin_.setText(SharePrefUtil.getUserPWD());
        setListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity) getActivity()).tintManager.setTintColor(Color.parseColor("#00000000"));
    }

    private void setListener() {
        findViewById(R.id.login_images).setOnClickListener(this);
        findViewById(R.id.register_login).setOnClickListener(this);
        findViewById(R.id.forgetpwd_login).setOnClickListener(this);

        namelogin_.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;//监听前的文本
            private int editStart;
            private int editEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable mEditText) {
                editStart = namelogin_.getSelectionStart();
                editEnd = namelogin_.getSelectionEnd();
                if (temp.length() == 0) {
                    pwdlogin_.setText("");
                }
                //后续改动去掉了
//                else if (temp.toString().equals(SharePrefUtil.getUserName())) {
//                    pwdlogin_.setText(SharePrefUtil.getUserPWD());
//                }
            }
        });

        ((ResizeLayout) findViewById(R.id.sizelayout)).setOnResizeListener(new ResizeLayout.OnResizeListener() {
            @Override
            public void OnResize(int w, int h, int oldw, int oldh) {
                int change = BIGGER;
                if (screenheight == 0) screenheight = h;
                if (h < screenheight) {
                    change = SMALLER;
                }
                Message msg = new Message();
                msg.what = MSG_RESIZE;
                msg.arg1 = change;
                mHandler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.login_images:
                httptool.doPost();
                break;
            case R.id.register_login:
                FragmentUtil.toFragmentPush(((BaseActivity) getActivity()).getFrgManager(), First.REGISTER, true);
                break;
            case R.id.forgetpwd_login:
//                ((FirstActivity) getActivity()).toFragmentPush(((BaseActivity) getActivity()).getFrgManager(),First.FORGET,true);
                break;
        }
    }


}
