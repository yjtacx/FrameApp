package yjt.frameapp.view.titlebar;

/**
 * 顶部栏回调接口
 * @author yujiangtao
 *
 */
public interface TitlebarCallback {
	//	void leftclick(boolean defaut);
//	void rightclick();
	void leftimgclick(boolean backable);
	void lefttvclick(boolean backable);
	void rightimgclick();
	void righttvclick();
}
