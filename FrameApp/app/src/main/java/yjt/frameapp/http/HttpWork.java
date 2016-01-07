package yjt.frameapp.http;

import android.text.TextUtils;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.Map;

import yjt.frameapp.bean.BaseBean;
import yjt.frameapp.utils.JSONUtil;

/**
 * Created by yujiangtao on 2015/12/26.
 * 网络请求操作工具类
 */
public class HttpWork implements HttpInter{
    private String HOSTURL = "http://172.168.1.0:8000/ucloud/%s";
    public static final short DEFAULT_REQUEST_CODE = 0x01;
    private HttpInter class_=null;

    public HttpWork(HttpInter webContext){
        this.class_=webContext;
    }

    public void doGet() {
        doGet(DEFAULT_REQUEST_CODE, true, "加载中");
    }

    public void doGet(final short requestcode) {
        doGet(requestcode, true, "加载中");
    }

    public void doGet(final short requestcode, boolean showProgress) {
        doGet(requestcode, showProgress, "加载中");
    }



    /***
     * get请求
     * @param requestcode
     */
    public void doGet( final short requestcode,final boolean showdialog,final String dialogmsg) {
        if(!HttpUtil.isNetworkConnected()){
            netError(requestcode);
            return;
        }
        Map<String,String> params=getParmas(requestcode);
        if(params==null)return;
        OkHttpUtils
                .get()
                .params(getParmas(requestcode))
                .url(getAbsoluteUrl(getUrlSuffix(requestcode)))
                .tag(class_)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onAfter() {
                        super.onAfter();
                        //如果对话框是显示状态，隐藏
                        doAfter(requestcode);
                    }
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        doBefore(requestcode,showdialog,dialogmsg);
                    }
                    @Override
                    public void onError(Request request, Exception e) {
                        doError(requestcode, e);
                    }

                    @Override
                    public void inProgress(float progress) {
                        super.inProgress(progress);
                    }


                    @Override
                    public void onResponse(String response) {
                        //如果对话框是显示状态，隐藏
                        System.out.println(response);
                        if (TextUtils.isEmpty(response)) {
                            //返回数据为""或者为null
                            //处理（）
                            return;
                        }
                        if (BaseBean.class.isAssignableFrom( getParserClass(requestcode))) {
                            BaseBean bean = JSONUtil.parseBeanFromJson(response,
                                    getParserClass(requestcode));
                            if (bean == null) {
                                //解析数据异常
                                //处理（）
                                return;
                            }
                             doSuccess(requestcode, bean, null);

                        } else {
                            doSuccess(requestcode, null, response);
                        }
                    }
                });
    }
    public void doPost() {
        doPost(DEFAULT_REQUEST_CODE, true, "加载中");
    }

    public void doPost(final short requestcode) {
        doPost(requestcode, true, "加载中");
    }

    public void doPost(final short requestcode, boolean showProgress) {
        doPost(requestcode, showProgress, "加载中");
    }
    /***
     * 上传String 参数
     * @param requestcode
     */
    public void doPost(final short requestcode,final boolean showdialog,final String dialogmsg){
        if(!HttpUtil.isNetworkConnected()){
            netError(requestcode);
            return;
        }
        Map<String,String> map= getParmas(requestcode);
        if(map==null)return;
        OkHttpUtils.postString().params(map)
                .url(getAbsoluteUrl(getUrlSuffix(requestcode)))
                .tag(class_)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onAfter() {
                        super.onAfter();
                        //如果对话框是显示状态，隐藏
                        doAfter(requestcode);
                    }
                    @Override
                    public void onError(Request request, Exception e) {
                        doError(requestcode, e);
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        doBefore(requestcode,showdialog,dialogmsg);
                    }
                    @Override
                    public void inProgress(float progress) {
                        super.inProgress(progress);
                    }
                    @Override
                    public void onResponse(String response) {
                        if (TextUtils.isEmpty(response)) {
                            //返回数据为""或者为null
                            //处理（）
                            return;
                        }
                        if (BaseBean.class.isAssignableFrom(getParserClass(requestcode))) {
                            BaseBean bean = JSONUtil.parseBeanFromJson(response,
                                    getParserClass(requestcode));
                            if (bean == null) {
                                //解析数据异常
                                //处理（）
                                return;
                            }
                            doSuccess(requestcode, bean, null);

                        } else {
                            doSuccess(requestcode, null, response);
                        }
                    }
                });
    }
    public void doPostForm() {
        doPost(DEFAULT_REQUEST_CODE, true, "加载中");
    }

    public void doPostForm(final short requestcode) {
        doPost(requestcode, true, "加载中");
    }

    public void doPostForm(final short requestcode, boolean showProgress) {
        doPost(requestcode, showProgress, "加载中");
    }
    /***
     * 上传form
     * @param requestcode
     */
    public void doPostForm(final short requestcode,final boolean showdialog,final String dialogmsg){
        if(!HttpUtil.isNetworkConnected()){
            netError(requestcode);
            return;
        }
        Map<String,String> map= getParmas(requestcode);
        if(map==null)return;
        PostFormBuilder postbuilder=OkHttpUtils.post();
        if(map.containsKey("File")){
            String filesstr=map.get("File");
            map.remove("File");
            String[] f=filesstr.split(",");
            if(f!=null&&f.length>0){
                for (String path:f) {
                    File file=new File(path);
                    if(file!=null&&file.exists()){
                        int start=path.lastIndexOf("/");
                        postbuilder.addFile("File",path.substring(start+1),file);
                    }
                }
            }
        }
        postbuilder.params(map)
                .url(getAbsoluteUrl(getUrlSuffix(requestcode)))
                .tag(class_)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onAfter() {
                        super.onAfter();
                        //如果对话框是显示状态，隐藏
                        doAfter(requestcode);
                    }
                    @Override
                    public void onError(Request request, Exception e) {
                        doError(requestcode, e);
                    }

                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                        doBefore(requestcode,showdialog,dialogmsg);
                    }
                    @Override
                    public void inProgress(float progress) {
                        super.inProgress(progress);
                    }
                    @Override
                    public void onResponse(String response) {
                        if (TextUtils.isEmpty(response)) {
                            //返回数据为""或者为null
                            //处理（）
                            return;
                        }
                        if (BaseBean.class.isAssignableFrom(getParserClass(requestcode))) {
                            BaseBean bean = JSONUtil.parseBeanFromJson(response,
                                    getParserClass(requestcode));
                            if (bean == null) {
                                //解析数据异常
                                //处理（）
                                return;
                            }
                            doSuccess(requestcode, bean, null);

                        } else {
                            doSuccess(requestcode, null, response);
                        }
                    }
                });
    }

    private String getAbsoluteUrl(String parturl) {
        String url = String.format(HOSTURL, parturl);
        return url;
    }
    public final void cancelRequest(HttpInter cls){
        OkHttpUtils.getInstance().cancelTag(cls);
    }

    @Override
    public final void doError(int requestcode, Exception e) {
        class_.doError(requestcode,e);
    }

    @Override
    public final void doSuccess(int requestcode, BaseBean bean, String data) {
        class_.doSuccess(requestcode,bean,data);
    }

    @Override
    public final Class<?> getParserClass(int requestcode) {
        return class_.getParserClass(requestcode);
    }

    @Override
    public final String getUrlSuffix(int requestcode) {
        return class_.getUrlSuffix(requestcode);
    }

    @Override
    public final Map<String, String> getParmas(int requestcode) {
        return class_.getParmas(requestcode);
    }

    @Override
    public final void netError(int requestcode) {
        class_.netError(requestcode);
    }


    @Override
    public final void doBefore(int requestcode, boolean showdialog, String dialogmsg) {
        class_.doBefore(requestcode,showdialog,dialogmsg);
    }

    @Override
    public final void doAfter(int requestcode) {
        class_.doAfter(requestcode);
    }
}
