package yjt.frameapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 不同时区对应的时间处理工具类
 *
 * @author yujiangtao
 *
 * @date 2014年7月31日
 */
public class TimeZoneUtil {

	/**
	 * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
	 *
	 * @return
	 */
	public static boolean isInEasternEightZones() {
		boolean defaultVaule = true;
		if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
			defaultVaule = true;
		else
			defaultVaule = false;
		return defaultVaule;
	}

	/**
	 * 根据不同时区，转换时间 2014年7月31日
	 *
	 * @return
	 */
	public static Date transformTime(Date date, TimeZone oldZone,
									 TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;

	}

	public static Long stringToTime(String Time, int mode) {
		Date dt = new Date();

		SimpleDateFormat Format;

		switch (mode) {
			case 0:
				Time = Time.replace("T", " ");

				Time = Time.substring(0, 18);

				Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;

			case 1:
				String[] array_1 = Time.split(" ");
				Time = MonStringFormat(array_1[1].toString()) + " "
						+ array_1[2].toString() + " " + array_1[3].toString() + " "
						+ array_1[5].toString();
				Format = new SimpleDateFormat("MM dd HH:mm:ss yyyy");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;

			case 2:
				Time = Time.substring(5, 25);
				String[] array_2 = Time.split(" ");
				Time = array_2[0].toString() + " "
						+ MonStringFormat(array_2[1].toString()) + " "
						+ array_2[2].toString() + " " + array_2[3].toString();
				Format = new SimpleDateFormat("dd MM yyyy HH:mm:ss");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
			case 3:
				Format = new SimpleDateFormat("MM/dd HH:mm");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
			case 4:
				Format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;

			case 5:
				Format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
			case 6:
				Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
			case 7:
				Format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
			case 8:
				Format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
			case 9:
				Format = new SimpleDateFormat("yyyy-MM");
				try {
					dt = Format.parse(Time);
				} catch (Exception e) {
				}
				break;
		}

		return dt.getTime();
	}

	public static String timeToString(Long sec, int mode) {
		Date date = new Date();
		date.setTime(sec);
		SimpleDateFormat Format;
		String time = "";
		switch (mode) {
			case 0:
				Format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				time = Format.format(date);
				break;

			case 1:
				Format = new SimpleDateFormat("yyyy/MM/dd");
				time = Format.format(date);
				break;

			case 2:
				Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				time = Format.format(date);
				break;

			case 3:
				Format = new SimpleDateFormat("yyyy-MM-dd");
				time = Format.format(date);
				break;

			case 4:
				Format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				time = Format.format(date);
				break;

			case 5:
				Format = new SimpleDateFormat("yyyy年MM月dd日");
				time = Format.format(date);
				break;

			case 6:
				Format = new SimpleDateFormat("HH:mm:ss");
				time = Format.format(date);
				break;

			case 7:
				Format = new SimpleDateFormat("MM/dd HH:mm");
				time = Format.format(date);
				break;

			case 8:
				Format = new SimpleDateFormat("HH:mm");
				time = Format.format(date);
				break;
			case 9:
				Format = new SimpleDateFormat("MM/dd\nHH:mm");
				time = Format.format(date);
				break;
			case 10:
				Format = new SimpleDateFormat("yyyy:MM:dd");
				time = Format.format(date);
				break;
			case 11:
				Format = new SimpleDateFormat("yyyy-MM");
				time = Format.format(date);
				break;
			case 12:
				Format=new SimpleDateFormat("yyyyMM");
				time=Format.format(date);
				break;
		}

		return time;
	}
	public static long h_m_tolong(int h, int m) {
		long t = h * 60 * 60 * 1000L + m * 60 * 1000;
		return t;
	}
	public static String getH265TimeStamp() {
		String timestamp = timeToString(System.currentTimeMillis(), 0)
				.replaceAll("/", "").replaceAll(":", "").replace(" ", "");
		return timestamp;
	}

	public static Long GMTSecToLocalSec(Long GMT_SEC) {
		return GMT_SEC + TimeZone.getDefault().getRawOffset();
	}

	public static Long LocalSecToGMTSec(Long LOCAL_SEC) {
		return LOCAL_SEC - TimeZone.getDefault().getRawOffset();
	}

	public static String MonStringFormat(String MonString) {
		String MM = "";
		String[] arrayW = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		String[] arrayD = { "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12" };

		for (int n = 0; n < 12; n++) {
			if (arrayW[n].contains(MonString))
				MM = arrayD[n].toString();
		}

		return MM;
	}
	/**
	 * 获得指定日期的前一天
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static String getBeforeOneDay(String time) {//可以用new Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c
				.getTime());
		return dayBefore;
	}
	/**
	 * 获得指定日期的前一天
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */
	public static String getBeforeOneDayF(String time) {//可以用new Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);

		String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c
				.getTime());
		return dayBefore;
	}

	/**
	 * 获得指定日期的后一天
	 *
	 * @param specifiedDay
	 * @return
	 */
	public static String getAfterOneDay(String specifiedDay) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day + 1);

		String dayAfter = new SimpleDateFormat("yyyyMMdd")
				.format(c.getTime());
		return dayAfter;
	}
}
