package com.yz.toolscommon.mtools;

import com.yz.toolscommon.config.Const;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author maoll
 */
public class CreateFile7 {

    /**
     * 提取MACRO代码块到同一个文件中
     */

    public static void main(String[] args) {
        long curr1 = System.currentTimeMillis();
        System.out.println("开始");
        String pathListFile = "D:\\fileNameList.txt";

        List<File> fileList = new ArrayList<>();


//        readFileByChars(pathListFile, fileList);    //文件路径 -> List<File>中
        siphonFileString(new File(pathListFile));




        long curr2 = System.currentTimeMillis();
        long time = (curr2 - curr1) / 1000;
        System.out.println("完成写入...! 耗时:" + time + "秒");
    }


    /**
     * 2 文件内正则匹配的字符存入Set<String>中
     */
    public static void filter(File file, Set<String> stringSet) {

        String pattern = Const.regex_8;
        StringBuilder bf = new StringBuilder();
        String lineSep = System.lineSeparator();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                bf.append(s).append(lineSep);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(bf.toString());

        while (m.find()) {
            String resultStr = m.group();
            stringSet.add(resultStr);

        }
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
               if (!s.startsWith("S")){
//                   System.out.println(s);
               }else {
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
    public static void writeFile(Set<String> stringSet) {
        String lineSep = System.lineSeparator();
        // Set -> List
        List<String> list = new ArrayList<>(stringSet);
        //List字母排序
        List<String> orderList = list.stream().sorted().collect(Collectors.toList());

        for (int i = orderList.size() - 1; i >= 0; i--) {
            
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(Const.result_txt));
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

}
