package com.yz.javamail2.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static void main(String[] args) {
        String str = "INFO@KPP-GR.COM";
//        String rex = "(?<=\\().+(?=\\))";
        String rex = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        StringUtils stringUtils = new StringUtils();
        List<String> stringRex = getStringRex(str, rex);
        System.out.println(stringRex.toString());

    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static List<String> getStringRex(String str, String rex) {
        List<String> stringList = new ArrayList<>();
        Pattern r = Pattern.compile(rex);
        Matcher m = r.matcher(str);
        while (m.find()) {
            stringList.add(m.group());
        }
        return stringList;
    }

    public static List<String> stringsub(String str, String rex, String red) {
        List<String> stringList = new ArrayList<>();
        if (str.contains(rex)) {
            str = str.substring(str.indexOf(rex));
            stringList.add(str.substring(0, str.indexOf(red)).replaceAll(rex, "").trim());
            stringList.addAll(stringsub(str.substring(str.indexOf(red) + 1), rex, red));
        }
        return stringList;
    }

    /**
     * 关键字 提取到 Set<String> 里
     * 正则匹配的文字段落抽取到result文件中
     */
    public static List<String>siphonFileString(File file, Charset charset) {
        //匹配文件内的正则
        StringBuilder bf = new StringBuilder();
        List<String> stringList = new ArrayList<>();
//        String lineSep = System.lineSeparator();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                // 每读一行换一行
                bf.append(s);//.append(lineSep);
                stringList.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringList;
    }
}
