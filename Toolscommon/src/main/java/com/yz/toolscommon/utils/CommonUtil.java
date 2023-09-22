package com.yz.toolscommon.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yz.toolscommon.config.CommonConst;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import lombok.SneakyThrows;

/**
 * Common class of CommonUtil.
 *
 * @author SoftRoad
 */
@Slf4j
public class CommonUtil {


	/**
	 * 置き換える必要のあるJavaテキスト
	 */
	private static Map<String, String> replaceCharacterMap = new HashMap<>();
	/**
	 * 削除するJavaテキスト
	 */
	private static List<String> removeCharacterList = new ArrayList<>();
	static {
		replaceCharacterMap.put("（", null);
		replaceCharacterMap.put("）", null);
		replaceCharacterMap.put("・", "_");
		replaceCharacterMap.put("-", "_");
		replaceCharacterMap.put("／", "_");
		replaceCharacterMap.put("－", "_");
		replaceCharacterMap.put("/", "_");
		replaceCharacterMap.put("#", "o");
		replaceCharacterMap.put("＃", "Ｏ");
		replaceCharacterMap.put("№", "NO");
		replaceCharacterMap.put(".", "_");
		replaceCharacterMap.put("〓", "2");
		replaceCharacterMap.put("(", null);
		replaceCharacterMap.put(")", null);
		replaceCharacterMap.put("~", null);
		replaceCharacterMap.put("～", null);
		replaceCharacterMap.put("☆", null);
		replaceCharacterMap.put("^", null);
		replaceCharacterMap.put("＾", null);
		replaceCharacterMap.put("．", "_");
		replaceCharacterMap.put("，", null);
		replaceCharacterMap.put("＋", null);
		replaceCharacterMap.put("：", null);
		replaceCharacterMap.put("”", null);
		replaceCharacterMap.put("○", "o");
		replaceCharacterMap.put(" ", null);
		replaceCharacterMap.put("＜", null);
		replaceCharacterMap.put("①", "1");
		replaceCharacterMap.put("②", "2");
		replaceCharacterMap.put("③", "3");
		replaceCharacterMap.put("④", "4");
		replaceCharacterMap.put("⑤", "5");
		replaceCharacterMap.put("⑥", "6");
		replaceCharacterMap.put("⑦", "7");
		replaceCharacterMap.put("⑧", "8");
		replaceCharacterMap.put("⑨", "9");
		replaceCharacterMap.put("⑩", "10");
		replaceCharacterMap.put("⑪", "11");
		replaceCharacterMap.put("⑫", "12");
		replaceCharacterMap.put("⑬", "13");
		replaceCharacterMap.put("⑭", "14");
		replaceCharacterMap.put("⑮", "15");
		replaceCharacterMap.put("⑯", "16");
		replaceCharacterMap.put("⑰", "17");
		replaceCharacterMap.put("⑱", "18");
		replaceCharacterMap.put("⑲", "19");
		replaceCharacterMap.put("⑳", "20");
		replaceCharacterMap.put("'", null);
		replaceCharacterMap.put("\\", "Y");
		replaceCharacterMap.put("@", "A");
		replaceCharacterMap.put("$", "S");
		replaceCharacterMap.put("%", "L");
		replaceCharacterMap.put("&", "B");
		replaceCharacterMap.put("NULL", "N_VAR");
		replaceCharacterMap.put("*", "X");

		removeCharacterList.add("_");
		removeCharacterList.add("＿");
		removeCharacterList.add(" ");// 半幅スペース
		removeCharacterList.add("　");// 全幅スペース
	}


	/**
	 * get the job name
	 * 
	 * @param className
	 * @return
	 */
	public static String getJobName(String className) {
		String[] array = className.split(".");
		return array[array.length - 2].toUpperCase();
	}

	/**
	 * return the reply message
	 * 
	 * @param str
	 * @return
	 */
	public static String getReply(String str) {
		if (str.equals("")) {

			String[] array = str.replace("[", "").replace("]", "").split(",");
			return array[0];
		}

		return str;
	}

	/**
	 * *blank *blanks
	 * 
	 * @param len
	 * @return String
	 */
	public static String blank(int len) {
		return CommonConst.BLANK.repeat(len);
	}

	/**
	 * *blank *blanks
	 * 
	 * @param len
	 * @param size
	 * @return String[]
	 */
	public static String[] blankArr(int size, int len) {
		String[] blankArr = new String[size];
		for (int i = 0; i < size; i++) {
			blankArr[i] = blank(len);
		}
		return blankArr;
	}

	/**
	 * numArr
	 * 
	 * @param num
	 * @param size
	 * @return int[]
	 */
	public static int[] numArr(int num, int size) {
		int[] numArr = new int[size];
		for (int i = 0; i < size; i++) {
			numArr[i] = num;
		}
		return numArr;
	}

	/**
	 * numArr
	 * 
	 * @param num
	 * @param size
	 * @return long[]
	 */
	public static long[] numArr(long num, int size) {
		long[] numArr = new long[size];
		for (int i = 0; i < size; i++) {
			numArr[i] = num;
		}
		return numArr;
	}

	/**
	 * numArr
	 * 
	 * @param num
	 * @param size
	 * @return BigDecimal[]
	 */
	public static BigDecimal[] numArr(BigDecimal num, int size) {
		BigDecimal[] numArr = new BigDecimal[size];
		for (int i = 0; i < size; i++) {
			numArr[i] = num;
		}
		return numArr;
	}

	/**
	 * strArr
	 * 
	 * @param str
	 * @param size
	 * @return String[]
	 */
	public static String[] strArr(String str, int size) {
		String[] strArr = new String[size];
		for (int i = 0; i < size; i++) {
			strArr[i] = str;
		}
		return strArr;
	}

	/**
	 * *zero *zeros
	 * 
	 * @param len
	 * @return String
	 */
	public static String zero(int len) {
		return CommonConst.ZERO.repeat(len);
	}

	/**
	 * *zero *zeros
	 * 
	 * @param len
	 * @param size
	 * @return String[]
	 */
	public static String[] zeroArr(int size, int len) {
		String[] zeroArr = new String[size];
		for (int i = 0; i < size; i++) {
			zeroArr[i] = zero(len);
		}
		return zeroArr;
	}

	/**
	 * *date
	 * 
	 * @param format
	 * @return String
	 */
	public static String date(String... format) {
		// SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd");
		SimpleDateFormat df = new SimpleDateFormat("MMddYYYY"); // 当不指定日期格式时，RPG默认为 MDY，即 月日年 格式
		if (format.length > 0) {
			if ("YMD".equals(format[0])) {
				df = new SimpleDateFormat("YYYYMMdd");
			} else if ("MDY".equals(format[0])) {
				df = new SimpleDateFormat("MMddYYYY");
			} else if ("DMY".equals(format[0])) {
				df = new SimpleDateFormat("ddMMYYYY");
			}
		}
		return df.format(new Date());
	}

	/**
	 * *dateNumber
	 * 
	 * @param format
	 * @return int
	 */
	public static int dateNumber(String... format) {
		return Integer.parseInt(date(format));
	}

	/**
	 * udate
	 * 
	 * @param format
	 * @return String
	 */
	public static String udate(String... format) {
		SimpleDateFormat df = new SimpleDateFormat("YYMMdd");
		if (format.length > 0) {
			if ("MDY".equals(format[0])) {
				df = new SimpleDateFormat("MMddYY");
			} else if ("DMY".equals(format[0])) {
				df = new SimpleDateFormat("ddMMYY");
			} else if ("YMD".equals(format[0])) {
				df = new SimpleDateFormat("YYMMdd");
			}
		}
		return df.format(new Date());
	}

	/**
	 * udateNumber
	 * 
	 * @param format
	 * @return int
	 */
	public static int udateNumber(String... format) {
		return Integer.parseInt(udate(format));
	}

	/**
	 * *year
	 * 
	 * @return String
	 */
	public static String year() {
		SimpleDateFormat df = new SimpleDateFormat("YYYY");
		return df.format(new Date());
	}

	/**
	 * *yearNumber
	 * 
	 * @return int
	 */
	public static int yearNumber() {
		return Integer.parseInt(year());
	}

	/**
	 * uyear
	 * 
	 * @return String
	 */
	public static String uyear() {
		SimpleDateFormat df = new SimpleDateFormat("YY");
		return df.format(new Date());
	}

	/**
	 * uyearNumber
	 * 
	 * @return int
	 */
	public static int uyearNumber() {
		return Integer.parseInt(uyear());
	}

	/**
	 * *month
	 * 
	 * @return String
	 */
	public static String month() {
		SimpleDateFormat df = new SimpleDateFormat("MM");
		return df.format(new Date());
	}

	/**
	 * *monthNumber
	 * 
	 * @return int
	 */
	public static int monthNumber() {
		return Integer.parseInt(month());
	}

	/**
	 * umonth
	 * 
	 * @return String
	 */
	public static String umonth() {
		SimpleDateFormat df = new SimpleDateFormat("MM");
		return df.format(new Date());
	}

	/**
	 * umonthNumber
	 * 
	 * @return int
	 */
	public static int umonthNumber() {
		return Integer.parseInt(umonth());
	}

	/**
	 * *day
	 * 
	 * @return String
	 */
	public static String day() {
		SimpleDateFormat df = new SimpleDateFormat("dd");
		return df.format(new Date());
	}

	/**
	 * *dayNumber
	 * 
	 * @return int
	 */
	public static int dayNumber() {
		return Integer.parseInt(day());
	}

	/**
	 * uday
	 * 
	 * @return String
	 */
	public static String uday() {
		SimpleDateFormat df = new SimpleDateFormat("dd");
		return df.format(new Date());
	}

	/**
	 * udayNumber
	 * 
	 * @return int
	 */
	public static int udayNumber() {
		return Integer.parseInt(uday());
	}


	/**
	 * hivalNumLong
	 * 
	 * @param len
	 * @return
	 */
	public static long hivalNumLong(int len) {

		return Long.parseLong(hivalStrLong(len));
	}

	/**
	 * hivalStrLong
	 * 
	 * @param len
	 * @return
	 */
	public static String hivalStrLong(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("9");
		}
		return br.toString();
	}

	/**
	 * hivalNumInt
	 * 
	 * @param len
	 * @return
	 */
	public static int hivalNumInt(int len) {

		return Integer.parseInt(hivalStrInt(len));
	}

	/**
	 * hivalStrInt
	 * 
	 * @param len
	 * @return
	 */
	public static String hivalStrInt(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("9");
		}
		return br.toString();
	}

	/**
	 * hivalNumBigDecimal
	 * 
	 * @param len
	 * @return
	 */
	public static BigDecimal hivalNumBigDecimal(int len, int desc) {
		return new BigDecimal(hivalStrBigDecimal(len, desc));
	}

	/**
	 * hivalStrBigDecimal
	 * 
	 * @param len
	 * @return
	 */
	public static String hivalStrBigDecimal(int len, int desc) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len - desc; i++) {
			br.append("9");
		}
		br.append(".");
		for (int i = 0; i < desc; i++) {
			br.append("9");
		}
		return br.toString();
	}

	/**
	 * hivalNumIntArr
	 * 
	 * @param len
	 * @return
	 */
	public static int[] hivalNumIntArr(int size, int len) {
		int[] hivalArr = new int[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = hivalNumInt(len);
		}
		return hivalArr;
	}

	/**
	 * hivalStrIntArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] hivalStrIntArr(int size, int len) {
		String[] hivalArr = new String[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = String.valueOf(hivalNumInt(len));
		}
		return hivalArr;
	}

	/**
	 * hivalNumLongArr
	 * 
	 * @param len
	 * @return
	 */
	public static long[] hivalNumLongArr(int size, int len) {
		long[] hivalArr = new long[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = hivalNumLong(len);
		}
		return hivalArr;
	}

	/**
	 * hivalStrLongArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] hivalStrLongArr(int size, int len) {
		String[] hivalArr = new String[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = String.valueOf(hivalNumLong(len));
		}
		return hivalArr;
	}

	/**
	 * hivalNumLongArr
	 * 
	 * @param len
	 * @return
	 */
	public static BigDecimal[] hivalNumBigDecimalArr(int size, int len, int desc) {
		BigDecimal[] hivalArr = new BigDecimal[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = hivalNumBigDecimal(len, desc);
		}
		return hivalArr;
	}

	/**
	 * hivalStrBigDecimalArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] hivalStrBigDecimalArr(int size, int len, int desc) {
		String[] hivalArr = new String[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = String.valueOf(hivalNumBigDecimal(len, desc));
		}
		return hivalArr;
	}

	/**
	 * lovalNumInt
	 * 
	 * @param len
	 * @return
	 */
	public static int lovalNumInt(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("9");
		}
		return Integer.parseInt(br.toString()) * -1;
	}

	/**
	 * lovalStrInt
	 * 
	 * @param len
	 * @return
	 */
	public static String lovalStrInt(int len) {
		return String.valueOf(lovalNumInt(len));
	}

	/**
	 * lovalNumBigDecimal
	 * 
	 * @param len
	 * @return
	 */
	public static BigDecimal lovalNumBigDecimal(int len, int desc) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len - desc; i++) {
			br.append("9");
		}
		br.append(".");
		for (int i = 0; i < desc; i++) {
			br.append("9");
		}
		return new BigDecimal(br.toString()).multiply(new BigDecimal("-1"));
	}

	/**
	 * lovalStrBigDecimal
	 * 
	 * @param len
	 * @return
	 */
	public static String lovalStrBigDecimal(int len, int desc) {
		return String.valueOf(lovalNumBigDecimal(len, desc));
	}

	/**
	 * lovalNumLong
	 * 
	 * @param len
	 * @return
	 */
	public static long lovalNumLong(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("9");
		}
		return Long.parseLong(br.toString()) * -1;
	}

	/**
	 * lovalStrLong
	 * 
	 * @param len
	 * @return
	 */
	public static String lovalStrLong(int len) {
		return String.valueOf(lovalNumLong(len));
	}

	/**
	 * lovalNumIntArr
	 * 
	 * @param len
	 * @return
	 */
	public static int[] lovalNumIntArr(int size, int len) {
		int[] lovalArr = new int[size];
		for (int i = 0; i < size; i++) {
			lovalArr[i] = lovalNumInt(len);
		}
		return lovalArr;
	}

	/**
	 * lovalStrIntArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] lovalStrIntArr(int size, int len) {
		String[] lovalArr = new String[size];
		for (int i = 0; i < size; i++) {
			lovalArr[i] = String.valueOf(lovalNumInt(len));
		}
		return lovalArr;
	}

	/**
	 * lovalNumLongArr
	 * 
	 * @param len
	 * @return
	 */
	public static long[] lovalNumLongArr(int size, int len) {
		long[] hivalArr = new long[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = lovalNumLong(len);
		}
		return hivalArr;
	}

	/**
	 * lovalStrLongArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] lovalStrLongArr(int size, int len) {
		String[] lovalArr = new String[size];
		for (int i = 0; i < size; i++) {
			lovalArr[i] = String.valueOf(lovalNumLong(len));
		}
		return lovalArr;
	}

	/**
	 * lovalNumLongArr
	 * 
	 * @param len
	 * @return
	 */
	public static BigDecimal[] lovalNumBigDecimalArr(int size, int len, int desc) {
		BigDecimal[] hivalArr = new BigDecimal[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = lovalNumBigDecimal(len, desc);
		}
		return hivalArr;
	}

	/**
	 * lovalStrBigDecimalArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] lovalStrBigDecimalArr(int size, int len, int desc) {
		String[] hivalArr = new String[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = String.valueOf(lovalNumBigDecimal(len, desc));
		}
		return hivalArr;
	}

	/**
	 * hivalStr
	 * 
	 * @param len
	 * @return
	 */
	public static String hivalStr(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("↟");
		}
		return br.toString();
	}

	/**
	 * lovalNumArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] hivalStrArr(int size, int len) {
		String[] hivalArr = new String[size];
		for (int i = 0; i < size; i++) {
			hivalArr[i] = hivalStr(len);
		}
		return hivalArr;
	}

	/**
	 * lovalStr
	 * 
	 * @param len
	 * @return
	 */
	public static String lovalStr(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("↡");
		}
		return br.toString();
	}

	/**
	 * lovalNumArr
	 * 
	 * @param len
	 * @return
	 */
	public static String[] lovalStrArr(int size, int len) {
		String[] lovalArr = new String[size];
		for (int i = 0; i < size; i++) {
			lovalArr[i] = lovalStr(len);
		}
		return lovalArr;
	}

	/**
	 * *all'x..'
	 * 
	 * @param x
	 * @param len
	 * @return String
	 */
	public static String all(String x, int len) {
		StringBuilder br = new StringBuilder();
		int index = len;
		// move *all factor2时，异常。（必须指定x）
		if (x != null && x.length() != 0) {
			if (len % x.length() == 0) {
				index = len / x.length();
			} else {
				index = len / x.length() + 1;
			}
		}
		for (int i = 0; i < index; i++) {
			br.append(x);
		}
		return br.toString().substring(0, len);
	}

	/**
	 * allNumber
	 * 
	 * @param x
	 * @param len
	 * @return
	 */
	public static long allNumber(String x, int len) {
		return Long.parseLong(all(x, len));
	}

	/**
	 * allArr
	 * 
	 * @param x
	 * @param size
	 * @param len
	 * @return
	 */
	public static String[] allArr(String x, int size, int len) {
		String[] allArr = new String[size];
		for (int i = 0; i < size; i++) {
			allArr[i] = all(x, len);
		}
		return allArr;
	}

	/**
	 * allArr
	 * 
	 * @param x
	 * @param size
	 * @return
	 */
	public static int[] allArr(int x, int size) {
		int[] allArr = new int[size];
		for (int i = 0; i < size; i++) {
			allArr[i] = x;
		}
		return allArr;
	}

	/**
	 * onInArr
	 * 
	 * @param indi
	 */
	public static void onInArr(String[] indi) {
		if (indi != null) {
			for (int i = 0; i < indi.length; i++) {
				indi[i] = "1";
			}
		}
	}

	/**
	 * offInArr
	 * 
	 * @param indi
	 */
	public static void offInArr(String[] indi) {
		if (indi != null) {
			for (int i = 0; i < indi.length; i++) {
				indi[i] = "0";
			}
		}
	}



	/**
	 * *on
	 * 
	 * @param len
	 * @return String
	 */
	public static String on(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("1");
		}
		return br.toString();
	}

	/**
	 * *off
	 * 
	 * @param len
	 * @return String
	 */
	public static String off(int len) {
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < len; i++) {
			br.append("0");
		}
		return br.toString();
	}

	/**
	 * a. コマンドを実行する。
	 * 
	 * @param cmdCommand cmdコマンド
	 * @return 実施結果
	 */
	public static String execCMD(String cmdCommand) {
		return execCMD(cmdCommand, false);
	}

	/**
	 * a. コマンドを実行する。
	 * 
	 * @param cmdCommand cmdコマンド
	 * @param pasuse     中止フラグ
	 * @return 実施結果
	 */
	public static String execCMD(String cmdCommand, boolean pasuse) {
		StringBuilder stringBuilder = new StringBuilder();
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(cmdCommand);
			BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream(), CommonConst.UTF_8));
			String line = null;
			while ((line = bf.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			log.info(stringBuilder.toString());
			return stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}




	/**
	 * Match Check
	 * 
	 * @param str     文字列
	 * @param stRegex regular Expressions
	 * @return 結果
	 */
	public static boolean match(String str, String stRegex) {
		Pattern pattern = Pattern.compile(stRegex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 右トリムする。
	 *
	 * @param str 文字列
	 * @return トリムされた文字列
	 */
	public static String trimRight(final String str) {
		try {
			return str.replaceAll("[　\\s]+$", "");
		} catch (final NullPointerException e) {
			return str;
		}
	}

	/**
	 * retrieve system value for program parameter(RTVSYSVAL)
	 * 
	 * @param systemParm
	 */
	public static String retrieveSystemValue(String systemParm) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSmmm");
		Date date = new Date(System.currentTimeMillis());

		switch (systemParm) {
		case "QYEAR":
			return formatter.format(date).substring(2, 4);
		case "QMONTH":
			return formatter.format(date).substring(4, 6);
		case "QDAY":
			return formatter.format(date).substring(6, 8);
		case "QHOUR":
			return formatter.format(date).substring(8, 10);
		case "QMINUTE":
			return formatter.format(date).substring(10, 12);
		case "QSECOND":
			return formatter.format(date).substring(12, 14);
		case "QTIME":
			return formatter.format(date).substring(8, 17);
		case "QDATE":
			return formatter.format(date).substring(2, 8);
		case "QDATETIME":
			return formatter.format(date);
		case "QCENTURY":
			return "1";
		default:
			return "";

		}
	}
	

	/**
	 * 対象関係Mapに追加
	 * 
	 * @param relationMap
	 * @param arrObj
	 * @return
	 */
	public static void delRelationMap(Object relationMap, String... arrObj) {

		if (relationMap instanceof HashMap<?, ?>) {
			if (arrObj != null && arrObj.length > 0) {

				for (String obj : arrObj) {
					if (StringUtils.equals("ALL", obj)) {
						((HashMap<?, ?>) relationMap).clear();
					} else {
						if (arrObj.length > 0) {
							((HashMap<?, ?>) relationMap).remove(obj);
						}
					}
				}
			}
		}
	}


	/**
	 * 比較処理「>=」（文字タイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：ge(String,int)メソッドの呼び出し。
	 * 利用例：ge('    ',0) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(String left, int right) {
		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return ge(new BigDecimal(StringUtils.trim(left)), new BigDecimal(right));
	}

	/**
	 * 比較処理「>=」（文字タイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：ge(String,BigDecimal)メソッドの呼び出し。
	 * 利用例：ge('123  ',123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(String left, BigDecimal right) {
		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return ge(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「>=」（数値intタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：ge(int,String)メソッドの呼び出し。
	 * 利用例：ge(123,'123  ') 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(int left, String right) {
		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return ge(new BigDecimal(left), new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「>=」（数値intタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：ge(int,BigDecimal)メソッドの呼び出し。
	 * 利用例：ge(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(int left, BigDecimal right) {

		// 数値比較共通
		return ge(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「>=」（数値BigDecimalタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：ge(BigDecimal,String)メソッドの呼び出し。
	 * 利用例：ge(123.45,"123.45") 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(BigDecimal left, String right) {
		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return ge(left, new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「>=」（数値BigDecimalタイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：ge(BigDecimal,int)メソッドの呼び出し。
	 * 利用例：ge(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(BigDecimal left, int right) {
		// 数値比較共通
		return ge(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「>=」（数値BigDecimalタイプと数値longタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：ge(BigDecimal,long)メソッドの呼び出し。
	 * 利用例：ge(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値longタイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static boolean ge(BigDecimal left, long right) {
		// 数値比較共通
		return ge(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「>=」（数値BigDecimalタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため,比較の共通基底。
	 * 利用方式：ge(BigDecimal,BigDecimal)メソッドの呼び出し。
	 * 利用例：ge(123.45,123.45) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「>=」,flase:「<」
	 */
	public static Boolean ge(BigDecimal left, BigDecimal right) {

		if (left == null) {
			left = new BigDecimal(0);
		}
		if (right == null) {
			right = new BigDecimal(0);
		}
		// Java標準のcompareToを利用して比較する。
		return left.compareTo(right) >= 0;
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Boolean ge(int left, int right) {
		return left >= right;
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Boolean ge(long left, int right) {
		return left >= right;
	}


	/**
	 * 比較処理「>」（文字タイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：gt(String,int)メソッドの呼び出し。
	 * 利用例：gt('    ',0) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(String left, int right) {
		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return gt(new BigDecimal(StringUtils.trim(left)), new BigDecimal(right));
	}

	/**
	 * 比較処理「>」（文字タイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：gt(String,BigDecimal)メソッドの呼び出し。
	 * 利用例：gt('123  ',123) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(String left, BigDecimal right) {
		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return gt(new BigDecimal(StringUtils.trim(left)), right);
	}

	/**
	 * 比較処理「>」（数値intタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：gt(int,String)メソッドの呼び出し。
	 * 利用例：gt(123,'123  ') 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(int left, String right) {
		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return gt(new BigDecimal(left), new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「>」（数値intタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：gt(int,BigDecimal)メソッドの呼び出し。
	 * 利用例：gt(123,123) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(int left, BigDecimal right) {
		// 数値比較共通
		return gt(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「>」（数値BigDecimalタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：gt(BigDecimal,String)メソッドの呼び出し。
	 * 利用例：gt(123.45,"123.45") 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(BigDecimal left, String right) {
		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return gt(left, new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「>」（数値BigDecimalタイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：gt(BigDecimal,int)メソッドの呼び出し。
	 * 利用例：gt(123,123) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(BigDecimal left, int right) {
		// 数値比較共通
		return gt(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「>」（数値BigDecimalタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため,比較の共通基底。
	 * 利用方式：gt(BigDecimal,BigDecimal)メソッドの呼び出し。
	 * 利用例：gt(123.45,123.45) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「>」,flase:「<=」
	 */
	public static Boolean gt(BigDecimal left, BigDecimal right) {

		if (left == null) {
			left = new BigDecimal(0);
		}
		if (right == null) {
			right = new BigDecimal(0);
		}
		// Java標準のcompareToを利用して比較する。
		return left.compareTo(right) > 0;
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Boolean gt(int left, int right) {
		return left > right;
	}

	public static Boolean gt(long left, int right) {
		return left > right;
	}

	/**
	 * 比較処理「=」（ObjectとObject）。
	 *
	 * <pre>
	 * 目的：・一つのスペースと複数スペースがイコール
	 *       ・LOWとHIGHT文字が比較時転換処理
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：eq(Object,Object)メソッドの呼び出し。
	 * 利用例：eq(' ','   ') 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（Objectタイプ）
	 * @param right 計算要素（Objectタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(Object left, Object right) {

		// NULLの場合、空白とする。
		if (left == null) {
			left = StringUtils.EMPTY;
		}
		if (right == null) {
			right = StringUtils.EMPTY;
		}
		// 文字列としての比較
		return eq(left.toString(), right.toString());
	}

	/**
	 * 比較処理「=」（文字タイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・一つのスペースと複数スペースがイコール
	 *       ・LOWとHIGHT文字が比較時転換処理
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：eq(String,String)メソッドの呼び出し。
	 * 利用例：eq(' ','   ') 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(String left, String right) {
		// NULLの場合、空白とする。
		if (left == null) {
			left = StringUtils.EMPTY;
		}
		if (right == null) {
			right = StringUtils.EMPTY;
		}
		
		// 「=」のオプションを渡して、共通比較
		if (right.equals(CommonConst.SYS_BLANK)) {
			return StringUtils.isBlank(left);
		}
		if (left.equals(CommonConst.SYS_BLANK)) {
			return StringUtils.isBlank(right);
		}
		return compareStr(left, right, CommonConst.OP_EQ);
	}

	/**
	 * 比較処理「=」（文字タイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：eq(String,int)メソッドの呼び出し。
	 * 利用例：eq('    ',0) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(String left, int right) {
		// スペースは0と相等
		if (left == null || StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return eq(new BigDecimal(StringUtils.trim(left)), new BigDecimal(right));
	}

	/**
	 * 比較処理「=」（文字タイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：eq(String,BigDecimal)メソッドの呼び出し。
	 * 利用例：eq('123  ',123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(String left, BigDecimal right) {
		// スペースは0と相等
		if (left == null || StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return eq(new BigDecimal(StringUtils.trim(left)), right);
	}

	/**
	 * 比較処理「=」（数値intタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：eq(int,String)メソッドの呼び出し。
	 * 利用例：eq(123,'123  ') 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(int left, String right) {
		// スペースは0と相等
		if (right == null || StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return eq(new BigDecimal(left), new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「=」（数値intタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：eq(int,BigDecimal)メソッドの呼び出し。
	 * 利用例：eq(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(int left, BigDecimal right) {
		// 数値比較共通
		return eq(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「=」（数値BigDecimalタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：eq(BigDecimal,String)メソッドの呼び出し。
	 * 利用例：eq(123.45,"123.45") 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(BigDecimal left, String right) {
		// スペースは0と相等
		if (right == null || StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return eq(left, new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「=」（数値BigDecimalタイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：eq(BigDecimal,int)メソッドの呼び出し。
	 * 利用例：eq(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(BigDecimal left, int right) {
		// 数値比較共通
		return eq(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「=」（数値BigDecimalタイプと数値longタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：eq(BigDecimal,long)メソッドの呼び出し。
	 * 利用例：eq(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値longタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static boolean eq(BigDecimal left, long right) {
		// 数値比較共通
		return eq(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「=」（数値BigDecimalタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため,比較の共通基底。
	 * 利用方式：eq(BigDecimal,BigDecimal)メソッドの呼び出し。
	 * 利用例：eq(123.45,123.45) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「=」,flase:「<>」
	 */
	public static Boolean eq(BigDecimal left, BigDecimal right) {

		if (left == null) {
			left = new BigDecimal(0);
		}
		if (right == null) {
			right = new BigDecimal(0);
		}
		// Java標準のcompareToを利用して比較する。
		return left.compareTo(right) == 0;
	}


	/**
	 * 比較処理「<」（文字タイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：lt(String,int)メソッドの呼び出し。
	 * 利用例：lt('    ',0) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(String left, int right) {

		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return lt(new BigDecimal(StringUtils.trim(left)), new BigDecimal(right));
	}

	/**
	 * 比較処理「<」（文字タイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：lt(String,BigDecimal)メソッドの呼び出し。
	 * 利用例：lt('123  ',123) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(String left, BigDecimal right) {

		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return lt(new BigDecimal(StringUtils.trim(left)), right);
	}

	/**
	 * 比較処理「<」（数値intタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：lt(int,String)メソッドの呼び出し。
	 * 利用例：lt(123,'123  ') 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(int left, String right) {

		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return lt(new BigDecimal(left), new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「<」（数値intタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：lt(int,BigDecimal)メソッドの呼び出し。
	 * 利用例：lt(123,123) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(int left, BigDecimal right) {
		// 数値比較共通
		return lt(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「<」（数値BigDecimalタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：lt(BigDecimal,String)メソッドの呼び出し。
	 * 利用例：lt(123.45,"123.45") 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(BigDecimal left, String right) {

		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return lt(left, new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Boolean lt(long left, int right) {
		return lt(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「<」（数値BigDecimalタイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：lt(BigDecimal,int)メソッドの呼び出し。
	 * 利用例：lt(123,123) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(BigDecimal left, int right) {
		// 数値比較共通
		return lt(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「<」（数値BigDecimalタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため,比較の共通基底。
	 * 利用方式：lt(BigDecimal,BigDecimal)メソッドの呼び出し。
	 * 利用例：lt(123.45,123.45) 結果がfalse
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「<」,flase:「>=」
	 */
	public static Boolean lt(BigDecimal left, BigDecimal right) {

		if (left == null) {
			left = new BigDecimal(0);
		}
		if (right == null) {
			right = new BigDecimal(0);
		}
		// Java標準のcompareToを利用して比較する。
		return left.compareTo(right) < 0;
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Boolean lt(int left, int right) {
		return left < right;
	}


	/**
	 * 比較処理「<=」（文字タイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：le(String,int)メソッドの呼び出し。
	 * 利用例：le('    ',0) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(String left, int right) {
		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return le(new BigDecimal(StringUtils.trim(left)), new BigDecimal(right));
	}

	/**
	 * 比較処理「<=」（文字タイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：le(String,BigDecimal)メソッドの呼び出し。
	 * 利用例：le('123  ',123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(String left, BigDecimal right) {
		// スペースは0と相等
		if (StringUtils.isBlank(left)) {
			left = "0";
		}
		// 数値比較共通
		return le(new BigDecimal(StringUtils.trim(left)), right);
	}

	/**
	 * 比較処理「<=」（数値intタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：le(int,String)メソッドの呼び出し。
	 * 利用例：le(123,'123  ') 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(int left, String right) {
		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return le(new BigDecimal(left), new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「<=」（数値intタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：le(int,BigDecimal)メソッドの呼び出し。
	 * 利用例：le(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値intタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(int left, BigDecimal right) {
		// 数値比較共通
		return le(new BigDecimal(left), right);
	}

	/**
	 * 比較処理「<=」（数値BigDecimalタイプと文字タイプ）。
	 *
	 * <pre>
	 * 目的：・全スペース文字が0と相等
	 *       ・文字タイプと数値intタイプが直接に比較できる
	         ・余計なスペースが不要、トリムが必要
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：le(BigDecimal,String)メソッドの呼び出し。
	 * 利用例：le(123.45,"123.45") 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（文字タイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(BigDecimal left, String right) {
		// スペースは0と相等
		if (StringUtils.isBlank(right)) {
			right = "0";
		}
		// 数値比較共通
		return le(left, new BigDecimal(StringUtils.trim(right)));
	}

	/**
	 * 比較処理「<=」（数値BigDecimalタイプと数値intタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため
	 *       違い数値タイプの計算を共通化する。
	 * 利用方式：le(BigDecimal,int)メソッドの呼び出し。
	 * 利用例：le(123,123) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値intタイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(BigDecimal left, int right) {
		// 数値比較共通
		return le(left, new BigDecimal(right));
	}

	/**
	 * 比較処理「<=」（数値BigDecimalタイプと数値BigDecimalタイプ）。
	 *
	 * <pre>
	 * 目的：業務ロジックに計算統一するため,比較の共通基底。
	 * 利用方式：le(BigDecimal,BigDecimal)メソッドの呼び出し。
	 * 利用例：le(123.45,123.45) 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（数値BigDecimalタイプ）
	 * @param right 計算要素（数値BigDecimalタイプ）
	 * @return 比較結果 true:「<=」,flase:「>」
	 */
	public static Boolean le(BigDecimal left, BigDecimal right) {

		if (left == null) {
			left = new BigDecimal(0);
		}
		if (right == null) {
			right = new BigDecimal(0);
		}
		// Java標準のcompareToを利用して比較する。
		return left.compareTo(right) <= 0;
	}

	/**
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static Boolean le(int left, int right) {
		return left <= right;
	}

	/**
	 * 文字比較の共通処理。
	 *
	 * <pre>
	 * 目的：・一つのスペースと複数スペースがイコール
	 *       ・LOWとHIGHT文字が比較時転換処理
	 *       Java標準にこの特性が適用できないため
	 *       共通を作成する。
	 * 利用方式：le(String,String)メソッドの呼び出し。
	 * 利用例：le(' ','   ') 結果がtrue
	 * </pre>
	 *
	 * @param left  計算要素（文字タイプ）
	 * @param right 計算要素（文字タイプ）
	 * @param kbn   比較オプション(GE,GT,EQ,LT,LE)
	 * @return 比較結果
	 */
	public static Boolean compareStr(String left, String right, String kbn) {

		// NULLの場合、空白とする。
		if (StringUtils.isEmpty(left)) {
			left = StringUtils.EMPTY;
		}
		if (StringUtils.isEmpty(right)) {
			right = StringUtils.EMPTY;
		}

		// LOW以外すべて空白の場合、LOWが[ ]になる
		if (StringUtils.replace(left, CommonConst.STR_LOW_VAL, "").length() > 0
				&& StringUtils.isBlank(StringUtils.replace(left, CommonConst.STR_LOW_VAL, ""))) {

			left = " ";
		}
		if (StringUtils.replace(right, CommonConst.STR_LOW_VAL, "").length() > 0
				&& StringUtils.isBlank(StringUtils.replace(right, CommonConst.STR_LOW_VAL, ""))) {
			right = " ";
		}

		// 共通比較実施
		int ret = compareTo(left, right);

		// > or not <= の場合
		if (CommonConst.OP_GT.equals(kbn)) {
			return ret > 0;

			// >= or not < の場合
		} else if (CommonConst.OP_GE.equals(kbn)) {
			return ret >= 0;

			// < or not >= の場合
		} else if (CommonConst.OP_LT.equals(kbn)) {
			return ret < 0;

			// <= or not > の場合
		} else if (CommonConst.OP_LE.equals(kbn)) {
			return ret <= 0;

			// = の場合
		} else if (CommonConst.OP_EQ.equals(kbn)) {
			return ret == 0;

			// != の場合
		} else if (CommonConst.OP_NE.equals(kbn)) {
			return ret != 0;
		}
		return true;
	}

	/**
	 * 文字列の比較
	 *
	 * @param left  比較要素（左）
	 * @param right 比較要素（右）
	 * @return 比較結果
	 * 
	 */
	public static int compareTo(String left, String right) {

		int lenLeft = left.length();
		int lenRight = right.length();
		int len = Math.max(lenLeft, lenRight);

		// 不足の部分、空白で補足
		if (lenLeft > lenRight) {
			right = StringUtils.rightPad(right, len, " ");
		} else if (lenLeft < lenRight) {
			left = StringUtils.rightPad(left, len, " ");
		}

		// CHAR配列を変換
		char[] charArrLeft = left.toCharArray();
		char[] charArrRight = right.toCharArray();

		int ret;

		// 文字ごと比較
		for (int i = 0; i < len; i++) {

			// HIGHTとLOWの処理
			if (charArrLeft[i] == '↡') {
				charArrLeft[i] = (char) Integer.MIN_VALUE;
			} else if (charArrLeft[i] == '↟') {
				charArrLeft[i] = (char) Integer.MAX_VALUE;
			}

			if (charArrRight[i] == '↡') {
				charArrRight[i] = (char) Integer.MIN_VALUE;
			} else if (charArrRight[i] == '↟') {
				charArrRight[i] = (char) Integer.MAX_VALUE;
			}

			// CHAR比較実施
			ret = compareByCharset(charArrLeft[i], charArrRight[i]);

			if (ret != 0) {
				return ret;
			}
		}
		return 0;
	}

	/**
	 * 文字比較(文字コードによる)
	 *
	 * @param left  比較文字１
	 * @param right 比較文字２
	 * @return 比較結果
	 */
	public static int compareByCharset(char left, char right) {

		// UTF-8の場合
		return left - right;
	}

	/**
	 * a. 指定された秒数を待ち。
	 * 
	 * @param sec ： 待ち秒数
	 */
	public static void delay(long sec) {

		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * a. 指定時刻までまち a. 指定時刻が現行時刻より前の場合，翌日の時刻までまち
	 * 
	 * @param time ： 待ち時刻
	 */
	public static void delay(String time) {

		// a.日付時刻のフォーマットを設定
		SimpleDateFormat tf = new SimpleDateFormat(CommonConst.TIME_ISO);
		SimpleDateFormat df = new SimpleDateFormat(CommonConst.DATE_ISO);
		SimpleDateFormat dt = new SimpleDateFormat(CommonConst.TIMESTAMP_ISO);

		// a.システムの日付を取得する。
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		// a.システムの時刻を取得する。
		String currenttime = tf.format(new Date());
		String currentdaytime = dt.format(new Date());

		try {
			// a.指定された再開時刻、システムの時刻をDateに変換する。
			Date dt1 = tf.parse(time);
			Date dt2 = tf.parse(currenttime);

			// a. 再開時刻とシステムの時刻を比較する
			if (dt1.getTime() >= dt2.getTime()) {
				// a. 再開時刻 ＞＝ システムの時刻
				long sleeptime = dt1.getTime() - dt2.getTime();
				delay(sleeptime);

				// a. 再開時刻 ＜ システムの時刻
			} else {
				// a. 明日の日付を取得する。
				calendar.add(Calendar.DATE, 1);
				date = calendar.getTime();
				String day = df.format(date);
				String daytime = new String(day + " " + time);

				// a. 指定された再開時刻、システムの時刻をDateに変換する。
				Date dt3 = dt.parse(daytime);
				Date dt4 = dt.parse(currentdaytime);
				long sleeptime = dt3.getTime() - dt4.getTime();
				delay(sleeptime);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * a.ホストをPINGする
	 * 
	 * @param hostname
	 * @param timeout
	 * @return PINGの結果 TRUE：OK FALSE NG
	 * @throws Exception 例外発行
	 */
	public static boolean pingCheck(String hostname, int... timeout) throws Exception {

		// a.タイムアウトのでオルトが1秒
		int out = 1;
		if (timeout.length > 0) {
			// a.タイムアウトが外部から指定するとき、指定時間を利用する。
			out = timeout[0];
		}
		// a.ホストをPINGする。
		InetAddress address = InetAddress.getByName(hostname);
		boolean flag = address.isReachable(out * 1000);
		if (flag) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * a.対象関係Mapから、解放する。
	 * 
	 * @param relationMap 対象関係Map
	 * @param delObj      削除対象キー
	 */
	public static HashMap<String, String> delRelationMap(HashMap<String, String> relationMap, String[] delObj) {

		if (relationMap == null) {
			relationMap = new HashMap<String, String>();
		}

		// ALLの場合、すべて削除
		if (delObj.length == 1 && StringUtils.equals(CommonConst.RESERVE_ALL, delObj[0])) {

			relationMap.clear();
			return relationMap;
		}

		// a.指定キー対象を削除する。
		for (String obj : delObj) {
			relationMap.remove(obj);
		}
		return relationMap;
	}

	/**
	 * a.対象関係Mapに追加。
	 * 
	 * @param relationMap 対象関係Map
	 * @param obj     利用対象
	 * @param toObj       関連対象
	 */
	public static HashMap<String, String> addRelationMap(HashMap<String, String> relationMap, String obj,
			String toObj) {

		if (relationMap == null) {
			relationMap = new HashMap<String, String>();
		}
		relationMap.put(obj, toObj);
		return relationMap;
	}

	/**
	 * BigDecimalを文字列へ変換
	 * 
	 * @param val BigDecimal値
	 * @param len 文字列長さ
	 * @return
	 */
	public static String toStr(BigDecimal val, int len) {
		return toStr(val, len, 0);
	}

	/**
	 * 
	 * @param val
	 * @param len
	 * @return
	 */
	public static String toStr(long val, int len) {
		return toStr(new BigDecimal(val), len, 0);
	}

	/**
	 * BigDecimalを文字列へ変換
	 * 
	 * @param val BigDecimal値
	 * @param allLen 文字列長さ
	 * @param dec 小数長さ
	 * @return 変換後文字列
	 */
	public static String toStr(BigDecimal val, int allLen, int dec) {

		// 正数部の長さ
		int len = allLen - dec;
		String retStr = null;
		String[] retArr = val.toString().split("\\.");

		if (val.compareTo(new BigDecimal(0)) >= 0) {
			// 正数かつ小数がある
			if (dec > 0) {
				retStr = StringUtils.leftPad(retArr[0], len - 1) + "." + StringUtils.rightPad(retArr[1], dec);
			} else {
				// 正数かつ小数がない
				retStr = StringUtils.leftPad(retArr[0], len);
			}

		} else {
			// マイナスかつ小数がある
			if (dec > 0) {

				retStr = StringUtils.leftPad(retArr[0], len - 1) + "." + StringUtils.rightPad(retArr[1], dec);
			} else {
				// マイナスかつ小数がない
				retStr = StringUtils.leftPad(retArr[0], len);
			}
		}
		return retStr;
	}

	/**
	 * 文字列へ変換
	 * 
	 * @param val 入力文字列
	 * @param len 文字列長さ
	 * @return 変換後文字列
	 */
	public static String toStr(String val, int len) {

		return StringUtils.rightPad(val, len);
	}

	/**
	 * BigDecimalを文字列へ変換
	 * 
	 * @param val BigDecimal値
	 * @param len 文字列長さ
	 * @return
	 */
	public static String toStr(int val, int len) {
		return toStr(new BigDecimal(val), len, 0);
	}


	public static String convertPlace(String convertStr, int endEp) {
		StringBuilder convertSb = new StringBuilder();
		String[] arr1 = convertStr.split("&");
		for (String str : arr1) {
			String[] arr2 = str.split("_");
			String content = arr2[0];
			int endPos = Integer.parseInt(arr2[1]);
			int blankCount = 0;
			try {
				int fieldLen = content.replace("'", "").getBytes("MS932").length;
				blankCount = endPos - fieldLen;
				convertSb.append(StringUtils.leftPad(" ", blankCount));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return convertSb.toString();
	}

	public static <T> List<T> getSubList(List<T> obj, int pageSize, int pageNo) {
		List<T> resultList = new ArrayList<>();
		if (null != obj) {
			pageNo = pageNo > 0 ? pageNo - 1 : 0;
			int realSize = (pageNo + 1) * pageSize;
			realSize = obj.size() > realSize ? realSize : obj.size();
			resultList = obj.subList(pageNo * pageSize, realSize);
		}
		return resultList;
	}

	/**
	 * SQL文変数入替
	 * 
	 * @param sqlStr BigDecimal値
	 * @param clpVars 文字列長さ
	 * @return
	 */
	public static String replaceSqlVar(String sqlStr, Map<String, String> clpVars) {
		Iterator<?> iter = clpVars.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<?, ?> entry = (Entry<?, ?>) iter.next();
			String key = entry.getKey().toString();
			String val = entry.getValue().toString();
			if (sqlStr.contains(key))
				sqlStr = sqlStr.replace(key, "\'" + val + "\'");
		}
		return sqlStr;
	}

	/**
	 * IS NUMERIC判断
	 * 
	 * @param data 入力パラメータ
	 * @return 数値の場合trueを戻す
	 */
	public static boolean isNumeric(final int data) {
		return true;
	}

	/**
	 * IS NUMERIC判断
	 * 
	 * @param data 入力パラメータ
	 * @return 数値の場合trueを戻す
	 */
	public static boolean isNumeric(final long data) {
		return true;
	}

	/**
	 * IS NUMERIC判断
	 * 
	 * @param data 入力パラメータ
	 * @return 数値の場合trueを戻す
	 */
	public static boolean isNumeric(final BigDecimal data) {
		return true;
	}

	/**
	 * IS NUMERIC判断
	 * 
	 * @param data 入力パラメータ
	 * @return 数値の場合trueを戻す
	 */
	public static boolean isNumeric(final String data) {
		if (data == null || data.trim().length() == 0) {
			return false;
		}

		byte[] bs = data.getBytes();

		for (byte bit : bs) {
			if (bit < 48 || bit > 57) {
				return false;
			}
		}

		return true;
	}

	/**
	 * IS NUMERIC判断
	 * 
	 * @param bs 入力パラメータ
	 * @return 数値の場合trueを戻す
	 */
	public static boolean isNumeric(byte[] bs) {
		if (bs == null) {
			return false;
		}

		byte[] leftBs = new byte[bs.length / 2];

		System.arraycopy(bs, 0, leftBs, 0, bs.length / 2);

		for (byte bit : leftBs) {
			if (bit < 48 || bit > 57) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Unicode文字のみを含んでいるかどうかを調べます。
	 * 
	 * @param data 入力パラメータ
	 * @return 結果
	 */
	public static boolean isAlphabetic(final String data) {
		if (data.equals(" "))
			return true;
		return StringUtils.isAlpha(data);
	}

	/**
	 * Unicode文字のみを含んでいるかどうかを調べます。
	 * 
	 * @param data 入力パラメータ
	 * @return 結果
	 */
	public static boolean isAlphabetic(final long data) {
		return false;
	}

	/**
	 * Unicode文字のみを含んでいるかどうかを調べます。
	 * 
	 * @param data 入力パラメータ
	 * @return 結果
	 */
	public static boolean isAlphabetic(final BigDecimal data) {
		return false;
	}

	/**
	 * Unicode大文字のみを含んでいるかどうかを調べます。
	 * 
	 * @param data 入力パラメータ
	 * @return 結果
	 */
	public static boolean isAlphabeticUpper(final String data) {
		return StringUtils.isAlphaSpace(data) && isAllUpperSpaceCase(data);
	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only uppercase characters.
	 * </p>
	 * <p>
	 * {@code null} will return {@code false}. An empty String (length()=0) will
	 * return {@code false}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAllUpperCase(null)   = false
	 * StringUtils.isAllUpperCase("")     = false
	 * StringUtils.isAllUpperCase("  ")   = true
	 * StringUtils.isAllUpperCase("ABC")  = true
	 * StringUtils.isAllUpperCase("aBC")  = false
	 * StringUtils.isAllUpperCase("A C")  = true
	 * StringUtils.isAllUpperCase("A1C")  = false
	 * StringUtils.isAllUpperCase("A/C")  = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if only contains uppercase characters, and is non-null
	 * @since 2.5
	 * @since 3.0 Changed signature from isAllUpperCase(String) to
	 *        isAllUpperCase(CharSequence)
	 */
	private static boolean isAllUpperSpaceCase(final CharSequence cs) {
		if (cs == null || StringUtils.isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!(Character.isUpperCase(cs.charAt(i)) || cs.charAt(i) == 32)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only uppercase characters.
	 * </p>
	 * <p>
	 * {@code null} will return {@code false}. An empty String (length()=0) will
	 * return {@code false}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAllUpperCase(null)   = false
	 * StringUtils.isAllUpperCase("")     = false
	 * StringUtils.isAllUpperCase("  ")   = false
	 * StringUtils.isAllUpperCase("ABC")  = true
	 * StringUtils.isAllUpperCase("aBC")  = false
	 * StringUtils.isAllUpperCase("A C")  = false
	 * StringUtils.isAllUpperCase("A1C")  = false
	 * StringUtils.isAllUpperCase("A/C")  = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if only contains uppercase characters, and is non-null
	 * @since 2.5
	 * @since 3.0 Changed signature from isAllUpperCase(String) to
	 *        isAllUpperCase(CharSequence)
	 */
	private static boolean isAllUpperCase(final CharSequence cs) {
		if (cs == null || StringUtils.isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!(Character.isUpperCase(cs.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Unicode小文字のみを含んでいるかどうかを調べます。
	 * 
	 * @param data 入力パラメータ
	 * @return 結果
	 */
	public static boolean isAlphabeticLower(final String data) {
		return StringUtils.isAlpha(data) && isAllLowerCase(data);
	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only lowercase characters.
	 * </p>
	 * <p>
	 * {@code null} will return {@code false}. An empty CharSequence (length()=0)
	 * will return {@code false}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAllLowerCase(null)   = false
	 * StringUtils.isAllLowerCase("")     = false
	 * StringUtils.isAllLowerCase("  ")   = tru
	 * StringUtils.isAllLowerCase("abc")  = true
	 * StringUtils.isAllLowerCase("abC")  = false
	 * StringUtils.isAllLowerCase("ab c") = true
	 * StringUtils.isAllLowerCase("ab1c") = false
	 * StringUtils.isAllLowerCase("ab/c") = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if only contains lowercase characters, and is non-null
	 * @since 2.5
	 * @since 3.0 Changed signature from isAllLowerCase(String) to
	 *        isAllLowerCase(CharSequence)
	 */
	private static boolean isAllLowerSpaceCase(final CharSequence cs) {
		if (cs == null || StringUtils.isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!(Character.isLowerCase(cs.charAt(i)) || cs.charAt(i) == 32)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the CharSequence contains only lowercase characters.
	 * </p>
	 * <p>
	 * {@code null} will return {@code false}. An empty CharSequence (length()=0)
	 * will return {@code false}.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isAllLowerCase(null)   = false
	 * StringUtils.isAllLowerCase("")     = false
	 * StringUtils.isAllLowerCase("  ")   = false
	 * StringUtils.isAllLowerCase("abc")  = true
	 * StringUtils.isAllLowerCase("abC")  = false
	 * StringUtils.isAllLowerCase("ab c") = false
	 * StringUtils.isAllLowerCase("ab1c") = false
	 * StringUtils.isAllLowerCase("ab/c") = false
	 * </pre>
	 *
	 * @param cs the CharSequence to check, may be null
	 * @return {@code true} if only contains lowercase characters, and is non-null
	 * @since 2.5
	 * @since 3.0 Changed signature from isAllLowerCase(String) to
	 *        isAllLowerCase(CharSequence)
	 */
	private static boolean isAllLowerCase(final CharSequence cs) {
		if (cs == null || StringUtils.isEmpty(cs)) {
			return false;
		}
		final int sz = cs.length();
		for (int i = 0; i < sz; i++) {
			if (!(Character.isLowerCase(cs.charAt(i)))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * IS POSITIVE判断
	 * 
	 * @param data 入力パラメータ
	 * @return data > 0 の場合trueを戻す
	 */
	public static boolean isPositive(int data) {
		return data > 0 ? true : false;
	}

	/**
	 * IS POSITIVE判断
	 * 
	 * @param data 入力パラメータ
	 * @return data > 0 の場合trueを戻す
	 */
	public static boolean isPositive(long data) {
		return data > 0 ? true : false;
	}

	/**
	 * IS POSITIVE判断
	 * 
	 * @param data 入力パラメータ
	 * @return data > 0 の場合trueを戻す
	 */
	public static boolean isPositive(BigDecimal data) {
		return data.compareTo(BigDecimal.ZERO) > 0 ? true : false;
	}

	/**
	 * IS NEGATIVE判断
	 * 
	 * @param data 入力パラメータ
	 * @return data < 0 の場合trueを戻す
	 */
	public static boolean isNegative(int data) {
		return data < 0 ? true : false;
	}

	/**
	 * IS NEGATIVE判断
	 * 
	 * @param data 入力パラメータ
	 * @return data < 0 の場合trueを戻す
	 */
	public static boolean isNegative(long data) {
		return data < 0 ? true : false;
	}

	/**
	 * IS NEGATIVE判断
	 * 
	 * @param data 入力パラメータ
	 * @return data < 0 の場合trueを戻す
	 */
	public static boolean isNegative(BigDecimal data) {
		return data.compareTo(BigDecimal.ZERO) < 0 ? true : false;
	}

	/**
	 * IS ZERO判断
	 * 
	 * @param str 入力パラメータ
	 * @return data = 0 の場合trueを戻す
	 */
	public static boolean isZero(final String str) {
		// TODO
		return isZero(new BigDecimal(str));
	}

	/**
	 * IS ZERO判断
	 * 
	 * @param data 入力パラメータ
	 * @return data = 0 の場合trueを戻す
	 */
	public static boolean isZero(final long data) {
		return 0 == data;
	}

	/**
	 * IS ZERO判断
	 * 
	 * @param data 入力パラメータ
	 * @return data = 0 の場合trueを戻す
	 */
	public static boolean isZero(final BigDecimal data) {
		return data.compareTo(BigDecimal.ZERO) == 0 ? true : false;
	}

	/**
	 * IS ZERO判断
	 * 
	 * @param data 入力パラメータ
	 * @return data = 0 の場合trueを戻す
	 */
	public static boolean isZero(byte[] data) {
		int len = data.length / 2;
		byte[] bs = new byte[len];

		System.arraycopy(data, 0, bs, 0, len);

		for (byte bit : bs) {
			if (bit != 48) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 文字配列はSPACE以外かどうか判断
	 * 
	 * @param data 入力パラメータ
	 * @return SPACE以外の場合falseを戻す
	 */
	public static boolean isBlank(String data) {
		byte[] bs = data.getBytes();

		for (byte bit : bs) {
			if (bit != 32) {
				return false;
			}
		}

		return true;
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal plus(final BigDecimal value1, final BigDecimal value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.add(v2);
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal plus(final Integer value1, final BigDecimal value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : new BigDecimal(value1);
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.add(v2);
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal plus(final BigDecimal value1, final long value2) {
		return plus(value1, new BigDecimal(value2));
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal plus(final long value1, final BigDecimal value2) {
		return plus(new BigDecimal(value1), value2);
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal plus(final BigDecimal value1, final int value2) {
		return plus(value1, new BigDecimal(value2));
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static int plus(final int value1, final int value2) {
		return value1 + value2;
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static long plus(final long value1, final long value2) {
		return value1 + value2;
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static long plus(final long value1, final long value2, final long value3) {
		return value1 + value2 + value3;
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static long plus(final long value1, final long value2, final long value3, final long value4) {
		return value1 + value2 + value3 + value4;
	}

	/**
	 * プラス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static long plus(final long value1, final long value2, final long value3, final long value4,
			final long value5) {
		return value1 + value2 + value3 + value4 + value5;
	}

	/**
	 * マイナス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal minus(final BigDecimal value1, final BigDecimal value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.subtract(v2);
	}

	/**
	 * マイナス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal minus(final BigDecimal value1, final long value2) {

		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = new BigDecimal(value2);
		return v1.subtract(v2);
	}

	/**
	 * マイナス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal minus(final long value1, final BigDecimal value2) {

		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		BigDecimal v1 = new BigDecimal(value1);
		return v1.subtract(v2);
	}

	/**
	 * マイナス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static int minus(final int value1, final int value2) {
		return value1 - value2;
	}

	/**
	 * マイナス
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static long minus(final long value1, final long value2) {
		return value1 - value2;
	}

	/**
	 * 掛け算
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal multiply(final BigDecimal value1, final BigDecimal value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.multiply(v2).setScale(32, RoundingMode.DOWN);
	}

	public static BigDecimal multiply(final int value1, final BigDecimal value2) {
		return multiply(new BigDecimal(value1), value2);
	}

	public static BigDecimal multiply(final BigDecimal value1, final int value2) {
		return multiply(value1, new BigDecimal(value2));
	}

	public static BigDecimal multiply(final BigDecimal value1, final long value2) {
		return multiply(value1, BigDecimal.valueOf(value2));
	}

	/**
	 * 掛け算
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static int multiply(final int value1, final int value2) {
		return value1 * value2;
	}

	/**
	 * 掛け算
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static long multiply(final long value1, final long value2) {
		return value1 * value2;
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final BigDecimal value1, final BigDecimal value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		if (v2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		}
		return v1.divide(v2, 31, RoundingMode.HALF_UP);
	}

	/**
	 * 分割
	 *
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final BigDecimal value1, final BigDecimal value2, boolean isThrow) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		if (!isThrow && v2.compareTo(BigDecimal.ZERO) == 0) {
			return BigDecimal.ZERO;
		} else if (isThrow && v2.compareTo(BigDecimal.ZERO) == 0) {
			throw null;
		}
		return v1.divide(v2, 31, RoundingMode.HALF_UP);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final long value1, final BigDecimal value2) {
		return divide(new BigDecimal(value1), value2, false);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final long value1, final BigDecimal value2, boolean isThrow) {
		return divide(new BigDecimal(value1), value2, isThrow);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final BigDecimal value1, final long value2) {
		return divide(value1, new BigDecimal(value2), false);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final BigDecimal value1, final long value2, boolean isThrow) {
		return divide(value1, new BigDecimal(value2), isThrow);
	}

	public static BigDecimal divide(final BigDecimal value1, final int value2) {
		return divide(value1, new BigDecimal(value2), false);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final BigDecimal value1, final int value2, boolean isThrow) {
		return divide(value1, new BigDecimal(value2), isThrow);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final int value1, final int value2) {
		return divide(new BigDecimal(value1), new BigDecimal(value2), false);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final int value1, final int value2, boolean isThrow) {
		return divide(new BigDecimal(value1), new BigDecimal(value2), isThrow);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final long value1, final long value2) {
		return divide(new BigDecimal(value1), new BigDecimal(value2), false);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final long value1, final long value2, boolean isThrow) {
		return divide(new BigDecimal(value1), new BigDecimal(value2), isThrow);
	}

	public static BigDecimal divide(final long value1, final int value2) {
		return divide(new BigDecimal(value1), new BigDecimal(value2), false);
	}

	/**
	 * 分割
	 * 
	 * @param value1 入力パラメータ
	 * @param value2 入力パラメータ
	 * @return 結果
	 */
	public static BigDecimal divide(final long value1, final int value2, boolean isThrow) {
		return divide(new BigDecimal(value1), new BigDecimal(value2), isThrow);
	}

	/**
	 * 文字配列はSPACE以外かどうか判断(数値型など、文字列取得できないのこと)
	 * 
	 * @param data 入力パラメータ
	 * @return SPACE以外の場合falseを戻す
	 */
	public static boolean isBlank(byte[] data) {
		int len = data.length / 2;
		byte[] bs = new byte[len];

		System.arraycopy(data, 0, bs, 0, len);

		for (byte bit : bs) {
			if (bit != 32) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static BigDecimal rem(final BigDecimal value1, final BigDecimal value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.remainder(v2);
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static BigDecimal rem(final BigDecimal value1, final int value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = new BigDecimal(String.valueOf(value2));
		return v1.remainder(v2);
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static BigDecimal rem(final int value1, final BigDecimal value2) {
		BigDecimal v1 = new BigDecimal(String.valueOf(value1));
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.remainder(v2);
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static BigDecimal rem(final BigDecimal value1, final long value2) {
		BigDecimal v1 = value1 == null ? BigDecimal.ZERO : value1;
		BigDecimal v2 = new BigDecimal(String.valueOf(value2));
		return v1.remainder(v2);
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static BigDecimal rem(final long value1, final BigDecimal value2) {
		BigDecimal v1 = new BigDecimal(String.valueOf(value1));
		BigDecimal v2 = value2 == null ? BigDecimal.ZERO : value2;
		return v1.remainder(v2);
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static long rem(final long value1, final long value2) {
		return Math.floorMod(value1, value2);
	}

	/**
	 * 残りを取る
	 * 
	 * @param value1
	 * @param value2
	 * @return 結果
	 */
	public static int rem(final int value1, final int value2) {
		return Math.floorMod(value1, value2);
	}


	/**
	 * バイトの長さを伸びる
	 * 
	 * @param bt
	 * @param len
	 * @return
	 */
	private static byte[] addBytesLen(byte[] bt, int len) {
		int btLen = bt.length;
		byte[] tempb;

		tempb = new byte[len];
		System.arraycopy(bt, 0, tempb, 0, btLen);
		Arrays.fill(tempb, btLen, len, (byte) 0x40);
		return tempb;
	}

	/**
	 * ソートフィールドに従ってソートし、その結果をSEARCH ALLステートメントの検索に使用します。
	 * 
	 * <pre>
	 * 目的：主キーのサイズに従ってテーブルを並べ替えます
	 * 利用方式：searchAllSort（Integer,Integer,BiFunction <Integer,Integer,Boolean>,
	 * BiConsumer <Integer,Integer>）メソッドを呼び出します。
	 * 利用例：searchAllSort(1, 10, (i, j) -> {...}, (i, j) -> {...})
	 * </pre>
	 * 
	 * @param start      開始データの場所を示します
	 * @param end        終了データの場所を示します
	 * @param biFunction 2つのインデックス位置のデータサイズを比較します
	 * @param biConsumer 2つのインデックス位置でデータを交換します
	 */
	public static void searchAllSort(Integer start, Integer end, BiFunction<Integer, Integer, Boolean> biFunction,
			BiConsumer<Integer, Integer> biConsumer) {
		for (int i = start; i < end; i++) {
			for (int j = i + 1; j < end; j++) {
				Boolean apply = biFunction.apply(i, j);
				if (apply) {
					biConsumer.accept(i, j);
				}
			}
		}
	}

	/**
	 * HULFT配信
	 *
	 * @param fileID
	 * @param sync
	 */
	public static void utlSend(String fileID, String sync) {
		StringBuilder cmdbuf = new StringBuilder();
		// CMDを生成
		cmdbuf.append(CommonConst.HULFT_UTLSEND);
		cmdbuf.append(" -f " + fileID); // FILEID
		if (CommonConst.HULFT_YES.equals(sync)) {
			cmdbuf.append(CommonConst.BLANK).append(CommonConst.LINE).append(CommonConst.HULFT_SYNC); // SYNC
		}
		// CMD呼び出し
		execCMD(cmdbuf.toString());
	}

	/**
	 * 起動HULFT送信プロセス
	 */
	public static void hulsndd() {
		// CMD呼び出し
		execCMD(CommonConst.HULFT_HULSNDD);
	}

	/**
	 * 起動HULFT受信プロセス
	 */
	public static void hulrcvd() {
		// CMD呼び出し
		execCMD(CommonConst.HULFT_HULRCVD);
	}

	/**
	 * 起動HULFTリクエストプロセス
	 */
	public static void hulobsd() {
		// CMD呼び出し
		execCMD(CommonConst.HULFT_HULOBSD);
	}


	/**
	 * Comparison of changes in model.
	 * 
	 * @param o1 changes in model
	 * @param o2 changes in model
	 * @return Comparing results
	 */
	public static <T> boolean isChange(T o1, T o2) {

		String stro1 = o1 != null ? o1.toString() : null;
		String stro2 = o2 != null ? o2.toString() : null;
		return !StringUtils.equals(stro1, stro2);
	}


	/**
	 * Java特殊文字の処理
	 * 
	 * @param word ソース文字列
	 * @return 処理後の文字列
	 */
	public static String javaSpecialCharacterHandle(String word) {
		String target = word;
		for (Entry<String, String> entry : replaceCharacterMap.entrySet()) {
			String repTo = entry.getValue() == null ? "" : entry.getValue();
			if (target.indexOf(entry.getKey()) >= 0) {
				target = target.replace(entry.getKey(), repTo);
			}
		}
		target = javaCharacterRemove(target);
		return target;
	}

	/**
	 * Javaの特殊文字の削除
	 * 
	 * @param word ソース文字列
	 * @return 処理後の文字列
	 */
	public static String javaCharacterRemove(String word) {
		String regex = null;
		for (String item : removeCharacterList) {
			if (regex == null) {
				regex = "\\" + item;
			} else {
				regex += "|\\" + item;
			}
		}
		if (regex != null) {
			String[] wordArray = word.split(regex);
			word = "";
			for (String item : wordArray) {
				if (StringUtils.isNotBlank(item)) {
					word += item;
				}
			}
		}
		return word;
	}

	/**
	 * Force kill process
	 * 
	 * @param pid
	 */
	public static void KillProcess(int pid) {
		String osName = System.getProperty("os.name");
		String killCMD = "";
		String findProcessCMD = "";
		if (osName.startsWith("Windows")) {
			killCMD = "taskkill /pid " + pid + " -t -f";
			findProcessCMD = "tasklist|findstr " + pid;
		} else if (osName.startsWith("Linux")) {
			killCMD = "kill -9 " + pid;
			findProcessCMD = "ps -p  " + pid + " -f";
		}
		execCMD(killCMD);
		String resultfindProcess = execCMD(findProcessCMD);

		if (resultfindProcess.contains(String.valueOf(pid))) {
			log.info("SUCCESS: kill process with PID " + pid);
		}
	}
	
	/**
	 * バックアップテーブル
	 * @param tableName
	 * @param backUpPath
	 * @param typeFile
	 * @throws SneakyThrows
	 */
	@SneakyThrows
	public static void cpyToTap(String tableName, String backUpPath,String typeFile)  {
		ResourceBundle resource = ResourceBundle.getBundle("application");
		String dbpath = resource.getString("db.pg_dump_path").trim();
		String username = resource.getString("spring.datasource.username").trim();   
		
		String jdbcUrl = resource.getString("spring.datasource.jdbcUrl").trim();
		String dbUrl =jdbcUrl.substring(jdbcUrl.indexOf("://")+3, jdbcUrl.indexOf(":5432")).trim();  
		String dbname = jdbcUrl.substring(jdbcUrl.indexOf("5432/")+5).trim();  
		
		if ("0".equals(typeFile)) {
//			FileUtil.copyFile("", backUpPath);
		} else {
			File file = new File(backUpPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			backUpPath = backUpPath + dbname + "." + tableName + ".backup";// バックアップを生成するファイル名

			Runtime rt = Runtime.getRuntime();// JVMの運用環境を得る
			StringBuffer cmdbuf = new StringBuffer();
			cmdbuf.append(dbpath); // データベース実行EXE
			cmdbuf.append(" --dbname=" + dbname);// データベース名
			cmdbuf.append(" --host=" + dbUrl);
			cmdbuf.append(" --username=" + username); // ユーザー名
			cmdbuf.append(" --no-password --data-only"); // テーブルのみのバックアップ
			cmdbuf.append(" --table=" + tableName);// バックアップされたテーブル名
			cmdbuf.append(" --inserts --column-inserts --encoding=UTF8 --disable-dollar-quoting"); // COPYコマンドではなくINSERTコマンドとしてデータをダンプし、列名付きINSERTコマンドとしてデータをダンプする
			cmdbuf.append(" --file=" + backUpPath); // バックアップのパス

			// CMD呼び出し
			rt.exec(cmdbuf.toString());
		}
	}

}
