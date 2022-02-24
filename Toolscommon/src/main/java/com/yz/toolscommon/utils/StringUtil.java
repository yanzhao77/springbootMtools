package com.yz.toolscommon.utils;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	
	public static final char UNDERLINE = '_';
	public static final char LINE = '-';
	

    /**
     * @param express 判断字符串空
     * @return
     */
    public static boolean isBlank(String... express) {
        if (express == null)
            return true;

        if (express.length == 1)
            return express[0] == null || "".equals(express[0].trim());

        for (String e : express) {
            if (e != null && !"".equals(e.trim()))
                return false;
        }

        return true;
    }

    public static boolean isEqualsIn(String str, String... express) {
        if (str == null && express == null)
            return true;
        if (str != null && express == null)
            return false;

        return Arrays.asList(express).contains(str);
    }

    /**
     * @param num判断是不是数字
     * @return
     */
    public static boolean isNumber(String num) {
        try {
            return StringUtils.isNumeric(num.replaceAll("^[-]?(.*)$", "$1"));
        } catch (Exception e) {
            return false;
        }
    }

    public static int toInt(String num) {
        return Integer.valueOf(num);
    }

    public static String toBeautifulLine(String line, boolean... bs) {

        char[] lineArr = line.toCharArray();
        char lineChar;
        char constFlg = ' ';
        for (int i = 0; i < lineArr.length; i++) {
            lineChar = lineArr[i];
            switch (lineChar) {
                case ' ' :
                    if (constFlg != ' ') {
                        break;
                    }
                    if (((i + 1 < lineArr.length)
                            && (((bs.length == 0 || !bs[0]) && lineArr[i + 1] == ' ')
                                    || lineArr[i + 1] == '=' || lineArr[i + 1] == ','))
                            || ((i > 0) && (lineArr[i - 1] == '=' || lineArr[i - 1] == ','))) {
                        lineArr = ArrayUtils.remove(lineArr, i);
                        i--;
                    }
                    break;
                case '"' :
                    if (constFlg == ' ') {
                        constFlg = '"';
                    } else if (constFlg == '"') {
                        constFlg = ' ';
                    } else {
                        lineArr = ArrayUtils.insert(i, lineArr, '\\');
                        i++;
                    }
                    break;
                case '\'' :
                    if (constFlg == ' ') {
                        constFlg = '\'';
                    } else if (constFlg == '\'') {
                        constFlg = ' ';
                    }
                    break;
                default :
                    break;
            }
        }
        return (new String(lineArr)).trim();
    }

    public static String removeConst(String line) {

        char[] lineArr = line.toCharArray();
        char lineChar;
        char constFlg = ' ';
        for (int i = 0; i < lineArr.length; i++) {
            lineChar = lineArr[i];
            switch (lineChar) {
                case '"' :
                    if (constFlg == ' ') {
                        constFlg = '"';
                    } else if (constFlg == '"') {
                        constFlg = ' ';
                    }
                    break;
                case '\'' :
                    if (constFlg == ' ') {
                        constFlg = '\'';
                    } else if (constFlg == '\'') {
                        constFlg = ' ';
                    }
                    break;
                default :
                    if (constFlg != ' ') {
                        lineArr = ArrayUtils.remove(lineArr, i);
                        i--;
                    }
                    break;
            }
        }
        return (new String(lineArr)).trim();
    }

    /**
     * @param STR 首字母转小写
     * @return sTR
     */
    public static String toLowerFristChar(String str) {
        if (StringUtil.isBlank(str))
            return str;

        if (str.length() == 1)
            return str.toLowerCase();

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    
    /**
     * @param k39_rec 首字母转大写
     * @return K39_rec
     */
    public static String toUpperFristChar(String str) {
        if (StringUtil.isBlank(str))
            return str;

        if (str.length() == 1)
            return str.toUpperCase();

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    
    /**
     * @param fileName
     * @return
     */
    public static String getMapperName(String fileName) {
        if (StringUtil.isBlank(fileName))
            return fileName;

        return fileName.toLowerCase() + "Mapper";
    }
    
    public static String getMapperNameByMapper(String mapperName) {
        if (StringUtil.isBlank(mapperName))
            return mapperName;

        return toLowerFristChar(mapperName) + "Mapper";
    }

    
    /**
     * 
     * @param fileName "K39_REC" "TUTB-REC_ABOUT"
     * @return 			K39Rec   TutbRecAbout
     */
    public static String getEntityName(String fileName) {
        if (StringUtil.isBlank(fileName))
            return fileName;

        return toUpperFristChar(fileName.toLowerCase()).toString();
    }

    public static String getTableName(String fileName) {
        return fileName;
    }

    /*
    public static String rTrim(String str) {
        String rtnStr = "";

        if (str.startsWith(" ")) {
            rtnStr = InputUtil.subString(str, 0,
                    str.indexOf(str.trim().substring(0, 1)) + BytesUtil.string2Byte(str.trim()).length);
        } else {
            rtnStr = str.trim();
        }

        return rtnStr;
    }
    */

    /**
     * a 数子字符前面补0: 789  ,5位-> 00789  
     */
    public static String numFormat(String str, int len) {
        String zeroStr = "";

        for (int i = 0; i < len - str.length(); i++) {
            zeroStr = zeroStr + "0";
        }
        return zeroStr + str;
    }

    /**a字符串前后补空格space,  "str",7,true ->"str    "  
     * len 字符串总长度
     * flag true:后面补空格
     * 	false : 前面补空格
     */
    public static String strFormat(String str, int len, boolean flag) {
        String spaceStr = "";

        for (int i = 0; i < len - str.length(); i++) {
            spaceStr = spaceStr + " ";
        }
        return flag ? (str + spaceStr) : (spaceStr + str);
    }

    
    /**
     * a根据分隔符拆分字符串为 一行一行的数组
     */
    public static String[] splitLineByChgSign(String inputContext) {

    	if (inputContext == null || inputContext.isEmpty()) {
            return new String[]{};
        } else {
            return inputContext.split("\n|\r|\r\n");
        }
    }
    
    
    /**
     * 替换指定字符 转驼峰命名
     * 
     */
    public static String underlineToCamel(String param,char line) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == line) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    
    /**
     * 替换指定字符 转驼峰命名
     * [0,9} 下标从0开始,包前不包后
     * 
     * mc.start() 匹配到的字符开始索引
     * mc.end() 匹配到的字符结束索引(不包含)
     */
    public static String underlineToCamel2(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        StringBuilder sb = new StringBuilder(param);
        Matcher mc = Pattern.compile("-").matcher(param);
        int i = 0;
        while (mc.find()) {
            int position = mc.end() - (i++);
            sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
        }
        return sb.toString();
    }
    
    
    
    public static List<String> getStringByRegex(String regex, String str) {
        Matcher m = Pattern.compile(regex).matcher(str);
        List<String> strArr =new ArrayList<String>();
        while (m.find()) {
            String s = m.group();
            strArr.add(s);
        } 
        return strArr;
    }
    
}
