package com.yz.toolscommon;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMain {

    static int num;

    public static void main(String[] args) {
        String inFileDir = "E:\\Nwt\\cache\\recv\\韩宝鑫\\シナリオテスト";
        String outFileDir = "D:\\data\\outFile";
        List<File> inFileList = new ArrayList<File>();
        String logFileSuffix = ".log";
        String call_rex = "JB1 : CALL";
        Map<File, List<String>> inFileValueMap = new HashMap<File, List<String>>();
        Map<String, List<String>> outFileValueMap = new HashMap<String, List<String>>();

        getFilePath(new File(inFileDir), logFileSuffix, inFileList);
        inFileList.forEach(file -> {
            inFileValueMap.put(file, readFile(file));
        });

        inFileValueMap.forEach((file, strings) -> {
            System.out.println("inFile" + "\t" + file);
            int call_index = 0;
            String newFileName = "null";
            for (int i = strings.size() - 1; i >= 0; i--) {
                String value = strings.get(i);
                if (value.contains(call_rex)) {
                    call_index = i;

                    // 获取 call 后面的名字
                    // 定义正则表达式
                    String regex = "CALL\\s+([^\\s]+)";
                    // 编译正则表达式
                    Pattern pattern = Pattern.compile(regex);
                    // 创建 Matcher 对象
                    Matcher matcher = pattern.matcher(value);
                    // 查找匹配
                    if (matcher.find()) {
                        // 获取匹配的部分
                        String matched = matcher.group(1);
                        matched = matched.replaceAll("@LIBL", "_");
                        // 去除特殊字符
                        newFileName = matched.replaceAll("[^a-zA-Z0-9_]", "");
                    }
                    break;
                }
            }
            List<String> newValueList = new ArrayList<>();
            for (int i1 = call_index; i1 < strings.size(); i1++) {
                newValueList.add(strings.get(i1));
            }
            String path = file.getPath().replace(inFileDir, "").replaceAll("\\\\", "_");
            newFileName = outFileDir + File.separator + newFileName + path;

            if (outFileValueMap.containsKey(newFileName)) {
                newFileName = newFileName + num;
                num += 1;
            }

            outFileValueMap.put(newFileName, newValueList);
        });
        outFileValueMap.keySet().forEach(System.out::println);
        outFileValueMap.forEach(FileMain::writeFile);
    }

    public static void getFilePath(File fileDir, String logFileSuffix, List<File> inFileList) {
        File[] files = fileDir.listFiles(pathname -> pathname.getName().endsWith(logFileSuffix) || pathname.isDirectory());
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                inFileList.add(file);
            } else {
                getFilePath(file, logFileSuffix, inFileList);
            }
        }
    }

    public static List<String> readFile(File file) {
        try {
            return Files.readAllLines(Paths.get(file.getPath()), Charset.forName("ms932"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void writeFile(String filePath, List<String> valueList) {
        FileWriter fileWriter = null;
        //快速读取文件流的路径
        try {
            //最加文件写入
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) file.createNewFile();

            fileWriter = new FileWriter(file);

            for (String value : valueList) {
                fileWriter.write(value + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //或者关闭写入流，写入数据，流不能使用；
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
