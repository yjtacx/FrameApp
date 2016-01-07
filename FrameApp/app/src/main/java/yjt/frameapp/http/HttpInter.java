package yjt.frameapp.http;

import java.util.Map;

import yjt.frameapp.bean.BaseBean;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public interface HttpInter {

    /***
     * 错误处理
     * @param requestcode
     * @param e
     */
    void doError(int requestcode, Exception e);

    /***
     * 没有检查到网络连接
     * @param requestcode
     */
    void netError(int requestcode);

    /***
     * 请求前的操作
     * @param requestcode
     */
    void doBefore(int requestcode,boolean showdialog,String dialogmsg);

    /***
     * 请求结束后的处理
     * @param requestcode
     */
    void doAfter(int requestcode);

    /***
     * 成功处理
     * @param requestcode
     * @param bean
     * @param data
     */
    void doSuccess(int requestcode, BaseBean bean, String data);

    /***
     * 取得解析类
     * @param requestcode
     * @return
     */
    Class<?> getParserClass(int requestcode);

    /***
     * 取得url
     * @param requestcode
     * @return
     */
    String getUrlSuffix(int requestcode);

    /***
     * 取得参数集
     * @param requestcode
     * @return
     */
    Map<String, String> getParmas(int requestcode);

    /***
     * 取得json串参数
     * @param requestcode
     * @return
     */
   // String getJsonString(int requestcode);



}
