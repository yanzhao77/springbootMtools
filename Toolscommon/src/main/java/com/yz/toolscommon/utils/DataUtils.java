package com.yz.toolscommon.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DataUtils {
	public String getDtaDifference(Date startTime, Date stopTime) throws ParseException {
		long l = startTime.getTime() - stopTime.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		long hour = (l / (60 * 60 * 1000) - day * 24);
		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long second = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//		long between = (startTime.getTime() - stopTime.getTime()) / 1000;// 除以1000是为了转换成秒
//		long day1 = between / (24 * 3600);
//		long hour1 = between % (24 * 3600) / 3600;
//		long min1 = between % 3600 / 60;
//		long second1 = between % 60 / 60;
		return "" + day + "天" + hour + "小时" + min + "分" + second + "秒";
	}

	/**
	 * 使用Calendar对象计算时间差，可以按照需求定制自己的计算逻辑
	 * 
	 * @param strDate
	 * @throws ParseException
	 */
	public static void calculateTimeDifferenceByCalendar(String strDate) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = formatter.parse(strDate);

		Calendar c1 = Calendar.getInstance(); // 当前日期
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date); // 设置为另一个时间

		int year = c1.get(Calendar.YEAR);
		int oldYear = c2.get(Calendar.YEAR);

		// 这里只是简单的对两个年份数字进行相减，而没有考虑月份的情况
		System.out.println("传入的日期与今年的年份差为：" + (year - oldYear));
	}

	/**
	 * 
	 * @param firstLoginTime 最小的时间
	 * @param nowTime传递最大的时间
	 * @return
	 */

	public static Map<String, Long> getDistanceTime2(Date firstLoginTime, Date nowTime) {
		Map<String, Long> dataMap = new HashMap();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1;
		d1 = nowTime;
		Date d2 = firstLoginTime;// 用户初次登录时间
		long diff = d1.getTime() - d2.getTime(); // 当前的时间减去我初次登陆的时间如果大于等于2小时
		long seconds = diff / (1000); // 共计秒数
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		dataMap.put("hours", hours);
		dataMap.put("minutes", minutes);
		dataMap.put("seconds", seconds);
		dataMap.put("days", days);
		return dataMap;

	}

	public static String getDistanceTime(Date startTime, Date stopTime) {
		long diff = stopTime.getTime() - startTime.getTime(); // 当前的时间减去我初次登陆的时间如果大于等于2小时
		long seconds = diff / (1000); // 共计秒数
		long days = diff / (1000 * 60 * 60 * 24);
		long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
		return "" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
	}

	public static void main(String[] args) {
		Date startTime = new Date();

		System.err.println(startTime);
		try {
			Thread.sleep(5000);
			DataUtils dataUtils = new DataUtils();
			Date stopTime = new Date();
			String str = getDistanceTime(startTime, stopTime);
//			Long dtaDifference = json.get("seconds");
//			String dtaDifference = dataUtils.getDtaDifference(startTime, stopTime);
			System.err.println(str);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
