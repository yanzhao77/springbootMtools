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
public class CreateFile6 {

    /**
     * 提取MACRO代码块到同一个文件中
     */

    public static void main(String[] args) {
        long curr1 = System.currentTimeMillis();
        System.out.println("开始");
//		Set<String> stringSet = new HashSet<>();

//		filter(new File(pathListFile),stringSet);		//a过滤重复地址
//		filter2(new File(pathListFile),stringSet);		//过滤重复但不用正则
        siphonFileString(new File(Const.result_txt));


        //writeFile(stringSet);				//写出数据到文件中

        //重复过滤,写出到文件
//		filter(Const.configPatch_1);


        long curr2 = System.currentTimeMillis();
        long time = (curr2 - curr1) / 1000;
        System.out.println("完成写入...! 耗时:" + time + "秒");
    }


    /**
     * 关键字 提取到 Set<String> 里
     * 正则匹配的文字段落抽取到result文件中
     */
    public static void siphonFileString(File file) {
        String pattern = Const.regex_11_1;            //匹配文件内的正则
        StringBuilder bf = new StringBuilder();
        String lineSep = System.lineSeparator();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                // 每读一行换一行
                bf.append(s).append(lineSep);
//				bf.append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(bf.toString());
        StringBuilder bf2 = new StringBuilder();
        while (m.find()) {
            String resultStr = m.group();

//			  bf2.append(file.getPath()).append(";").append(resultStr).append(lineSep);
//            bf2.append(file.getName()).append(lineSep).append(resultStr).append(lineSep);
            bf2.append(lineSep).append(resultStr).append(lineSep);

        }

        //a写入文件
        try {
            File outFile = new File(Const.result_1_txt);
            FileOutputStream fos = null;
            if (!outFile.exists()) {
                outFile.createNewFile();//a如果文件不存在，就创建该文件
                fos = new FileOutputStream(outFile);//a首次写入获取
            } else {
                //a如果文件已存在，那么就在文件末尾追加写入
                fos = new FileOutputStream(outFile, true);//a这里构造方法多了一个参数true,表示在文件末尾追加写入
            }

            OutputStreamWriter osw = new OutputStreamWriter(fos, "SHIFT-JIS");//a指定以UTF-8格式写入文件

            //a遍历list
            osw.write(bf2.toString());
            //a写入完成关闭流
            osw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public static void WritFileForByte(String fileName, byte[] bytes) {
        File file = new File(fileName);
        if (file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(bytes);
                bos.flush();
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void WritFileForStr(String fileName, String var) {
        File file = new File(fileName);
        if (file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                bos.write(var.getBytes());
                bos.flush();
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
