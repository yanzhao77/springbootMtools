package com.yz.toolscommon.mtools;

import com.yz.toolscommon.config.Const;

import java.io.*;

/**
 * @author maoll
 */
public class CreateFile5 {
    public static void main(String[] args) {
        String filePath = Const.result_1_txt;
        siphonFileString(new File(filePath));
    }


    /**
     * 关键字 提取到 Set<String> 里
     * 正则匹配的文字段落抽取到result文件中
     */
    public static void siphonFileString(File file) {

        StringBuilder bf = new StringBuilder();
        String lineSep = System.lineSeparator();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件
            String s = "";
            while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行
                // 每读一行换一行
                String s1 = s.replaceAll("\\s", "");

                bf.append(s1).append(lineSep);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //a写入文件
        try {
            File outFile = new File(Const.result_2_txt);
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
            osw.write(bf.toString());
            //a写入完成关闭流
            osw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
