package com.yz.toolscommon.mtools;

import com.yz.toolscommon.utils.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author maoll
 */
public class CreateFile8 {

    /**
     * 提取MACRO代码块到同一个文件中
     */

    public static void main(String[] args) {
        long curr1 = System.currentTimeMillis();
        System.out.println("开始");
        String path = "D:\\itoutyuu\\01_移行前\\06_ソース一式\\正味資産_210705(20210615版)\\";
//        String path = "D:\\xml";
        ArrayList<File> fileList = new ArrayList<>();
        getFiles(path, fileList);
        List<String> stringSet = new ArrayList<>();
        for (File file : fileList) {
            filter(file, stringSet);
        }
        writeFile(stringSet);
        long curr2 = System.currentTimeMillis();
        long time = (curr2 - curr1) / 1000;
        System.out.println("完成写入...! 耗时:" + time + "秒");
    }


    /**
     * 2 文件内正则匹配的字符存入Set<String>中
     */
    public static void filter(File file, List<String> stringFileList) {
//        String regex = "(?<=id=\")[\\w.]+?(?=\")";
//        String regex = "(?<=fileName\" value=\")[\\w.]+(?=\")";
        String regex = "ACCEPT.+?CONSOLE";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                List<String> stringByRegex = getStringByRegex(regex, s);
                System.out.println(Arrays.toString(stringByRegex.toArray()));
                for (String byRegex : stringByRegex) {
                    if (byRegex.contains("_")) {
                        if (!byRegex.contains("TEMP") && !byRegex.contains("SYSINFILE") && !byRegex.contains("PARFILE")) {

                            stringFileList.add(file.getPath() + "\t" + byRegex);
                        }
                    }
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        long count = stringFileList.stream().distinct().count();
//        if (stringFileList.size() == count) {
//            System.out.println(file.getPath() + "\t" + file.getName() + "\t" + "没有重复元素");
//
//        } else {
//            stringFileList.add(file.getPath() + "\t" + file.getName());
//            System.out.println(file.getPath() + "\t" + file.getName() + "\t" + "有重复元素");
//        }


    }

    public static List<String> getStringByRegex(String regex, String str) {
        Matcher m = Pattern.compile(regex).matcher(str);
        List<String> strArr = new ArrayList<String>();
        while (m.find()) {
            String s = m.group();
            strArr.add(s);
        }
        return strArr;
    }


    /**
     * 3过滤重复但不用正则
     */
    public static void filter2(File file, Set<String> stringSet) {

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                stringSet.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * w文件路径-File对象 - 加入 文件List<File>
     */
    public static void readFileByChars(String fileName, List<File> fileList) {

        File file = new File(fileName);
        try {
            // a构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));

            String s = null;
            while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
                File f = new File(s);
                fileList.add(f);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 关键字 提取到 Set<String> 里
     * 正则匹配的文字段落抽取到result文件中
     */
    public static void siphonFileString(File file) {
        String pattern = "(?<=value=\")\\w+_\\w+(?=\")";            //匹配文件内的正则
        StringBuilder bf = new StringBuilder();
        String lineSep = System.lineSeparator();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                // 每读一行换一行
                if (!s.startsWith("S")) {
//                   System.out.println(s);
                } else {
                    System.err.println(s);
                }

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 关键字 提取到 Set<String> 里
     * 匹配文件夹下每个文件名
     */
    public static void siphonFileStr(File file) {
        List<File> fileList = new ArrayList<>();
//        getFiles(Const.staticConfigPatch, fileList);

    }


    public static List<File> getFileList(String strPath) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("avi")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return fileList;
    }

    public static void getFiles(String path, ArrayList<File> list) {
        //目标集合fileList
        File file = new File(path);
        if (file.getName().equals(".svn")) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getFiles(fileIndex.getPath(), list);
                } else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    list.add(fileIndex);
                }
            }
        }
    }


    /**
     * Set<String> 数据写入文件
     */
    public static void writeFile(List<String> stringSet) {
        String lineSep = System.lineSeparator();
        // Set -> List
        List<String> list = new ArrayList<>(stringSet);
        //List字母排序
        List<String> orderList = list.stream().sorted().collect(Collectors.toList());

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(Const.result_xml_2_txt));
            //输出到文件中
            orderList.forEach(f -> {
                try {
                    out.write(f + lineSep);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            System.out.println("文件创建Error！");
        }

    }

    public static List<String> stringList = Arrays.asList("ACTMEN_F                ",
            "AMM003A_FA_W03          ",
            "AMM003A_FA_W04          ",
            "AMM003C_FA_Z03          ",
            "AMM003C_FA_Z04          ",
            "AMM003ES_FA_W03         ",
            "AMM003ES_FA_W04         ",
            "AMM003ES_FD_W03         ",
            "AMM003ES_FF_W03         ",
            "AMM003ES_FH_W03         ",
            "AMM003ES_FO_W03         ",
            "AMM003ZZ_FA_W03         ",
            "AMM004ZZ_FA_W03         ",
            "ASM04CJ_C0              ",
            "ASM05KL_MASTERRQ        ",
            "ASM05KM_MASTERRQ        ",
            "BANGO_F                 ",
            "CRM30_MASTERRQ          ",
            "D11D73_ISAM             ",
            "D97M31CH_MST1_REQ       ",
            "D97M31CH_MST2_REQ       ",
            "D97M31CH_MST3_REQ       ",
            "D97M31CH_MST4_REQ       ",
            "D97M31CH_MST5_REQ       ",
            "D97M31CH_MST6_REQ       ",
            "DV_MASTERRQ             ",
            "E13MR1CJ_C_RQ           ",
            "E13MR1CJ_L2             ",
            "E13MR1CJ_RQ             ",
            "E41M45S2_L1_ZEN         ",
            "E41M45SV_MASTERL1_ZEN   ",
            "E44M39_PUP7038          ",
            "E44M97_ISF              ",
            "F4Z11_Z_MASTERF2        ",
            "FBD002_D0B_MASTERD0     ",
            "FBD002_D0D_MASTERD0     ",
            "FBD002_D0H_MASTERD0     ",
            "FBD002_FBD_MASTERD0     ",
            "FBD002_F_MASTERD0       ",
            "FBD003_FUK_MASTERD0     ",
            "FBD003_HIR_MASTERD0     ",
            "FBD003_NGY_MASTERD0     ",
            "FBD003_OSA_MASTERD0     ",
            "FBD003_TOK_MASTERD0     ",
            "HAISIN_F                ",
            "IMSD4_KSN               ",
            "IMSD4_TOU               ",
            "ISC0607_RQIS            ",
            "ISC0628_CALEN           ",
            "ISC0635_KEYHN_IS        ",
            "ISC0801C_MST00025       ",
            "ISC0802B_MST00025       ",
            "ISC0803_MST00025        ",
            "ISC0812_MST00025        ",
            "ISC1116_ISM_SYOK        ",
            "ISC1116_ISM_TOKY        ",
            "ISC1316_ISM_SYOK        ",
            "ISC1316_ISM_TOKY        ",
            "ISC1317_ISM_SYOK        ",
            "ISC1317_ISM_TOKY        ",
            "JJK_MASTERRQ            ",
            "K12DBGF_MASTERRQ        ",
            "K12DBJ_D4               ",
            "K12M2825_MSTRQ          ",
            "K12M28K_MASTERRQ_K      ",
            "K12M2925_MSTRQ          ",
            "K12M29JU_MSTRQ          ",
            "K12M29K_MASTERRQ_K      ",
            "K12M3125_MSTRQ          ",
            "K12M31K_MASTERRQ_K      ",
            "K12M3225_MSTRQ          ",
            "K12M32K_MASTERRQ_K      ",
            "K12M3325_MSTRQ          ",
            "K12M33JU_MSTRQ          ",
            "K12M33K_MASTERRQ_K      ",
            "K12M3525_MSTRQ          ",
            "K12M35K_MASTERRQ_K      ",
            "K12M9023_MSTERR         ",
            "K12M90K_MASTERRQ_K      ",
            "K12M9123_MSTERR         ",
            "K12M9323_MSTERR         ",
            "K12MC9A_MASTERRQ        ",
            "K12MC9C_MASTERRQ        ",
            "K12MCA2_MASTERRQ        ",
            "K12MCA3_MASTERRQ        ",
            "K12MD2_MASTERF2         ",
            "K12MD2_MASTERRQ         ",
            "K12MD5CV_MASTERRQ       ",
            "K12RC1GF_B_WR1          ",
            "K12RC1GF_C_WR1          ",
            "K12RC1GF_MASTERRC       ",
            "K12RC1GF_R1             ",
            "K12RC1GF_R3             ",
            "K12RC1_B_WORKR1         ",
            "K12RC1_C_WORKR1         ",
            "K12RC1_R1               ",
            "K12RC1_R3               ",
            "K12RC1_WORKR3           ",
            "K21R30JG_MASTERF2       ",
            "K21R30JG_MASTERRQ       ",
            "K21R30ZN_MASTERF2       ",
            "K21R30ZN_MASTERRQ       ",
            "K22D38_ISAM_RQ          ",
            "K22D38_ISG_R2           ",
            "K22D38_ISG_R7           ",
            "K22D38_IS_R2            ",
            "K22D38_IS_R7            ",
            "K22D98_MASTERR2         ",
            "K22D98_MASTERR3         ",
            "K22D98_MASTERR7         ",
            "K22D98_MASTERR8         ",
            "K43M0123_MSTERR         ",
            "K90D34_MASTERF0         ",
            "K90M33_ANB_F2           ",
            "K90M45VT_ZN09_C0        ",
            "KAI3_C0                 ",
            "KAICHK_ZEN_W093         ",
            "KAM107LG_MSTB0          ",
            "KAM107LG_MSTF0          ",
            "KAM107LG_MSTL1          ",
            "KAM107_MSTB0            ",
            "KAM107_MSTF0            ",
            "KAM107_MSTL1            ",
            "KAM128RQ_MSTRQ          ",
            "KSNJET_MASTERRQ         ",
            "KZM012CV_A_R1           ",
            "KZM012CV_B_R1           ",
            "KZM012CV_R1             ",
            "KZM012V3_A_R1           ",
            "KZM012V3_B_R1           ",
            "KZM012V3_R1_W           ",
            "KZM012V3_R3_W           ",
            "KZM012_A_R1             ",
            "KZM012_B_R1             ",
            "KZM012_ISFG87           ",
            "KZM012_R1_W             ",
            "KZM012_R3_W             ",
            "M44B_MASTERRQ           ",
            "MASTERB0_BRND           ",
            "MASTERB0_ENTRY          ",
            "MASTERB0_SM0            ",
            "MASTERC0_CPY            ",
            "MASTERC0_K24RC2         ",
            "MASTERC0_KESN_ZENS      ",
            "MASTERCJ_FIX            ",
            "MASTERCM_FIX            ",
            "MASTERDV_BUKA           ",
            "MASTERDV_BUKA_ZEN       ",
            "MASTERDV_ZEN            ",
            "MASTERF0_BRND           ",
            "MASTERF0_SM0            ",
            "MASTERF0_SYUK           ",
            "MASTERF2_GYO_SSM039     ",
            "MASTERF2_GYO_SSM039A    ",
            "MASTERF2_NEW            ",
            "MASTERF2_Z              ",
            "MASTERL1_E44M40         ",
            "MASTERL1_FIX            ",
            "MASTERL1_SM0            ",
            "MASTERL2_E41M40         ",
            "MASTERRC_EGY            ",
            "MASTERRC_FIX            ",
            "MASTERRC_SHO            ",
            "MASTERRQ_ASM            ",
            "MASTXCHU_FIX            ",
            "MASTXHNB_CHU            ",
            "MASTXHNB_NEW            ",
            "MSG_F                   ",
            "MST00015_FIX            ",
            "MST00025_ISC0817        ",
            "MST00025_ISC1027        ",
            "MST01106_ENTRY          ",
            "MST01106_FIX            ",
            "MST01249_ISF            ",
            "MST01323_ISM            ",
            "MSZENC0_MSTC0ZEN        ",
            "PUP70012_KEEP           ",
            "S06_MSLOCS01_PARA       ",
            "S12_MSLOCS01_PARA       ",
            "SCT01321_JNJSYOK_ISM    ",
            "SCT01321_JNJTOKY_ISM    ",
            "SIM_KNR00001            ",
            "SSM00123_MSTRQ          ",
            "SSM00125_MSTRQ          ",
            "SSM00223_MSTRQ          ",
            "SSM00225_MSTRQ          ",
            "SSM002J1_MSTRQ          ",
            "SSM002JU_MSTRQ          ",
            "SSM00623_MSTRQ          ",
            "SSM00625_MSTRQ          ",
            "SSM00SS_MASTERRQ        ",
            "SSM02123_MSTRQ          ",
            "SSM021KK_MSTRQ          ",
            "SSM021_MSTRQ            ",
            "SSM02225_MSTRQ          ",
            "SSM02325_MSTRQ          ",
            "SSM02425_MSTRQ          ",
            "SSM02625_MSTRQ          ",
            "SSM02725_MSTRQ          ",
            "SSM02825_MSTRQ          ",
            "SSM029_MSTRQ            ",
            "SSM032_A_MSTRQ          ",
            "SSM032_B_MSTRQ          ",
            "SSM033DY_ANBDAY_F2      ",
            "SSM033DY_MSTRQ          ",
            "SSM033_MSTRQ            ",
            "SSM034DY_MSTRQ          ",
            "SSM034_MSTRQ            ",
            "SSM035DY_MSTRQ          ",
            "SSM035_MSTRQ            ",
            "SSM036_MASTERF2         ",
            "SSM036_MASTERRQ         ",
            "SSM037_A_MSTF2          ",
            "SSM037_MSTRQ            ",
            "SSM038B_MASTERF2        ",
            "SSM038_MASTERRQ         ",
            "SSM039A_MASTERRQ        ",
            "SSM039_MASTERRQ         ",
            "SSM041T5_TF2            ",
            "SSM051RQ_MSTRQ          ",
            "SSM0_MASTERCD_K         ",
            "SSM31_MASTERRQ          ",
            "STAY_M                  ",
            "TEL_MASTERB0            ",
            "TEL_MASTERF0            ",
            "TEL_MASTERL1            ",
            "TESTM01_MASTERF2        ",
            "TRM30_MSTRR2            ",
            "TT2M28K_MASTERRQ_K      ",
            "TT2M32K_MASTERRQ_K      ",
            "TTTTERB0_ISFG1          ",
            "TTTTERF0_ISFG1          ",
            "TTTTERL1_ISFG1          ",
            "TTT_MASTERRQ            ",
            "URI_MASTERRC            ",
            "URI_MASTERRC_FIX        ",
            "ZAITERB0_ISFG1          ",
            "ZAITERF0_ISFG1          ",
            "ZAITERL1_ISFG1          ",
            "ZAI_MASTERRQ            ");

}
