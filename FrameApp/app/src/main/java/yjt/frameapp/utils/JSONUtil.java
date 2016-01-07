package yjt.frameapp.utils;

import com.google.gson.Gson;
import java.io.Serializable;
import yjt.frameapp.bean.BaseBean;

/**
 * Created by yujiangtao on 2015/12/28.
 */
public class JSONUtil {
    /**
     * 将json数据直接解析为User对象
     *
     *
     * @param jsonData
     */
    public static BaseBean parseBeanFromJson(String jsonData, Class<?> class1) {
        Gson gson = new Gson();
        try {
            return (BaseBean) gson.fromJson(jsonData, class1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 将json数据直接解析为User对象
     *
     * @param jsonData
     */
    public static Serializable parseBeanFromJsonToObj(String jsonData, Class<?> class1) {
        Gson gson = new Gson();
        try {
            return (Serializable) gson.fromJson(jsonData, class1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
