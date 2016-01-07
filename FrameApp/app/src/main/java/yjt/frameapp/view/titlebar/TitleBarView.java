package yjt.frameapp.view.titlebar;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

import yjt.frameapp.R;

/**
 * 自定义标题栏
 *
 * @author yujiangtao
 *
 */
public class TitleBarView extends RelativeLayout implements OnClickListener {

	private Context mContext;

	private ImageView leftimg;
	private ImageView rightimg;
	private TextView lefttv;
	private TextView righttv;
	private TextView centertv;
	boolean backable = true;
	private ImageView centerimage;


	TitlebarCallback callback = null;

	public TitleBarView(Context context) {
		super(context);
		mContext = context;
	}

	public TitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView(attrs);
	}

	private void initView(AttributeSet attrs) {
		LayoutInflater.from(mContext).inflate(R.layout.title_bar_view, this);
		leftimg=(ImageView) findViewById(R.id.left_titlebar_image);
		rightimg=(ImageView) findViewById(R.id.right_titlebar_image);
		centertv=(TextView) findViewById(R.id.centertxt_titlebar);
		lefttv = (TextView) findViewById(R.id.left_titlebar_text);
		righttv = (TextView) findViewById(R.id.right_titlebar_text);
		centerimage = (ImageView) findViewById(R.id.centerimage_titlebar);
		leftimg.setOnClickListener(this);
		rightimg.setOnClickListener(this);
		lefttv.setOnClickListener(this);
		righttv.setOnClickListener(this);
		lefttv.setVisibility(View.GONE);
		righttv.setVisibility(View.GONE);
		leftimg.setVisibility(View.GONE);
		rightimg.setVisibility(View.GONE);

		TypedArray a = mContext.obtainStyledAttributes(attrs,
				R.styleable.titlebar);
		String titletext = a.getString(R.styleable.titlebar_centertext);
		String righttext = a.getString(R.styleable.titlebar_righttext);
		String lefttext = a.getString(R.styleable.titlebar_lefttext);
		Drawable leftd=a.getDrawable(R.styleable.titlebar_leftimgres);
		Drawable rightd=a.getDrawable(R.styleable.titlebar_rightimgres);
//		boolean leftimgvisible = a.getBoolean(
//				R.styleable.titlebar_leftimgvisible, false);
//		boolean lefttvvisible = a.getBoolean(
//				R.styleable.titlebar_lefttvvisible, false);
//		boolean rightimgvisible = a.getBoolean(
//				R.styleable.titlebar_rightimgvisile, false);
//		boolean righttvvisible = a.getBoolean(
//				R.styleable.titlebar_righttvvisible, false);
		if(leftd!=null){
			leftimg.setVisibility(View.VISIBLE);
			leftimg.setImageDrawable(leftd);
		}
		if(rightd!=null){
			rightimg.setVisibility(View.VISIBLE);
			rightimg.setImageDrawable(rightd);
		}
		if(!TextUtils.isEmpty(titletext)){
			centertv.setVisibility( View.VISIBLE );
			centertv.setText(titletext);
		}
		if(!TextUtils.isEmpty(lefttext)){
			lefttv.setText(lefttext);
			lefttv.setVisibility(View.VISIBLE);
		}
		if(!TextUtils.isEmpty(righttext)){
			righttv.setText(righttext);
			righttv.setVisibility(View.VISIBLE);
		}
//		boolean centervisible = a.getBoolean(R.styleable.titlebar_titlevisible,
//				false);
//		leftimg.setVisibility(leftimgvisible ? View.VISIBLE : View.GONE);
//		lefttv.setVisibility(lefttvvisible ? View.VISIBLE : View.GONE);
//		rightimg.setVisibility(rightimgvisible ? View.VISIBLE : View.GONE);
//		righttv.setVisibility(righttvvisible ? View.VISIBLE : View.GONE);



		a.recycle();
	}

	public void setTitleBarCall(TitlebarCallback callback) {
		this.callback = callback;
	}

	/**
	 * 设置标题文字信息
	 * @param txtRes
	 */
	public void setTitleText(String txtRes) {
		centerimage.setVisibility(View.GONE);
		centertv.setVisibility(View.VISIBLE);
		centertv.setText(txtRes);
	}

	/**
	 * 设置显示标题图片
	 */
	public void setTitleImageVisible(){
		centertv.setVisibility(View.GONE);
		centerimage.setVisibility(View.VISIBLE);
	}

	public ImageView getTitleBarLeftImage() {
		return leftimg;
	}

	public ImageView getTitleBarRightImage() {
		return rightimg;
	}

	public TextView getTitleBarRightTv() {
		return righttv;
	}

	public TextView getTitleBarLeftTv() {
		return lefttv;
	}

	/**
	 * 设置标题左边文字显示
	 * @param id
	 */
	public void setLeftText(int id) {
		leftimg.setVisibility(View.GONE);
		lefttv.setVisibility(View.VISIBLE);
		lefttv.setText(mContext.getString(id));
	}

	public void setRightText(int id) {
		rightimg.setVisibility(View.GONE);
		righttv.setVisibility(View.VISIBLE);
		righttv.setText(mContext.getString(id));
	}

	public void setLeftImg(int id) {
		lefttv.setVisibility(View.GONE);
		leftimg.setVisibility(View.VISIBLE);
		leftimg.setImageResource(id);
	}

	public void setRightImg(int id) {
		righttv.setVisibility(View.GONE);
		rightimg.setVisibility(View.VISIBLE);
		rightimg.setImageResource(id);
	}

	/**
	 * 设置是否返回的标识
	 * @param b
	 */
	public void setBackable(boolean b) {
		this.backable = b;
	}

	/**
	 * 隐藏右侧所有控件
	 */
	public void setRightGone() {
		rightimg.setVisibility(View.GONE);
		righttv.setVisibility(View.GONE);
	}
	/**
	 * 隐藏左侧所有控件
	 */
	public void setLeftGone(){
		leftimg.setVisibility(View.GONE);
		lefttv.setVisibility(View.GONE);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.left_titlebar_image:
				callback.leftimgclick(backable);
				break;
			case R.id.left_titlebar_text:
				callback.lefttvclick(backable);
				break;
			case R.id.right_titlebar_image:
				callback.rightimgclick();
				break;
			case R.id.right_titlebar_text:
				callback.righttvclick();
				break;
		}
	}

}
