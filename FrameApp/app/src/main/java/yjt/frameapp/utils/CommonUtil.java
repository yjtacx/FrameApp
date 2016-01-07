package yjt.frameapp.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import yjt.frameapp.R;

public class CommonUtil {
    /**
     * 判断是否是手机号
     * 电信
     * 中国电信手机号码开头数字
     * 2G/3G号段（CDMA2000网络）133、153、180、181、189
     * 4G号段 177
     * 联通
     * <p/>
     * 中国联通手机号码开头数字
     * 2G号段（GSM网络）130、131、132、155、156
     * 3G上网卡145
     * 3G号段（WCDMA网络）185、186
     * 4G号段 176、185[1]
     * 移动
     * <p/>
     * 中国移动手机号码开头数字
     * 2G号段（GSM网络）有134x（0-8）、135、136、137、138、139、150、151、152、158、159、182、183、184。
     * 3G号段（TD-SCDMA网络）有157、187、188
     * 3G上网卡 147
     * 4G号段 178
     * 补充
     * <p/>
     * 14号段以前为上网卡专属号段，如中国联通的是145，中国移动的是147等等。
     * 170号段为虚拟运营商专属号段，170号段的 11 位手机号前四位来区分基础运营商，其中 “1700” 为中国电信的转售号码标识，“1705” 为中国移动，“1709” 为中国联通。
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,1,5-9])|(17[6，7,8]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    String str = "^[1-9][0-9]{5}$";

    /**
     * 判断邮编
     *
     * @param
     * @return
     */
    public static boolean isZipNO(String zipString) {
        String str = "^[1-9][0-9]{5}$";
        return Pattern.compile(str).matcher(zipString).matches();
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static int getImageIDfromName(String name) {
        Class<yjt.frameapp.R.mipmap> cls = R.mipmap.class;
        try {
            int value = cls.getDeclaredField(name)
                    .getInt(null);
            return value;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return R.mipmap.ic_launcher;
    }

    public static Drawable getImageFromName(Context c, String name) {
        int id = getImageIDfromName(name);
        return c.getResources().getDrawable(id);
    }

    public static double getTwoPointDouble(double val, int precision) {
        double ret = 0.00;
        try {
            double factor = Math.pow(10, precision);
            ret = Math.floor(val * factor + 0.5) / factor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static String getTwoPointDouble(double d) {
        if (d == 0) return "0.00";
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            String text = df.format(d);
            if (text.endsWith("00")) return text.substring(0, text.length() - 3);
            if (text.endsWith("0")) return text.substring(0, text.length() - 1);
            return text;
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String getTwoPointString(double d) {
        if (d == 0) return "0.00";
        try {
            DecimalFormat df = new DecimalFormat("######0.00");
            String text = df.format(d);
            return text;
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String getOnePointDouble(double d) {
        if (d == 0) return "0.0";
        try {
            DecimalFormat df = new DecimalFormat("######0.0");
            String text = df.format(d);
            if (text.endsWith("0")) return text.substring(0, text.length() - 1);
            return text;
        } catch (Exception e) {
            return "0.0";
        }
    }

    //关键代码 执行序列化和反序列化  进行深度拷贝
    public static <T> List<T> deepCopy(List<T> src) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(src);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            @SuppressWarnings("unchecked")
            List<T> dest = (List<T>) in.readObject();
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<T>(src);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<T>(src);
        }
    }

}
