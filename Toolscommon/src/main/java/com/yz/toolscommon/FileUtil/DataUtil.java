package com.yz.toolscommon.FileUtil;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    public static String getDate(Timestamp timestamp, boolean flag) {
        String str = flag ? "" : ".";
        DateFormat sdf = new SimpleDateFormat("yy" + str + "MM" + str + "dd");
        return sdf.format(timestamp);
    }

    public void aa() {
        Calendar now = Calendar.getInstance();
        String substring = Integer.toString(now.get(Calendar.YEAR)).substring(2);
        System.out.println("年: " + now.get(Calendar.YEAR));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + now.get(Calendar.MINUTE));
        System.out.println("秒: " + now.get(Calendar.SECOND));
        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
        System.out.println(now.getTime());

        System.out.println(now.toString());
    }

    public String getDate(boolean flag) {
        Calendar now = Calendar.getInstance();
        String yearStr = Integer.toString(now.get(Calendar.YEAR)).substring(2);
        String month = String.valueOf(now.get(Calendar.MONTH) + 1).length() == 1 ? "0" + (now.get(Calendar.MONTH) + 1) : String.valueOf(now.get(Calendar.MONTH) + 1);

        int day = now.get(Calendar.HOUR_OF_DAY);
        String str = flag ? "" : ".";
        byte[] n = new byte[]{};
        return yearStr + str + month + str + day;
    }
}
