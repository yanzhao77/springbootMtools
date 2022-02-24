package com.yz.toolscommon.mtools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static void main(String[] args) {
        String str = "(ALKDONL/CHUD)";
        String rex = "(?<=\\().+(?=\\))";

        StringUtils stringUtils = new StringUtils();
        List<String> stringRex = stringUtils.getStringRex(str, rex);
        System.out.println(stringRex.toString());

    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public List<String> getStringRex(String str, String rex) {
        List<String> stringList = new ArrayList<>();
        Pattern r = Pattern.compile(rex);
        Matcher m = r.matcher(str);
        while (m.find()) {
            stringList.add(m.group());
        }
        return stringList;
    }

    public List<String> stringsub(String str, String rex, String red) {
        List<String> stringList = new ArrayList<>();
        if (str.contains(rex)) {
            str = str.substring(str.indexOf(rex));
            stringList.add(str.substring(0, str.indexOf(red)).replaceAll(rex, "").trim());
            stringList.addAll(stringsub(str.substring(str.indexOf(red) + 1), rex, red));
        }
        return stringList;
    }
}
