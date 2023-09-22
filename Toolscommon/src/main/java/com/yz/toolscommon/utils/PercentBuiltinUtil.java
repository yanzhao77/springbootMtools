package com.yz.toolscommon.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;


public class PercentBuiltinUtil {

    private final static String TIME_ISO = "hh.mm.ss";
    private final static String TIME_EUR = "hh.mm.ss";
    private final static String TIME_JIS = "hh:mm:ss";
    private final static List<String> TIME_HMS = Arrays.asList("hh:mm:ss", "hh.mm.ss", "hh,mm,ss", "hh&mm&ss");
    private final static String TIME_USA = "h:mm a";

    private final static List<String> DATE_MDY = Arrays.asList("MM/dd/yy", "MM-dd-yy", "MM.dd.yy", "MM,dd,yy", "MM&dd&yy");
    private final static List<String> DATE_DMY = Arrays.asList("dd/MM/yy", "dd-MM-yy", "dd.MM.yy", "dd,MM,yy", "dd&MM&yy");
    private final static List<String> DATE_YMD = Arrays.asList("yy/mm/dd", "yy-MM-dd", "yy.MM.dd", "yy,MM,dd", "yy&MM&dd");
    private final static List<String> DATE_JUL = Arrays.asList("yy/ddd", "yy-ddd", "yy.ddd", "yy,ddd", "yy&ddd");
    private final static String DATE_ISO = "yyyy-MM-dd";
    private final static String DATE_ISO0 = "yyyyMMdd";
    private static final String DATE_USA = "MM/dd/yyyy";
    private static final String DATE_USA0 = "MMddyyyy";
    private static final String DATE_EUR = "dd.MM.yyyy";
    private static final String DATE_EUR0 = "ddMMyyyy";
    private static final String DATE_JIS = "yyyy-MM-dd";
    private static final String DATE_JIS0 = "yyyyMMdd";
    private static final String DATE_MDY0 = "MMddyy";
    private static final String DATE_DMY0 = "ddMMyy";
    private static final String DATE_YMD0 = "yyMMdd";

    private final static char JIS_SPACE = 12288; // 日文空格
    private final static char OTHER_SPACE = 32; // 英文空格

    public enum DateEnum {
        DATE_MDY, DATE_DMY, DATE_YMD, DATE_MDY0, DATE_DMY0, DATE_YMD0, DATE_JUL, DATE_ISO, DATE_USA, DATE_EUR, DATE_JIS, DATE_ISO0, DATE_USA0, DATE_EUR0, DATE_JIS0;
    }

    public enum DateTimeDiffEnum {
        MSECONDS, SECONDS, MINUTES, HOURS, DAYS, MONTHS, YEARS, MS, S, MN, H, D, M, Y
    }

    public enum TimeEnum {
        HMS, ISO, USA, EUR, JIS;
    }

    /**
     * 取掉字符串左边和右边的空格
     *
     * @param str
     * @return
     */
    public static String trim(String str) {
        if (Objects.isNull(str) || str.trim().length() == 0) {
            return str;
        }
        return trimr(triml(str));
    }

    /**
     * 取掉字符串右边的空格
     *
     * @param str
     * @return
     */
    public static String trimr(String str) {
        if (Objects.isNull(str) || str.trim().length() == 0) {
            return str;
        } else {
            char[] chars = str.toCharArray();
            int last = 0;
            for (int i = chars.length - 1; i > 0; i--) {
                if (chars[i] != OTHER_SPACE && chars[i] != JIS_SPACE) {
                    last = i;
                    break;
                }
            }
            char[] subChars = Arrays.copyOfRange(chars, 0, last + 1);
            return String.valueOf(subChars);
        }
    }

    /**
     * 取掉左空格
     *
     * @param str
     * @return
     */
    public static String triml(String str) {
        if (Objects.isNull(str) || str.trim().length() == 0) {
            return str;
        } else {
            char[] chars = str.toCharArray();
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != OTHER_SPACE && chars[i] != JIS_SPACE) {
                    index = i;
                    break;
                }
            }
            return str.substring(index);
        }

    }

    /**
     * 改变字符串长度为指定长度
     *
     * @param source
     * @param length
     * @return @
     */
    public static String len(String source, int length) {
        if (length < 0) {
            throw new RuntimeException("The extended length must be greater than 0");
        }
        if (Objects.isNull(source)) {
            throw new RuntimeException("source is null");
        }

        String result = "";
        // 补位
        if (length > source.length()) {
            char[] sourceChars = source.toCharArray();
            int fillLength = length - source.length();
            char[] fillChars = new char[fillLength];
            char[] resultChars = new char[sourceChars.length + fillLength];
            Arrays.fill(fillChars, ' ');
            if (sourceChars.length > 0) {
                System.arraycopy(sourceChars, 0, resultChars, 0, sourceChars.length);
                System.arraycopy(fillChars, 0, resultChars, sourceChars.length, fillLength);
            } else {
                System.arraycopy(fillChars, 0, resultChars, 0, fillLength);
            }

            result = String.valueOf(resultChars);
        } else if (length < source.length()) {
            result = source.substring(0, length);
        }
        return result;
    }


    /**
     * 从指定位置搜索目标字符所在位置
     *
     * @param target
     * @param origin
     * @return @
     */
    public static int scan(String target, String origin) {
        if (Objects.isNull(target) || Objects.isNull(origin)) {
            throw new RuntimeException("target or origin is empty");
        }
        int result = origin.indexOf(target);
        return result >= 0 ? result + 1 : 0;
    }

    /**
     * 从指定位置搜索目标字符所在位置
     *
     * @param target
     * @param origin
     * @param index
     * @return @
     */
    public static int scan(String target, String origin, int index) {
        if (Objects.isNull(target) || Objects.isNull(origin)) {
            throw new RuntimeException("target or origin is null");
        }

        if (index < 0) {
            throw new RuntimeException("The starting search position cannot be less than 0");
        }

        if (index > origin.length()) {
            throw new RuntimeException("Search position is greater than the total length of characters");
        }
        String head = origin.substring(0, index - 1);
        String tail = origin.substring(index - 1);

        int headLength = head.length();
        int pos = tail.indexOf(target);

        if (pos < 0) {
            return 0;
        } else {
            pos = pos + headLength;
        }
        return pos + 1;
    }

    /**
     * 不指定替换位置的替换
     *
     * @param replace 替换字符串
     * @param origin  源字符串
     * @return
     */
    public static String replace(String replace, String origin) {
        return replace(replace, origin, 0);
    }

    /**
     * @param replace
     * @param origin
     * @return
     */
    public static String replace(String replace, String origin, int index) {
        if (Objects.isNull(origin) || origin.length() == 0) {
            throw new RuntimeException("origin is null");
        }
        if (Objects.isNull(replace)) {
            throw new RuntimeException("replace is null");
        }
        if (index < 0 || index > origin.length()) {
            throw new RuntimeException("Enter the start position incorrectly");
        }
        if (index == 0 && replace.length() >= origin.length()) {
            return replace;
        }
        char[] replaceChars = replace.toCharArray();
        char[] originChars = origin.toCharArray();
        int newLength = originChars.length - index >= replaceChars.length ? originChars.length : replaceChars.length + index - 1;
        char[] newOriginChars = new char[newLength];
        if (index == 0) {
            System.arraycopy(replaceChars, 0, originChars, 0, replaceChars.length);
            newOriginChars = originChars;
        } else {
            System.arraycopy(originChars, 0, newOriginChars, 0, originChars.length);
            System.arraycopy(replaceChars, 0, newOriginChars, index - 1, replaceChars.length);
        }
        return String.valueOf(newOriginChars);
    }

    /**
     * @param replace 替换字符串
     * @param origin  源字符
     * @param start   表示替换字符串的起始位置（以字符为单位来测量）。若未指定它，则起始位置是源字符串的开始处。值的范围可以是从一个到源字符串的当前长度加一。
     * @param length  表示源字符串中要替换的字符数。若指定零，则在指定的起始位置前插入替换字符串。如果未指定参数，则替换的字符数与替换字符串的长度一样。
     * @return @
     */
    public static String replace(String replace, String origin, int start, int length) {
        if (Objects.isNull(replace)) {
            throw new RuntimeException("replace is null");
        }
        if (Objects.isNull(origin)) {
            throw new RuntimeException("origin is null");
        }
        if (start <= 0 || start > origin.length() + 1) {
            throw new RuntimeException("Enter the start position incorrectly");
        }
        if (length < 0 || length > origin.length()) {
            throw new RuntimeException("Enter the length incorrectly");
        }

        char[] replaceChars = replace.toCharArray();
        char[] originChars = origin.toCharArray();
        if (length == 0) {
            if (start == originChars.length + 1) {
                return origin + replace;
            } else {
                char[] resultChars = new char[replaceChars.length + originChars.length];
                char[] subChars1 = Arrays.copyOf(originChars, start - 1);
                char[] shuChars2 = Arrays.copyOfRange(originChars, start - 1, originChars.length);
                System.arraycopy(subChars1, 0, resultChars, 0, subChars1.length);
                System.arraycopy(replaceChars, 0, resultChars, subChars1.length, replaceChars.length);
                System.arraycopy(shuChars2, 0, resultChars, subChars1.length + replaceChars.length, shuChars2.length);
                return String.valueOf(resultChars);
            }
        } else {
            if (replaceChars.length == length) {
                System.arraycopy(replaceChars, 0, originChars, start - 1, length);
            } else {
                char[] subOriginChars1 = Arrays.copyOf(originChars, start - 1);
                char[] subOriginChars2 = Arrays.copyOfRange(originChars, start + length - 1, originChars.length);
                int newlength = subOriginChars1.length + subOriginChars2.length + replaceChars.length;
                char[] resultChars = new char[newlength];

                System.arraycopy(subOriginChars1, 0, resultChars, 0, subOriginChars1.length);
                System.arraycopy(replaceChars, 0, resultChars, subOriginChars1.length, replaceChars.length);
                System.arraycopy(subOriginChars2, 0, resultChars, start + replaceChars.length - 1, subOriginChars2.length);

                return String.valueOf(resultChars);
            }
        }
        return String.valueOf(originChars);
    }

    /**
     * 把对象转换为压缩十进制格式
     *
     * @return
     */
    public static BigDecimal dec(String numeric) {
        numeric = numeric.replace(" ", "");
        return new BigDecimal(numeric);
    }

    /**
     * 字符类型压缩为10进制
     *
     * @param numeric
     * @param precision
     * @param decimalPlaces
     * @return @
     */
    public static BigDecimal dec(String numeric, int precision, int decimalPlaces) {

        if (Objects.isNull(numeric) || numeric.length() == 0) {
            throw new RuntimeException("Number error");
        }
        if (precision == decimalPlaces) {
            throw new RuntimeException("Number accuracy error");
        }
        numeric = numeric.replace(" ", "");

        int integerBits = precision - decimalPlaces;

        String[] numerics = numeric.split("\\.");

        if (numerics.length > 2) {
            throw new RuntimeException("Number error");
        } else {
            char[] intergerChars = numerics[0].toCharArray();
//			char[] decimalChars = numerics[1].toCharArray();
            if (intergerChars.length > integerBits) {
                throw new RuntimeException("The target for a numeric operation is too small to hold the result.");
            }

            BigDecimal bigDecimal = new BigDecimal(numeric);
            return bigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
        }
    }

    /**
     * @param d
     * @param precision
     * @param decimalPlaces
     * @return @
     */
    public static BigDecimal dech(String d, int precision, int decimalPlaces) {
        BigDecimal bigDecimal = new BigDecimal(d);

        int intBitsLength = bigDecimal.toBigInteger().toString().length();
        if (!((precision - decimalPlaces) == intBitsLength)) {
            throw new RuntimeException("The accuracy setting is wrong");
        }
        return bigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
    }

    /**
     * 指定为参数的数字表达式的绝对值
     *
     * @param str
     * @return
     */
    public static BigDecimal abs(String str) {
        BigDecimal bigDecimal = new BigDecimal(str);
        return bigDecimal.abs();
    }

    /**
     * 指定为参数的数字表达式的绝对值
     *
     * @return
     */
    public static int abs(int i) {
        if (i > Integer.MAX_VALUE || i <= Integer.MIN_VALUE) {
            throw new RuntimeException("Value is out of range");
        }
        return Math.abs(i);
    }

    /**
     * 指定为参数的数字表达式的绝对值
     *
     * @return
     */
    public static long abs(long l) {
        if (l > Long.MAX_VALUE || l < Long.MIN_VALUE) {
            throw new RuntimeException("Value is out of range");
        }
        return Math.abs(l);
    }

    /**
     * 返回 base 字符串的第一个包含 comparator 字符串中没有出现的字符的位置。 如果 base 中的所有字符也都出现在 comparator
     * 中，则此函数返回 0。
     *
     * @param comparator
     * @param base
     * @return @
     */
    public static int check(String comparator, String base) {
        return check(comparator, base, 1);
    }

    /**
     * 返回 base 字符串的第一个包含 comparator 字符串中没有出现的字符的位置。 如果 base 中的所有字符也都出现在 comparator
     * 中，则此函数返回 0。
     *
     * @param comparator
     * @param base
     * @param start
     * @return @
     */
    public static int check(String comparator, String base, int start) {

        if (Objects.isNull(comparator) || Objects.isNull(base)) {
            throw new RuntimeException("Operation object is empty");
        }
        if (start <= 0 || start > base.length()) {
            throw new RuntimeException("Index value cannot be less than 0 and greater than base length");
        }

        char[] baseChars = base.toCharArray();
        char[] comparatorChars = comparator.toCharArray();

        int result = 0;
        int i = 0;
        if (start > 0) {
            i = start - 1;
        }
        Arrays.sort(comparatorChars);
        for (int j = i; j < baseChars.length; j++) {
            if (Arrays.binarySearch(comparatorChars, baseChars[j]) < 0) {
                result = j + 1;
                break;
            }
        }
        return result;
    }

    /**
     * 返回 base 字符串的最后一个包含 comparator 字符串中没有出现的字符的位置。 如果 base 中的所有字符也都出现在 comparator
     * 中，则此函数返回 0。
     *
     * @param comparator
     * @param base
     * @return @
     */
    public static int checkr(String comparator, String base) {
        if (Objects.isNull(comparator) || Objects.isNull(base) || base.length() == 0) {
            throw new RuntimeException("Operation object is empty");
        }
        return checkr(comparator, base, base.length() - 1);
    }

    /**
     * 返回 base 字符串的最后一个包含 comparator 字符串中没有出现的字符的位置。 如果 base 中的所有字符也都出现在 comparator
     * 中，则此函数返回 0。
     *
     * @param comparator
     * @param base
     * @param start
     * @return @
     */
    public static int checkr(String comparator, String base, int start) {
        if (Objects.isNull(comparator) || Objects.isNull(base) || base.length() == 0 || comparator.length() == 0) {
            throw new RuntimeException("Operation object is empty");
        }

        if (start <= 0 || start > base.length()) {
            throw new RuntimeException("start error");
        }

        char[] subBaseChars = base.substring(0, start).toCharArray();
        char[] comparatorChars = comparator.toCharArray();

        int result = 0;
        Arrays.sort(comparatorChars);
        for (int j = 0; j < start; j++) {
            if (Arrays.binarySearch(comparatorChars, subBaseChars[j]) < 0) {
                result = j + 1;
                break;
            }
        }
        return result;
    }

    /**
     * @param expression
     * @param fromatEnum
     * @return
     */
    public static Date date(String expression, DateEnum fromatEnum) {
        if (Objects.isNull(expression) || expression.length() == 0) {
            throw new RuntimeException("expression is null or empty");
        }
        if (Objects.isNull(fromatEnum)) {
            throw new RuntimeException("fromat is null or empty");
        }
        Date date = null;
        switch (fromatEnum) {
            case DATE_MDY: {
                try {
                    String format = "/";
                    for (String str : DATE_MDY) {
                        if (str.charAt(2) == expression.charAt(2) && expression.charAt(2) == expression.charAt(5)) {
                            format = str;
                        }
                    }
                    DateFormat df = new SimpleDateFormat(format);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_DMY: {
                try {
                    String format = "/";
                    for (String str : DATE_DMY) {
                        if (str.charAt(2) == expression.charAt(2) && expression.charAt(2) == expression.charAt(5)) {
                            format = str;
                        }
                    }
                    DateFormat df = new SimpleDateFormat(format);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_YMD: {
                try {
                    String format = "/";
                    for (String str : DATE_YMD) {
                        if (str.charAt(2) == expression.charAt(2) && expression.charAt(2) == expression.charAt(5)) {
                            format = str;
                        }
                    }
                    DateFormat df = new SimpleDateFormat(format);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_JUL: {
                try {
                    String format = "/";
                    for (String str : DATE_JUL) {
                        if (str.charAt(2) == expression.charAt(2)) {
                            format = str;
                        }
                    }
                    DateFormat df = new SimpleDateFormat(format);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_ISO: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_ISO);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_JIS: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_JIS);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_USA: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_USA);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_EUR: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_EUR);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_ISO0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_ISO0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_JIS0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_JIS0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_USA0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_USA0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_EUR0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_EUR0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_DMY0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_DMY0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_MDY0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_MDY0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
            case DATE_YMD0: {
                try {
                    DateFormat df = new SimpleDateFormat(DATE_YMD0);
                    date = df.parse(expression);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return date;
    }

    public static Date date(String expression) {
        return date(expression, DateEnum.DATE_ISO);
    }

    public static Date date(int expression) {
        String dateStr = String.valueOf(expression);
        if (dateStr.length() < 8) {
            throw new RuntimeException("date input error");
        }
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date date;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("date error");
        }
        return date;
    }

    /**
     * @param op1
     * @param op2
     * @param diffEnum
     * @return @
     */
    public static long diff(Date op1, Date op2, DateTimeDiffEnum diffEnum) {

        if (Objects.isNull(op1) || Objects.isNull(op2)) {
            throw new RuntimeException("The operation object is null");
        }
        long diff = 0L;
        LocalDate localDate1 = op1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = op2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        LocalTime lt2 = LocalDateTime.ofInstant(op2.toInstant(), ZoneId.systemDefault()).toLocalTime();
        LocalTime lt1 = LocalDateTime.ofInstant(op1.toInstant(), ZoneId.systemDefault()).toLocalTime();

        if (diffEnum.name().equals("DAYS") || diffEnum.name().equals("D")) {
            diff = ChronoUnit.DAYS.between(localDate2, localDate1);
            return diff;
        }

        if (diffEnum.name().equals("MONTHS") || diffEnum.name().equals("MN")) {
            diff = ChronoUnit.MONTHS.between(localDate2, localDate1);
            return diff;
        }

        if (diffEnum.name().equals("YEARS") || diffEnum.name().equals("Y")) {
            diff = ChronoUnit.YEARS.between(localDate2, localDate1);
            return diff;
        }

        if (diffEnum.name().equals("HOURS") || diffEnum.name().equals("H")) {
            diff = ChronoUnit.HOURS.between(lt2, lt1);
            return diff;
        }
        if (diffEnum.name().equals("MINUTES") || diffEnum.name().equals("M")) {
            diff = ChronoUnit.MINUTES.between(lt2, lt1);
            return diff;
        }
        if (diffEnum.name().equals("SECONDS") || diffEnum.name().equals("S")) {
            diff = ChronoUnit.SECONDS.between(lt2, lt1);
            return diff;
        }
        if (diffEnum.name().equals("MSECONDS") || diffEnum.name().equals("MS")) {

            diff = op1.getTime() - op2.getTime();
            int year1 = Integer.valueOf(String.format("%tY", op1));
            int month1 = Integer.valueOf(String.format("%tM", op1));
            int year2 = Integer.valueOf(String.format("%tY", op2));
            int month2 = Integer.valueOf(String.format("%tM", op2));
            int y = year1 - year2;
            int m = month1 - month2;
            if (y >= 32 && m >= 9) {
                if (String.valueOf(diff).length() > 15) {
                    throw new RuntimeException("error to longer");
                }
            }
            return diff;
        }
        return diff;
    }

    /**
     * @param date
     * @param seed
     * @return
     */
    public static Date addhours(Date date, int seed) {
        if (Objects.isNull(date)) {
            throw new RuntimeException("The operators objects is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        localDateTime = localDateTime.plusHours(seed);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * @param expression
     * @return
     * @throws ParseException
     */
    public static Date time(String expression, TimeEnum timeEnum) {
        if (Objects.isNull(expression) || expression.length() < 8) {
            throw new RuntimeException("expression is null or error");
        }
        Date date = null;
        try {
            switch (timeEnum) {
                case EUR: {
                    DateFormat df = new SimpleDateFormat(TIME_EUR);
                    date = df.parse(expression);
                    break;
                }
                case HMS: {
                    String format = ":";
                    for (String str : TIME_HMS) {
                        if (str.charAt(2) == expression.charAt(2) && expression.charAt(2) == expression.charAt(5)) {
                            format = str;
                        }
                    }
                    DateFormat df = new SimpleDateFormat(format);
                    date = df.parse(expression);
                    break;
                }
                case ISO: {
                    DateFormat df = new SimpleDateFormat(TIME_ISO);
                    date = df.parse(expression);
                    break;
                }
                case JIS: {
                    DateFormat df = new SimpleDateFormat(TIME_JIS);
                    date = df.parse(expression);
                    break;
                }
                case USA: {
                    DateFormat df = new SimpleDateFormat(TIME_USA, Locale.US);
                    date = df.parse(expression);
                    break;
                }
            }
        } catch (ParseException e) {
            throw new RuntimeException("time error");
        }

        return date;
    }

    /**
     * 返回数字变量或表达式的小数位数
     *
     * @param param
     * @return
     */
    public static BigDecimal decpos(String param) {
        try {

            BigDecimal bigDecimal = new BigDecimal(param);
            if (param.indexOf(".") != -1) {
                String str = String.valueOf(param.split("\\.")[1].length());
                return new BigDecimal(str);
            } else {
                return new BigDecimal("0");
            }

        } catch (NumberFormatException exception) {
            throw new NumberFormatException();
        }
    }

    /**
     * 返回操作数 dividend 除以 divisor 所得的商的整数部分
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static BigDecimal div(long divisor, long dividend) {
        BigDecimal bigDivisor = new BigDecimal(String.valueOf(divisor));
        BigDecimal bigDividend = new BigDecimal(String.valueOf(dividend));
        return bigDivisor.divide(bigDividend, 0, RoundingMode.HALF_DOWN);
    }

    public static BigDecimal div(int divisor, int dividend) {
        BigDecimal bigDivisor = new BigDecimal(String.valueOf(divisor));
        BigDecimal bigDividend = new BigDecimal(String.valueOf(dividend));
        return bigDivisor.divide(bigDividend, 0, RoundingMode.HALF_DOWN);
    }

    /**
     * 返回操作数 dividend 除以 divisor 所得的余数。两个操作数必须是无小数位的数值
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static BigDecimal rem(long divisor, long dividend) {

        BigDecimal bigDivisor = new BigDecimal(String.valueOf(divisor));
        BigDecimal bigDividend = new BigDecimal(String.valueOf(dividend));
        return bigDivisor.remainder(bigDividend);
    }

    /**
     * 返回操作数 dividend 除以 divisor 所得的余数。两个操作数必须是无小数位的数值
     *
     * @param divisor
     * @param dividend
     * @return
     */
    public static BigDecimal rem(int divisor, int dividend) {

        BigDecimal bigDivisor = new BigDecimal(String.valueOf(divisor));
        BigDecimal bigDividend = new BigDecimal(String.valueOf(dividend));
        return bigDivisor.remainder(bigDividend);
    }

    /**
     * 将数字表达式的值转换为整数。截断所有小数。
     *
     * @param param
     * @return
     */
    public static int toInt(BigDecimal param) {
        BigDecimal bigDecimal = param;
        return bigDecimal.setScale(0, RoundingMode.HALF_DOWN).intValue();
    }

    /**
     * @param param
     * @return
     */
    public static int toInt(String param) {
        BigDecimal intBig = new BigDecimal(param);
        return intBig.setScale(0, RoundingMode.HALF_DOWN).intValue();
    }

    /**
     * @param param
     * @return @
     */
    public static int inth(BigDecimal param) {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(param));
        return bigDecimal.setScale(0, RoundingMode.HALF_UP).intValue();
    }

    /**
     * @param param
     * @return
     */
    public static int inth(String param) {
        BigDecimal bigDecimal = new BigDecimal(param);

        return bigDecimal.setScale(0, RoundingMode.CEILING).intValue();
    }

    /**
     * 指定的数字数组表达式的所有元素的总和
     *
     * @param array
     * @return
     */
    public static BigDecimal xfoot(int[] array) {
        if (Objects.isNull(array)) {
            throw new RuntimeException("array is empty");
        }
        BigDecimal bigDecimal = new BigDecimal("0");
        for (int i : array) {
            BigDecimal tmp = new BigDecimal(i);
            bigDecimal = bigDecimal.add(tmp);
        }
        return bigDecimal;
    }

    /**
     * 求数组的和
     *
     * @param array
     * @return
     */
    public static BigDecimal xfoot(long[] array) {

        if (Objects.isNull(array)) {
            throw new RuntimeException("array is empty");
        }
        BigDecimal bigDecimal = new BigDecimal("0");
        for (long i : array) {
            bigDecimal = bigDecimal.add(new BigDecimal(i));
        }
        return bigDecimal;
    }

    public static BigDecimal xfoot(String[] array) {
        if (Objects.isNull(array)) {
            throw new RuntimeException("array is empty");
        }
        BigDecimal bigDecimal = new BigDecimal("0");
        for (String str : array) {
            if (Objects.nonNull(str)) {
                bigDecimal = bigDecimal.add(new BigDecimal(str));
            }
        }
        return bigDecimal;
    }

    public static BigDecimal xfoot(BigDecimal[] array) {
        if (Objects.isNull(array)) {
            throw new RuntimeException("array is empty");
        }
        BigDecimal bigDecimal = new BigDecimal("0");
        for (BigDecimal big : array) {
            if (Objects.nonNull(big)) {
                bigDecimal = bigDecimal.add(big);
            }
        }
        return bigDecimal;
    }

    /**
     * 根据 from、to 和 startpos 的值转换 string
     *
     * @param from
     * @param to
     * @param string
     * @param startpos
     * @return @
     */
    public static String xlate(String from, String to, String string, int startpos) {
        if (Objects.isNull(from) || Objects.isNull(to) || Objects.isNull(string) || from.length() == 0 || to.length() == 0 || string.length() == 0) {
            throw new RuntimeException("Operation object cannot be empty");
        }
        if (startpos > 0 && startpos > string.length()) {
            throw new RuntimeException("Index number error");
        }
        char[] fromChars = from.toCharArray();
        char[] toChars = to.toCharArray();

        char[] stringChars = string.toCharArray();

        Map<String, String> map = new HashMap<>();
        int length = Math.min(fromChars.length, toChars.length);
        for (int i = 0; i < length; i++) {
            map.put(String.valueOf(fromChars[i]), String.valueOf(toChars[i]));
        }
        if (startpos > 0) {
            startpos = startpos - 1;
        }
        for (int i = startpos; i < stringChars.length; i++) {
            if (map.containsKey(String.valueOf(stringChars[i]))) {
                stringChars[i] = map.get(String.valueOf(stringChars[i])).charAt(0);
            }
        }
        return String.valueOf(stringChars);
    }

    public static String xlate(String from, String to, String string) {
        return xlate(from, to, string, 1);
    }

    enum Editc3ParamEnum {
        ASTFILL, CURSYM, NONE;
    }

    // 分隔符，三位一分割
    static List<String> formatNumDeciaml2Bits = Arrays.asList("A", "B", "J", "K", "N", "O", "1", "2");
    // 不分割
    static List<String> numDeciaml2Bits = Arrays.asList("C", "D", "L", "M", "P", "Q", "3", "4");
    // 其他处理类型
    static List<String> otherOperType = Arrays.asList("5", "6", "7", "8", "9", "X", "Z");

    static List<String> deceditFormat = Arrays.asList(".", ",", "0,", "0.");

    public static String editc(String numeric, String editcode, String str) {

        char[] editcodeCharArr = editcode.toCharArray();
        if (Objects.isNull(numeric) || editcodeCharArr.length > 1) {
            throw new RuntimeException("Operation editcode can have only one character");
        }

        String result = "";
        if (String.valueOf(Editc3ParamEnum.ASTFILL).equals(str)) {
            result = editc(numeric, editcodeCharArr[0], Editc3ParamEnum.ASTFILL);
        } else if (String.valueOf(Editc3ParamEnum.CURSYM).equals(str)) {
            result = editc(numeric, editcodeCharArr[0], Editc3ParamEnum.CURSYM);
        } else {
            result = editc(numeric, editcodeCharArr[0], str.toCharArray()[0]);
        }

        return result;
    }

    public static String editc(String numeric, String editcode) {

        char[] editcodeCharArr = editcode.toCharArray();
        if (Objects.isNull(numeric) || editcodeCharArr.length > 1) {
            throw new RuntimeException("Operation editcode can have only one character");
        }
        String result = "";
        if (editcodeCharArr[0] == 'Y') {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                result = editc(sdf.parse(numeric), editcodeCharArr[0]);
            } catch (ParseException e) {
                // e.printStackTrace();
                throw new RuntimeException(numeric + " cannot be converted to date in the format yyyy-MM-dd");
            }
        } else {
            result = editc(numeric, editcodeCharArr[0], Editc3ParamEnum.NONE);
        }

        return result;
    }

    /**
     * @param numeric
     * @param editcode
     * @return @
     */
    public static String editc(Object numeric, char editcode) {
        if (Objects.isNull(numeric)) {
            throw new RuntimeException("Operation object cannot be empty");
        }
        return editc(numeric.toString(), editcode);
    }

    /**
     * @param numeric
     * @param editcode
     * @return @
     */
    public static String editc(String numeric, char editcode) {

        if (Objects.isNull(numeric) || numeric.length() == 0) {
            throw new RuntimeException("Operation object cannot be empty");
        }

        if (!formatNumDeciaml2Bits.contains(String.valueOf(editcode)) && !numDeciaml2Bits.contains(String.valueOf(editcode)) && !otherOperType.contains(String.valueOf(editcode))) {
            throw new RuntimeException("Not in range");
        }

        int numericLength = numeric.charAt(0) == '-' ? numeric.length() - 1 : numeric.length();
        int decLength = 0;
        String decLengthStr = "";
        if (numeric.contains(".")) {
            decLength = numeric.substring(numeric.indexOf(".") + 1).length();
            decLengthStr = ".";
            for (int i = 0; i < decLength; i++) {
                decLengthStr = decLengthStr + "0";
            }
        }

        String symbol = getOsLocaleSymbol();
        String result = "";
        if ('9' == editcode && (numeric.length() == 4 || numeric.length() == 3) && !numeric.contains(".")) {

            String day = numeric.substring(numeric.length() - 2);
            String month = numeric.substring(0, numeric.length() - 2);

            return month + "-" + day;
        } else {
            boolean negFlg = false;
            BigDecimal bigDecimal = new BigDecimal(numeric);
            if (bigDecimal.signum() == -1) {
                bigDecimal = bigDecimal.abs();
                negFlg = true;
            }
            String negStr = negFlg ? "CR" : "";
            DecimalFormat numberDecimalFormat = (DecimalFormat) DecimalFormat.getNumberInstance();

            if (formatNumDeciaml2Bits.contains(String.valueOf(editcode))) {
                numberDecimalFormat.applyPattern("#,###" + decLengthStr);
                result = numberDecimalFormat.format(bigDecimal) + negStr;
            }
            if (numDeciaml2Bits.contains(String.valueOf(editcode))) {
                numberDecimalFormat.applyPattern("#" + decLengthStr);
                result = numberDecimalFormat.format(bigDecimal) + negStr;
            }
            if ('5' == editcode) {
                numberDecimalFormat.applyPattern("#,###" + decLengthStr);
                result = numberDecimalFormat.format(bigDecimal) + (negFlg ? "CR" : "DR");
            }
            if ('6' == editcode) {
                // 数字前补空格和星号，空格、星号数量不确定
                numberDecimalFormat.applyPattern("#,###" + decLengthStr);
                result = numberDecimalFormat.format(bigDecimal) + negStr + "*";
            }
            if ('7' == editcode) {
                // 货币符放在 无效数字未统计
                numberDecimalFormat.applyPattern("#,###" + decLengthStr);
                result = symbol + numberDecimalFormat.format(bigDecimal) + negStr;
            }
            // 货币符号在第一个有效数字前
            if ('8' == editcode) {
                numberDecimalFormat.applyPattern("#,###" + decLengthStr);
                result = symbol + numberDecimalFormat.format(bigDecimal) + negStr;
            }
            // 数字前补零
            if ('X' == editcode) {
                result = numeric.contains(".") ? numeric.replace(".", "") : numeric + negStr;
            }
            // 仅数字输出
            if ('Z' == editcode) {
                numberDecimalFormat.applyPattern("#" + decLengthStr);
                result = numberDecimalFormat.format(bigDecimal) + negStr;
                while (result.length() < numericLength) {
                    result = " " + result;
                }
                result = result.replace(".", "");
            }
            return result;
        }
    }

    public static String editc(Date numeric, char editcode) {
        String result = "";
        if (editcode == 'Y') {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            result = dateFormat.format(numeric);
        }
        return result;
    }

    public static String editc(String numeric, char editcode, String decedit) {

        if (!deceditFormat.contains(decedit)) {
            throw new RuntimeException("Not in range");
        }

        if (!formatNumDeciaml2Bits.contains(String.valueOf(editcode)) && !numDeciaml2Bits.contains(String.valueOf(editcode)) && !otherOperType.contains(String.valueOf(editcode))) {
            throw new RuntimeException("Not in range");
        }
        int deciamlLength = numeric.contains(".") ? numeric.substring(numeric.indexOf(".")).length() - 1 : 0;
        char[] deciamlChars = new char[deciamlLength];
        Arrays.fill(deciamlChars, '0');

        BigDecimal bigDecimal = new BigDecimal(numeric);

        DecimalFormat numberDecimalFormat = (DecimalFormat) DecimalFormat.getNumberInstance();
        String spillFormat = "#,###";
        String spill0Format = "#,##0";
        if (formatNumDeciaml2Bits.contains(String.valueOf(editcode))) {

            if (editcode == '1' || editcode == '2') {
                String result = "";
                if (bigDecimal.compareTo(new BigDecimal("0")) != 0) {
                    bigDecimal = bigDecimal.abs();
                }

                if (deceditFormat.contains(decedit) && (".".equals(decedit) || ",".equals(decedit))) {
                    if (deciamlLength > 0) {
                        numberDecimalFormat.applyPattern(spillFormat + "." + String.valueOf(deciamlChars));
                    } else {
                        numberDecimalFormat.applyPattern(spillFormat);
                    }
                    result = numberDecimalFormat.format(bigDecimal);
                }
                // decedit 为 0. 或者 0,
                if (deceditFormat.contains(decedit) && ("0.".equals(decedit) || "0,".equals(decedit))) {
                    if (deciamlLength > 0) {
                        numberDecimalFormat.applyPattern(spill0Format + "." + String.valueOf(deciamlChars));
                    } else {
                        numberDecimalFormat.applyPattern(spill0Format);
                    }
                    result = numberDecimalFormat.format(bigDecimal);
                }
                // decedit中包含逗号时
                if (decedit.contains(",")) {
                    result = result.replace(".", ",");
                }
                char[] tmpResChars = result.toCharArray();
                for (int i = 0; i < tmpResChars.length; i++) {
                    if (tmpResChars[i] == '0') {
                        tmpResChars[i] = OTHER_SPACE;
                    } else {
                        break;
                    }
                }

                if (("0.".equals(decedit) || "0,".equals(decedit) && bigDecimal.compareTo(new BigDecimal("1.0")) <= 0)) {
                    for (int i = 0; i < tmpResChars.length; i++) {
                        if (tmpResChars[i] == '.' || tmpResChars[i] == ',') {
                            tmpResChars[i - 1] = '0';
                            break;
                        }
                    }
                }

                return String.valueOf(tmpResChars);
            }

            if (editcode == 'A' || editcode == 'B') {

            }
        }

        return null;
    }

    /**
     * ASTFILL: 向右移位，左边空格不动 CURSYM:
     *
     * @param numeric
     * @param editcode
     * @param editcEnum
     * @return @
     */
    public static String editc(String numeric, char editcode, Editc3ParamEnum editcEnum) {

        if (Objects.isNull(numeric) || numeric.length() == 0) {
            throw new RuntimeException("Operation object cannot be empty");
        }
        char[] numericChars = numeric.toCharArray();

        // 无效0
        int unvaildZero = 0;
        for (int i = 0; i < numericChars.length; i++) {
            if (numericChars[i] == '0') {
                unvaildZero = unvaildZero + 1;
            } else {
                break;
            }
        }
        String unvildStr = "";
        if (unvaildZero > 0) {
            char[] unvaild0Chars = new char[unvaildZero];
            Arrays.fill(unvaild0Chars, '0');
            unvildStr = String.valueOf(unvaild0Chars);
        }
        String tmpRes = editc(numeric, editcode);
        if (editcEnum.name().equals("ASTFILL")) {
            tmpRes = unvildStr + tmpRes;
            char[] tmpResChars = tmpRes.toCharArray();
            for (int i = 0; i < tmpResChars.length; i++) {
                if (tmpResChars[i] == '0') {
                    tmpResChars[i] = '*';
                } else {
                    break;
                }
            }
            return String.valueOf(tmpResChars);
        }
        if (editcEnum.name().equals("CURSYM")) {
            String symbol = getOsLocaleSymbol();
            return symbol + tmpRes;
        }

        return tmpRes;
    }

    public static String editc(String numeric, char editcode, char thridParam) {
        String tmpRes = editc(numeric, editcode);
        return String.valueOf(thridParam) + tmpRes;
    }

    private static String getOsLocaleSymbol() {
        String symbol = "$";
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        String language = locale.getLanguage();
        if (country.equals("JP") && !"ja".equals(language)) {
            language = "ja";
        }
        locale = new Locale(language, country);
        Currency currency = Currency.getInstance(locale);
        symbol = currency.getSymbol(locale);
        return symbol;
    }

    /**
     * @param numeric
     * @param editword
     * @return @
     */

    public static String editw(int numeric, String editword) {
        if (editword.contains(",")) {
            String[] keyArr = editword.split(",");
            Integer newNumeric = numeric;
            for (String key : keyArr) {
                String editwNumeric = editw(newNumeric, key);
                newNumeric = Integer.valueOf(editwNumeric);
            }
        }
        String value = String.valueOf(numeric);
        List<String> keyRexList = StringUtil.getStringRex(editword, ".*(?=\\()");
        List<String> valueRexList = StringUtil.getStringRex(editword, "(?<=\\()(.*)(?=\\))");
        String keyword = keyRexList.size() > 0 ? keyRexList.get(0) : editword;
        String valueWord = valueRexList.size() > 0 ? valueRexList.get(0) : editword;
        switch (keyword) {
            case "EDTCDE":
                switch (valueWord) {
                    case "1":
                    case "2":
                    case "A":
                    case "B":
                    case "J":
                    case "K":
                    case "N":
                    case "O":
                        value = new DecimalFormat("#,###.##").format(numeric);
                        break;

                    case "3":
                    case "4":
                    case "C":
                    case "D":
                    case "L":
                    case "M":
                    case "P":
                    case "Q":
                        value = new DecimalFormat("#.##").format(numeric);
                        break;

                    case "X":
                    case "Z":
                    default:
                        value = new DecimalFormat("#").format(numeric);
                        break;
                }
                break;
            case "EDTWRD":


                break;
        }
        return value;
    }

    public static String editw(int[] numericArr, String editword) {
        // TODO
        return null;
    }

    public static String editw(long numeric, String editword) {
        // TODO
        return null;
    }

    public static String editw(long[] numericArr, String editword) {
        // TODO
        return null;
    }

    public static String editw(BigDecimal numeric, String editword) {
        // TODO
        return null;
    }

    public static String editw(BigDecimal[] numericArr, String editword) {
        // TODO
        return null;
    }

//	public static String editw(String numeric, String editword) {
//
//		if (Objects.isNull(numeric) || Objects.isNull(editword) || numeric.trim().length() == 0
//				|| editword.length() == 0) {
//			throw new RuntimeException("Operation object cannot be empty");
//		}
//		// 0和*为消字符，谁在前谁生效
//		int starIndex = editword.indexOf("*");
//		int zeroIndex = editword.indexOf("0");
//
//		int numericLength = numeric.length();
//		if ('-' == (numeric.charAt(0))) {
//			numericLength = numericLength - 1;
//		}
//
//		int enableSpace = 0;
//		for (char c : editword.toCharArray()) {
//			if (c == OTHER_SPACE) {
//				enableSpace = enableSpace + 1;
//			}
//		}
//		if (editword.contains("*") && editword.contains("0")) {
//			if (editword.contains("*") && starIndex < zeroIndex) {
//				enableSpace = enableSpace + 1;
//			}
//			if (editword.contains("0") && zeroIndex < starIndex) {
//				enableSpace = enableSpace + 1;
//			}
//		}
//		if (editword.contains("*") && !editword.contains("0")) {
//			enableSpace = enableSpace + 1;
//		}
//		if (!editword.contains("*") && editword.contains("0")) {
//			enableSpace = enableSpace + 1;
//		}
//
//		char currencySymbol = getCurrencySymbolType(editword);
//		int currencySymbolInex = editword.indexOf(currencySymbol);
//
//		if (numericLength > enableSpace) {
//			throw new RuntimeException("numeric too longer");
//		}
//		String last1Editword = editword.substring(editword.length() - 1);
//
//		long numLong = BigInteger.valueOf(Long.valueOf(numeric)).longValue();
//		if (numLong >= 0 && "-".equals(last1Editword)) {
//			editword = editword.substring(0, editword.length() - 1);
//
//		}
//		if (numLong < 0) {
//			numeric = numeric.substring(1, numeric.length());
//		}
//
//		// *在前0在后
//		if (starIndex < zeroIndex && editword.contains("*") && editword.contains("0")) {
//
//			char[] editwordChars = clearChar(editword.toCharArray(), starIndex);
//			editwordChars = reverseFill(editwordChars, numeric.toCharArray());
//			for (int i = 0; i <= starIndex; i++) {
//				if (editwordChars[i] < '1' || editwordChars[i] > '9') {
//					editwordChars[i] = '*';
//				} else {
//					break;
//				}
//			}
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				currencySymbol = getCurrencySymbolType(editword);
//				int currencySymbolIndex = editword.indexOf(currencySymbol);
//				if (currencySymbolIndex < starIndex) {
//					if (currencySymbolIndex == 0) {
//						editwordChars[0] = currencySymbol;
//					} else {
//						for (int i = 1; i < editwordChars.length; i++) {
//							if (editwordChars[i] != '*') {
//								editwordChars[i - 1] = currencySymbol;
//								break;
//							}
//						}
//					}
//				}
//			}
//			return String.valueOf(editwordChars).replace("&", " ");
//		}
//
//		// 0在*之前
//		if (zeroIndex < starIndex && editword.contains("*") && editword.contains("0")) {
//			char[] editwordChars = editword.toCharArray();
//			editwordChars = clearChar(editwordChars, zeroIndex);
//			int currencySymbolIndex = -1;
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				currencySymbolIndex = editword.indexOf(currencySymbol);
//				if (currencySymbolIndex > 0 && currencySymbolIndex < zeroIndex) {
//					editwordChars[currencySymbolIndex] = OTHER_SPACE;
//				}
//			}
//
//			editwordChars = reverseFill(editwordChars, numeric.toCharArray());
//			for (int i = 0; i <= zeroIndex; i++) {
//				if (editwordChars[i] < '1' || editwordChars[i] > '9') {
//					editwordChars[i] = OTHER_SPACE;
//				} else {
//					break;
//				}
//			}
//			if (currencySymbolIndex < zeroIndex && currencySymbolIndex >= 0) {
//				int fillZeroCount = 0;
//				if (editword.contains(".")) {
//					String headEditword = editword.substring(0, editword.indexOf("."));
//					char[] headEditwordChars = headEditword.toCharArray();
//					for (int i = 0; i < headEditwordChars.length; i++) {
//						if (headEditwordChars[i] == '0') {
//							fillZeroCount = fillZeroCount + 1;
//						}
//					}
//					if (fillZeroCount > 0) {
//						String resHeadEditword = String.valueOf(editwordChars).substring(0, editword.indexOf("."));
//						String resTailEditword = String.valueOf(editwordChars).substring(editword.indexOf("."));
//						try {
//							long l = Long.valueOf(resHeadEditword.trim());
//							if (Long.valueOf(resHeadEditword.trim().replace(",", "")).longValue() == 0L) {
//								char[] resHeadEditwordChars = resHeadEditword.toCharArray();
//								for (int i = resHeadEditwordChars.length - (1 + fillZeroCount); i >= 0; i--) {
//									resHeadEditwordChars[i] = OTHER_SPACE;
//								}
//								editwordChars = (String.valueOf(resHeadEditwordChars) + resTailEditword).toCharArray();
//							}
//						} catch (NumberFormatException e) {
////                            e.printStackTrace();
//						}
//					}
//				}
//				if (currencySymbolIndex == 0) {
//					editwordChars[0] = currencySymbol;
//				} else {
//					for (int i = 1; i < editwordChars.length; i++) {
//						if (editwordChars[i] != OTHER_SPACE) {
//							editwordChars[i - 1] = currencySymbol;
//							break;
//						}
//					}
//				}
//			}
//			return String.valueOf(editwordChars).replace("&", " ");
//		}
//
//		// 包含星不含0， 含点
//		if (editword.contains("*") && !editword.contains("0")) {
//			if (editword.length() == numeric.length() && editword.length() == 1) {
//				return numeric;
//			}
//			char[] editwordChars = editword.toCharArray();
//			editwordChars = clearChar(editwordChars, starIndex);
//			editwordChars = reverseFill(editwordChars, numeric.toCharArray());
//			if (starIndex == 0) {
//				editwordChars[0] = '*';
//			} else {
//				for (int i = 0; i < starIndex; i++) {
//					if (editwordChars[i] < '1' || editwordChars[i] > '9') {
//						editwordChars[i] = '*';
//					} else {
//						break;
//					}
//				}
//			}
//			if (editword.contains(".")) {
//				int pointIndex = editword.indexOf(".");
//				String res = String.valueOf(editwordChars);
//				String subRes = "";
//				if (pointIndex > starIndex) {
//					subRes = res.substring(starIndex, pointIndex);
//					try {
//						long l = Long.valueOf(subRes);
//						char[] fillChars = new char[subRes.length() + 1];
//						if (l == 0) {
//							Arrays.fill(fillChars, '*');
//							System.arraycopy(fillChars, 0, editwordChars, starIndex, fillChars.length);
//						}
//					} catch (NumberFormatException e) {
//
//					}
//				}
//			}
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				int currencySymbolIndex = editword.indexOf(currencySymbol);
//				if (currencySymbolIndex == 0) {
//					editwordChars[0] = currencySymbol;
//				} else {
//					for (int i = 1; i < editwordChars.length; i++) {
//						if (editwordChars[i] != '*') {
//							editwordChars[i - 1] = currencySymbol;
//							break;
//						}
//					}
//				}
//			}
//			return String.valueOf(editwordChars).replace("&", " ");
//		}
//
//		// 只包含0，不包含*
//		if (editword.contains("0") && !editword.contains("*")) {
//			char[] editwordChars = editword.toCharArray();
//			int currencySymbolIndex = -1;
//			editwordChars = clearChar(editwordChars, zeroIndex);
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				currencySymbolIndex = editword.indexOf(currencySymbol);
//				if (currencySymbolIndex > 0 && currencySymbolIndex < zeroIndex) {
//					editwordChars[currencySymbolIndex] = OTHER_SPACE;
//				}
//			}
//			editwordChars = reverseFill(editwordChars, numeric.toCharArray());
//			if (zeroIndex == 0 && editwordChars[0] == '0') {
//				editwordChars[0] = OTHER_SPACE;
//			} else {
//				for (int i = 0; i < zeroIndex; i++) {
//					if (editwordChars[i] < '1' || editwordChars[i] > '9') {
//						editwordChars[i] = OTHER_SPACE;
//					} else {
//						break;
//					}
//				}
//			}
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				if (editword.contains(".")) {
//					int fillZeroCount = 0;
//					String headEditword = editword.substring(0, editword.indexOf("."));
//					char[] headEditwordChars = headEditword.toCharArray();
//					for (int i = 0; i < headEditwordChars.length; i++) {
//						if (headEditwordChars[i] == '0') {
//							fillZeroCount = fillZeroCount + 1;
//						}
//					}
//					if (fillZeroCount > 0) {
//						String resHeadEditword = String.valueOf(editwordChars).substring(0, editword.indexOf("."));
//						String resTailEditword = String.valueOf(editwordChars).substring(editword.indexOf("."));
//						if (Long.valueOf(resHeadEditword.trim().replace(",", "")).longValue() == 0L) {
//							char[] resHeadEditwordChars = resHeadEditword.toCharArray();
//							for (int i = resHeadEditwordChars.length - (1 + fillZeroCount); i >= 0; i--) {
//								resHeadEditwordChars[i] = OTHER_SPACE;
//							}
//							editwordChars = (String.valueOf(resHeadEditwordChars) + resTailEditword).toCharArray();
//						}
//					}
//
//				}
//				if (currencySymbolIndex == 0) {
//					editwordChars[0] = currencySymbol;
//				} else {
//					for (int i = 1; i < editwordChars.length; i++) {
//						if (editwordChars[i] != OTHER_SPACE && editwordChars[i] != '0') {
//							editwordChars[i - 1] = currencySymbol;
//							break;
//						}
//					}
//				}
//			}
//			return String.valueOf(editwordChars).replace("&", " ");
//		}
//
//		// 包含点
//		if (editword.contains(".") && !editword.contains("*") && !editword.contains("0")) {
//			char[] editwordChars = editword.toCharArray();
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				editwordChars[currencySymbolInex] = OTHER_SPACE;
//			}
//			editwordChars = reverseFill(editwordChars, numeric.toCharArray());
//			int pointIndex = editword.indexOf(".");
//			for (int i = 0; i < pointIndex; i++) {
//				if (editwordChars[i] == '0' || editwordChars[i] == OTHER_SPACE) {
//					editwordChars[i] = OTHER_SPACE;
//				} else {
//					break;
//				}
//			}
//			String res = String.valueOf(editwordChars);
//			String subRes = res.substring(0, pointIndex);
//			try {
//				long l = Long.valueOf(subRes);
//				char[] fillChars = new char[subRes.length() + 1];
//				if (l == 0) {
//					Arrays.fill(fillChars, OTHER_SPACE);
//					System.arraycopy(fillChars, 0, editwordChars, 0, fillChars.length);
//					return String.valueOf(editwordChars).replace("&", " ");
//				}
//			} catch (NumberFormatException e) {
//
//			}
//			if (getCurrencySymbolType(editword) != OTHER_SPACE) {
//				if (currencySymbolInex == 0 && numeric.length() == editword.length()) {
//					return (String.valueOf(getCurrencySymbolType(editword)) + String.valueOf(editwordChars));
//				}
//				if (currencySymbolInex == 0 && numeric.length() < editword.length()) {
//					editwordChars[0] = getCurrencySymbolType(editword);
//				} else {
//					for (int i = 0; i < editwordChars.length; i++) {
//						if (editwordChars[i] != OTHER_SPACE && editwordChars[i] != '0') {
//							editwordChars[i - 1] = getCurrencySymbolType(editword);
//							break;
//						}
//					}
//				}
//			}
//			return String.valueOf(editwordChars).replace("&", " ");
//		}
//
//		// 只含货币符
//		if (getCurrencySymbolType(editword) != OTHER_SPACE && !editword.contains("*") && !editword.contains("0")
//				&& !editword.contains(".")) {
//			char[] editwordChars = editword.toCharArray();
//			editwordChars[currencySymbolInex] = OTHER_SPACE;
//			reverseFill(editwordChars, numeric.toCharArray());
//
//			if (currencySymbolInex == 0) {
//				editwordChars[0] = currencySymbol;
//			} else {
//				for (int i = currencySymbolInex; i > 0; i--) {
//					if (i > 0) {
//						if ((editwordChars[i] == OTHER_SPACE || editwordChars[i] == ',')
//								&& editwordChars[i - 1] == OTHER_SPACE) {
//							editwordChars[i] = currencySymbol;
//							break;
//						}
//					}
//				}
//			}
//			return String.valueOf(editwordChars).replace("&", " ");
//		}
//		// 只含0
//		if (editword.contains("0") && !editword.contains(".") && !editword.contains("*")
//				&& !editword.contains(String.valueOf(getCurrencySymbolType(editword)))) {
//
//			char[] editwordChars = editword.toCharArray();
//			editwordChars[zeroIndex] = OTHER_SPACE;
//			editwordChars = reverseFill(editwordChars, numeric.toCharArray());
//
//			if (zeroIndex == 0) {
//				editwordChars[0] = OTHER_SPACE;
//			} else {
//				for (int i = 0; i < zeroIndex; i++) {
//					if (editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE) {
//						editwordChars[i] = OTHER_SPACE;
//					}
//				}
//			}
//
//			return String.valueOf(editwordChars).replace("&", " ");
//
//		}
//		char[] editwordChars = reverseFill(editword.toCharArray(), numeric.toCharArray());
//		for (int i = 0; i < editwordChars.length; i++) {
//			if (editwordChars[i] == '0') {
//				editwordChars[i] = OTHER_SPACE;
//			} else {
//				break;
//			}
//		}
//		return String.valueOf(editwordChars).replace("&", " ");
////        return editword;
//	}

    /**
     * 消字符操作
     *
     * @param editwordChars
     * @param length
     * @return
     */
    private static char[] clearChar(char[] editwordChars, int length) {
        for (int i = 0; i <= length; i++) {
            if (editwordChars[i] != ',' && editwordChars[i] != OTHER_SPACE && editwordChars[i] != '.') {
                editwordChars[i] = OTHER_SPACE;
            }
        }
        return editwordChars;
    }

    /**
     * 获取货币符号
     *
     * @param editword
     * @return
     */
    private static char getCurrencySymbolType(String editword) {
        char currencySymbol = OTHER_SPACE;
        if (editword.contains("$") || editword.contains("￥")) {
            if (editword.contains("￥")) {
                currencySymbol = '￥';
            } else {
                currencySymbol = '$';
            }
        }
        return currencySymbol;
    }

    /**
     * 处理包含零或者星号
     *
     * @param editword
     * @param numeric
     * @return
     */
    private static String pointAndZeroType(String editword, String numeric) {

        int first0Index = editword.indexOf("0");
        int firstPiontIndex = editword.indexOf(".");
        String result = null;
        // editword 中 0在小数点之前
        if (first0Index < firstPiontIndex && first0Index >= 0 && firstPiontIndex >= 0) {
            char[] headEditwordChars = editword.substring(0, firstPiontIndex).toCharArray();
            char[] tailEditwordChars = editword.substring(firstPiontIndex).toCharArray();
            int zeroCount = 0;
            for (int i = 0; i < headEditwordChars.length; i++) {
                if (headEditwordChars[i] == '0') {
                    zeroCount = zeroCount + 1;
                }
                if (headEditwordChars[i] != ',') {
                    headEditwordChars[i] = OTHER_SPACE;
                }
            }
            String newEditword = String.valueOf(headEditwordChars) + String.valueOf(tailEditwordChars);
            char[] newEditwordChars = reverseFill(newEditword.toCharArray(), numeric.toCharArray());
            for (int i = 0; i < newEditwordChars.length; i++) {
                if (newEditwordChars[i] == '0' || newEditwordChars[i] == ',' || newEditwordChars[i] == OTHER_SPACE) {
                    newEditwordChars[i] = OTHER_SPACE;
                } else {
                    break;
                }
            }
            result = String.valueOf(newEditwordChars);
            // editword中小数点出现零后边，numeric转换后小数点左边为空格时
            String checkZero = result.substring(0, firstPiontIndex).replace("0", "").replace(",", "").trim();
            if (checkZero.length() < result.substring(0, firstPiontIndex).length()) {
                for (int i = firstPiontIndex - 1; i > firstPiontIndex - 1 - zeroCount; i--) {
                    if (newEditwordChars[i] == OTHER_SPACE || newEditwordChars[i] == ',') {
                        newEditwordChars[i] = '0';
                    }
                }
                result = String.valueOf(newEditwordChars);
            }
            return result;
        }

        // editword 中 小数点在0之前
        if (first0Index > firstPiontIndex && first0Index >= 0 && firstPiontIndex >= 0) {

            char[] headEditword = editword.substring(0, firstPiontIndex + 1).toCharArray();
            char[] tailEditword = editword.substring(firstPiontIndex + 1).toCharArray();
            for (int i = 0; i < headEditword.length; i++) {
                if (headEditword[i] != OTHER_SPACE) {
                    headEditword[i] = OTHER_SPACE;
                }
            }
            String newEditword = String.valueOf(headEditword) + String.valueOf(tailEditword);
            char[] newEditwordChars = reverseFill(newEditword.toCharArray(), numeric.toCharArray());
            for (int i = 0; i < firstPiontIndex + 1; i++) {
                if (newEditwordChars[i] == '0' || newEditwordChars[i] == ',' || newEditwordChars[i] == OTHER_SPACE) {
                    newEditwordChars[i] = OTHER_SPACE;
                } else {
                    break;
                }
            }
            return String.valueOf(newEditwordChars);
        }
        // 只包含小数点
        if (first0Index < 0 && firstPiontIndex >= 0) {
            char[] editwordChars = reverseFill(editword.toCharArray(), numeric.toCharArray());
            for (int i = 0; i < editwordChars.length; i++) {
                if (editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE || editwordChars[i] == '.') {
                    editwordChars[i] = OTHER_SPACE;
                } else {
                    break;
                }
            }
            return String.valueOf(editwordChars);
        }
        // editword中只含0
        if (first0Index >= 0 && firstPiontIndex < 0) {
            String newEditword = editword.replace("0", " ");
            char[] editwordChars = reverseFill(newEditword.toCharArray(), numeric.toCharArray());

            for (int i = 0; i < first0Index + 1; i++) {
                if (editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE) {
                    editwordChars[i] = OTHER_SPACE;
                } else {
                    break;
                }
            }
            return String.valueOf(editwordChars);
        }
        return result;
    }

    /**
     * 星号为主导符号
     *
     * @param editword
     * @param numeric
     * @return
     */
    private static String asteriskType(String editword, String numeric) {

        if (editword.contains("*") && !editword.contains("&") && !editword.contains("$") && !editword.contains("￥")) {
            if (editword.length() == 1) {
                return numeric;
            }
            int start1Index = editword.indexOf('*');
            int zeroIndex = editword.indexOf('0');
            int pointIndex = editword.indexOf('.');

            char[] editwordChars = editword.toCharArray();

            // *在前0在后
            if (start1Index < zeroIndex && editword.contains("0")) {
                editwordChars[start1Index] = OTHER_SPACE;
                editwordChars = reverseFill(editwordChars, numeric.toCharArray());
                for (int i = 0; i <= start1Index; i++) {
                    if (editwordChars[i] == OTHER_SPACE || editwordChars[i] == '0' || editwordChars[i] == ',') {
                        editwordChars[i] = '*';
                    } else {
                        break;
                    }
                }
            }
            // 0在前*在后
            if (zeroIndex < start1Index && editword.contains("0")) {
                editwordChars[zeroIndex] = OTHER_SPACE;
                editwordChars = reverseFill(editwordChars, numeric.toCharArray());
                for (int i = 0; i <= start1Index; i++) {
                    if (editwordChars[i] == OTHER_SPACE || editwordChars[i] == '0' || editwordChars[i] == ',') {
                        editwordChars[i] = OTHER_SPACE;
                    } else {
                        break;
                    }
                }
            }

            // 只包含*，不包含0
            if (editword.contains("*") && !editword.contains("0")) {
                editwordChars[start1Index] = OTHER_SPACE;
                editwordChars = reverseFill(editwordChars, numeric.toCharArray());
                for (int i = 0; i <= start1Index; i++) {
                    if (editwordChars[i] == OTHER_SPACE || editwordChars[i] == '0' || editwordChars[i] == ',') {
                        editwordChars[i] = '*';
                    } else {
                        break;
                    }
                }
            }

            if (start1Index == 0 && editword.length() > 1) {
                if (editwordChars[start1Index] == OTHER_SPACE) {
                    editwordChars[0] = '*';
                }
                return String.valueOf(editwordChars);
            }

            return String.valueOf(editwordChars);
        }

        // *为主导包含$
        if (editword.contains("*") && (editword.contains("$") || editword.contains("￥"))) {
            int startIndex = editword.indexOf("*");
            int startLast = editword.lastIndexOf("*");
            int moneySybolIndex = -1;

            if (editword.contains("$")) {
                moneySybolIndex = editword.indexOf("$");
            }
            if (editword.contains("￥")) {
                moneySybolIndex = editword.indexOf("￥");
            }
            char[] editwordChars = editword.toCharArray();
            editwordChars[startIndex] = OTHER_SPACE;
            editwordChars = reverseFill(editwordChars, numeric.toCharArray());
            if (startIndex == startLast) {
                for (int i = 0; i < editwordChars.length; i++) {
                    if ((editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE) && i != (startIndex + 1)) {
                        editwordChars[i] = '*';
                    } else {
                        break;
                    }
                }
            } else {
//                editwordChars[startIndex] = OTHER_SPACE;
//                editwordChars = reverseFill(editwordChars, numeric.toCharArray());
                for (int i = 1; i < editwordChars.length; i++) {
                    if (editwordChars[i] == '0' || editwordChars[i] == ',') {
                        editwordChars[i] = OTHER_SPACE;
                    } else {
                        break;
                    }
                }
            }
            if (editword.contains("*")) {
                int index = 0;
                if (moneySybolIndex == 0) {
                    index = 1;
                }
                for (int i = index; i < editwordChars.length; i++) {
                    if (editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE) {
                        editwordChars[i] = '*';
                    } else {
                        break;
                    }
                }
            }
            return String.valueOf(editwordChars).replace("&", " ");
        }
        return null;
    }

    /**
     * &类型为主导
     *
     * @param editword
     * @param numeric
     * @return
     */
    private static String andType(String editword, String numeric) {
        if (!editword.contains("*") && editword.contains("&")) {
            char[] editwordChars = reverseFill(editword.toCharArray(), numeric.toCharArray());

            if (editwordChars[0] == '0') {
                editwordChars[0] = OTHER_SPACE;
            }
            String result = String.valueOf(editwordChars).replaceAll("&", String.valueOf(OTHER_SPACE));
            return result;
        }
        return null;
    }

    /**
     * 货币类型
     *
     * @param editword
     * @param numeric
     * @return
     */
    private static String currencySymbol(String editword, String numeric) {
        int currencySymbolIndex = -1;
        char currencySymbol = '$';
        if (editword.contains("$") && !editword.contains("￥")) {
            currencySymbolIndex = editword.indexOf("$");
            currencySymbol = editword.charAt(currencySymbolIndex);
        }
        if (!editword.contains("$") && editword.contains("￥")) {
            currencySymbolIndex = editword.indexOf("￥");
            currencySymbol = editword.charAt(currencySymbolIndex);
        }

        if (editword.contains("$") || editword.contains("￥")) {
            String newEditword = editword;

            if (editword.contains("*")) {
                int firstStart = editword.indexOf('*');
                int lastStart = editword.lastIndexOf('*');
                if (firstStart == lastStart) {
                    newEditword = newEditword.replace("*", " ").replace("0", " ");
                } else {
                    String headStr = newEditword.substring(0, firstStart) + " ";
                    String tailStr = newEditword.substring(firstStart + 1);
                    newEditword = headStr + tailStr;
                }
            }

            if (currencySymbolIndex != 0) {
                newEditword = newEditword.replace("$", " ").replace("￥", " ").replace("0", " ");
            } else {
                String kill1st = newEditword.substring(1);
                String firstStr = newEditword.substring(0, 1);
                newEditword = firstStr + kill1st.replace("0", " ");
            }
            Long numL = Long.valueOf(numeric);
            String newNumeric = numL.toString();
            if (numL.equals(Long.valueOf("0"))) {
                newNumeric = numeric;
            }
            char[] editwordChars = reverseFill(newEditword.toCharArray(), newNumeric.toCharArray());
            String res = String.valueOf(editwordChars);
            // $出现在中间
            if (currencySymbolIndex != 0) {
                for (int i = 0; i < editwordChars.length; i++) {
                    if (editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE) {
                        editwordChars[i] = OTHER_SPACE;
                    } else {
                        break;
                    }
                }

                for (int i = editwordChars.length - 1; i >= 0; i--) {
                    if (editwordChars[i] == OTHER_SPACE) {
                        editwordChars[i] = currencySymbol;
                        break;
                    }
                }
                return String.valueOf(editwordChars);
            }
            // $出现在第一位
            if (currencySymbolIndex == 0) {
                if (editword.contains("*")) {
                    for (int i = 1; i < editwordChars.length; i++) {
                        if (editwordChars[i] == '0' || editwordChars[i] == ',' || editwordChars[i] == OTHER_SPACE) {
                            editwordChars[i] = '*';
                        } else {
                            break;
                        }
                    }
                } else { // $出现在中间位置
                    for (int i = 1; i < editwordChars.length; i++) {
                        if (editwordChars[i] == ',' || editwordChars[i] == '0' || editwordChars[i] == OTHER_SPACE) {
                            editwordChars[i] = OTHER_SPACE;
                        } else {
                            break;
                        }
                    }

                    int zeroCount = 0;
                    char[] headEditwordChars = null;
                    String tailStr = "";
                    if (editword.contains(".")) {
                        int zeroLastIndex = editword.lastIndexOf('.');
                        headEditwordChars = editword.substring(0, zeroLastIndex).toCharArray();

                        for (int i = 0; i < headEditwordChars.length; i++) {
                            if (headEditwordChars[i] == '0') {
                                zeroCount = zeroCount + 1;
                            }
                        }
                        tailStr = String.valueOf(editwordChars).substring(zeroLastIndex + 1).replace("0", "");
                    }
                    int lastPointIndex = editword.lastIndexOf('.');
                    if (tailStr.length() == 0) {
                        for (int i = lastPointIndex - 1; i >= lastPointIndex - zeroCount; i--) {
                            editwordChars[i] = '0';
                        }
                        return String.valueOf(editwordChars);
                    }
                }
                return String.valueOf(editwordChars);
            }

        }
        return null;
    }

    /**
     * 反向填充
     *
     * @param editwordChars
     * @param numericChars
     * @return
     */
    private static char[] reverseFill(char[] editwordChars, char[] numericChars) {

        for (int i = editwordChars.length - 1; i >= 0; i--) {
            for (int j = numericChars.length - 1; j >= 0; j--) {
                if (editwordChars[i] == OTHER_SPACE || editwordChars[i] == JIS_SPACE) {
                    editwordChars[i] = numericChars[j];
                    numericChars[j] = OTHER_SPACE;
                    continue;
                }
            }
        }
        return editwordChars;
    }

    /**
     * 去除重复字符
     *
     * @param source
     * @return
     */
    public static String deduplication(String source) {
        StringBuilder arfa = new StringBuilder("");
        // 遍历字符串str
        for (int i = 0; i < source.length(); i++) {
            // 获取字符串中下标为i的字符（char类型）
            char ch = source.charAt(i);
            // 如果字符串没有重复，即：每个字符的下标都会等价于该字符第一次出现时的下标
            // 判断字符串第一次出现ch字符时的下标是否等于i
            if (source.indexOf(ch) == i) {
                // 将ch字符转化为String类型，并添加到字符串arfa的末尾
                arfa.append(ch);
            }
        }
        char[] chars = arfa.toString().toUpperCase().toCharArray();
//        Arrays.sort(chars);
        return String.valueOf(chars);
    }
}
