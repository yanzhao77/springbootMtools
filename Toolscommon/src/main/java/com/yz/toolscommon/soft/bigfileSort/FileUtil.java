package com.yz.toolscommon.soft.bigfileSort;

import com.yz.toolscommon.soft.sort.MergeSort;
import com.yz.toolscommon.soft.sort.Quesort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * * 将大文件分割成小文件
 * * 排序好之后写入小文件
 * TODO
 * @date 2022/3/8 14:42
 */
public class FileUtil {
    public void fileSplit(String readFileName, String outFileDirName) {

        long start = System.currentTimeMillis();
        int size = 1024 * 1024 * 10;
        StringBuilder builder = new StringBuilder(size);
        int a = 0;
        //这里每个文件存多少数据根据自己实际情况而定,我这里的数据是1024*1024*128,我一共分为了16个小文件.
        int[] arr = new int[size];
        try {
            File file = new File(readFileName);
            long l = file.length() / size;
            System.err.println(l + "\t 个文件");
            FileReader fileReader = new FileReader(readFileName);
            BufferedReader br = new BufferedReader(fileReader);
            String len;
            int i = 0;
            while ((len = br.readLine()) != null) {
                //每次只存你定好的数据量进数组
                arr[a++] = Integer.parseInt(len);
                //由于我把文件分为十六个,所以if语句中只进去十六次,刚好由i来生成十六个小文件
                if (a == size) {
                    //调用快速排序的方法对每个小文件排序
                    long stime2 = System.currentTimeMillis();
                    int[] k = Quesort.quesort(arr, 0, arr.length - 1);
//                    int[] k = MergeSort.mergeSort(arr, 0, arr.length - 1);
                    long etime2 = System.currentTimeMillis();
                    System.out.println(arr.length + "\t 个数据");
                    System.out.println("quesort 排序用时:" + (etime2 - stime2));
                    i++;
                    for (int i1 = 0; i1 < k.length; i1++) {
                        builder.append(k[i1]);
                        builder.append("\n");
                    }
                    BufferedWriter bw = new BufferedWriter(new FileWriter(outFileDirName + "\\" + i + ".txt"));
                    bw.write(builder.toString());
                    builder.setLength(0);
                    a = 0;
                    bw.close();
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用 BufferedWriter 写文件
     *
     * @param filepath
     * @param content
     */
    public void writerFile(String filepath, String content) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filepath))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileWriterMethod(String filepath, String content) {
        // 第二个 append 的参数传递一个 true = 追加文件的意思
        try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            fileWriter.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creat3GBfile(String fielName) {
        StringBuffer stringBuilder = new StringBuffer();
        for (int i = 0; i < 10000000; i++) {
            int v = (int) (Math.random() * 1000000000);
            stringBuilder.append(v + System.lineSeparator());
        }
        writerFile(fielName, stringBuilder.toString());
        for (int i = 0; i < 30; i++) {
            fileWriterMethod(fielName, stringBuilder.toString());
        }
    }

    public void readandfile(String readFileName) {
        int size = 1024 * 1024 * 8;
        StringBuilder builder = new StringBuilder(size);
        int a = 0;
        //这里每个文件存多少数据根据自己实际情况而定,我这里的数据是1024*1024*128,我一共分为了16个小文件.
        int[] arr = new int[size];
        try {
            File file = new File(readFileName);
            long l = file.length() / size;
            System.err.println(l + "\t 个文件");
            FileReader fileReader = new FileReader(readFileName);
            BufferedReader br = new BufferedReader(fileReader);
            String len;
            while ((len = br.readLine()) != null) {
                //每次只存你定好的数据量进数组
                arr[a++] = Integer.parseInt(len);
                //由于我把文件分为十六个,所以if语句中只进去十六次,刚好由i来生成十六个小文件
                if (a == size) {
                    //调用快速排序的方法对每个小文件排序
                    long stime2 = System.currentTimeMillis();
                    int[] k = Quesort.quesort(arr, 0, arr.length - 1);
//                    int[] k = MergeSort.mergeSort(arr, 0, arr.length - 1);
                    long etime2 = System.currentTimeMillis();
                    System.out.println(arr.length + "\t 个数据");
                    System.out.println("quesort 排序用时:" + (etime2 - stime2));

                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        long stime2 = System.currentTimeMillis();


        String fielName = "E:\\App\\test.txt";
        FileUtil fileUtil = new FileUtil();
        String outDir = "E:\\App\\spileSort\\";
        String outDir2 = "E:\\App\\spileSort\\1.txt";
        fileUtil.readandfile(outDir2);
        long etime2 = System.currentTimeMillis();
        System.out.println("BufferedWriter 写入用时:" + (etime2 - stime2));
    }
}
