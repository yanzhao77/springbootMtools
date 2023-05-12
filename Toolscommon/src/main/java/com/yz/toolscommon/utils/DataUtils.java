package com.yz.toolscommon.utils;

import com.yz.toolscommon.FileUtil.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataUtils
{
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
     * @param firstLoginTime 最小的时间
     * @param nowTime        传递最大的时间
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

    public static void main(String[] args) throws IOException {

        String ssssssYY = getNowDateFormat("HHddMMYY", getNowDate());
        System.out.println(ssssssYY);
    }

    public void loadPropertiesFile() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("emailTxt/aaa/test.txt"));
        System.out.println(properties);
    }

    /**
     * <pre>
     * 実行開始時点などの日付を参照する。
     * </pre>
     *
     * @param format フォーマット
     * @param inDate データ
     * @return 実行開始時点などの日付
     */
    public static int getDate(String format, Date inDate) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);

        return Integer.valueOf(sdf.format(inDate));

    }

    public static String getNowDateTime() {
        return getNowDateFormat("YYYYMMddHHmmss", getNowDate());
    }

    public static String getNowDateStr() {
        return getNowDateFormat("YYYYMMdd", getNowDate());
    }

    /**
     * フォーマット データ取得
     *
     * @param format フォーマット
     * @return
     */
    public static String getNowDateFormat(String format, Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * データ取得
     *
     * @return 日付
     */
    public static Date getNowDate() {
        String envDate = System.getProperty("DATE");
        if (StringUtils.isBlank(envDate)) {
            return new Date();
        } else {
            return stringToDate(envDate);
        }
    }

    public static String updateDateFormatType(String oldDate, String oldDateFormat, String newDateFormat) throws ParseException {
        Date parseDate = new SimpleDateFormat(oldDateFormat).parse(oldDate);
        return getNowDateFormat(newDateFormat, parseDate);
    }

    public static boolean dateCheck(String year, String day) {
        int yearInt = Integer.parseInt(year);
        int dayInt = Integer.parseInt(day);
        boolean yearFlag = Integer.parseInt(year) <= getDate("YYYY");
        if (yearFlag) {
            int yearDayCount = checkLeapYear(yearInt) ? 366 : 365;
            if (dayInt > 0 && dayInt <= yearDayCount) {
                return true;
            }
        }
        return false;
    }


    /**
     * 日付取得
     *
     * @param format フォーマット
     * @return 取得日付
     */
    public static int getDate(String format) {

        String envDate = System.getProperty("DATE");
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        if (StringUtils.isBlank(envDate)) {
            envDate = sdf.format(new Date());
        } else {
            envDate = sdf.format(stringToDate(envDate));
        }

        return Integer.valueOf(envDate);

    }

    /**
     * <pre>
     * 実行開始時点などの時間を参照する。
     * </pre>
     *
     * @param format フォーマット
     * @param inTime データ
     * @return 実行開始時点などの日付
     */
    public static int getTime(String format, Date inTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return Integer.parseInt(sdf.format(inTime).substring(0, format.length()));
    }

    /**
     * うるう年かどうかを判断する
     *
     * @param year
     * @return
     */
    public static boolean checkLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 文字列を日付に変換する
     *
     * @param inDate 日付文字列
     * @param obj    Object
     * @return 変換日付
     */
    public static Date stringToDate(String inDate, Object... obj) {
        SimpleDateFormat sdf = null;
        Date outDate = null;
        if (inDate == null) {
            return outDate;
        }
        switch (inDate.length()) {
            case 8:
                sdf = new SimpleDateFormat("yyyyMMdd");
                break;
            case 7:

                String n = inDate.substring(0, 1);
                int year = Integer.parseInt(inDate.substring(1, 3));
                if (n.equals("1")) {
                    if (year > 46) {
                        return outDate;
                    }
                } else if (n.equals("2")) {
                    if (year > 16) {
                        return outDate;
                    }
                } else if (n.equals("3")) {
                    if (year > 63) {
                        return outDate;
                    }
                } else if (n.equals("4")) {

                } else {
                    return outDate;
                }

                inDate = inDate.substring(3);
                sdf = new SimpleDateFormat("MMdd");
                break;
            case 6:

                if (obj != null && obj.length > 0) {
                    int dateValue = (int) obj[0];
                    if (Integer.parseInt(inDate.substring(0, 2)) < dateValue) {
                        inDate = "20" + inDate;
                        sdf = new SimpleDateFormat("yyyyMMdd");
                    } else {
                        sdf = new SimpleDateFormat("yyMMdd");
                    }
                } else {
                    sdf = new SimpleDateFormat("yyMMdd");
                }

                break;
            default:
                break;
        }
        try {
            outDate = sdf.parse(inDate);
        } catch (ParseException e) {
        }
        return outDate;
    }

    public static int runingTime(String startNowDateTime, String nowDateTime) throws ParseException {
        Date startNowDate = new SimpleDateFormat("YYYYMMddHHmmss").parse(startNowDateTime);
        Date endNowDate = new SimpleDateFormat("YYYYMMddHHmmss").parse(nowDateTime);
        return differentDaysByMillisecond(startNowDate, endNowDate);
    }

    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }
}
