package com.yz.toolscommon.FileUtil;

import com.yz.toolscommon.utils.StringUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class FileUtils {

    public static void main(String[] args) {
//        List<File> listFile = ctfeTasklet.listFile(new File("E:\\App\\Data\\DA\\100013"));
//        HashMap<String, Object> map = new HashMap<>();
//        HashMap<String, Object> filesName = getFilesName(map, "E:\\App", "CN*");
//        System.out.println(filesName);
        String ss = "E:\\App\\Data\\100004\\CN02232CZ";
        String rex = "*all";
//        HashMap<String, Object> map = new HashMap<>();
        List<File> newFileList = new ArrayList<>();
        System.out.println(ss);
        List<File> fileListForStartsWithName = getFileListForStartsWithName(newFileList, "E:\\App\\Data\\100004", rex);
        for (File file : fileListForStartsWithName) {
            System.out.println(file.getName());
        }
        System.out.println(fileListForStartsWithName);

    }

    public static List<File> listFile(File file) {
        List<File> fileList = new ArrayList<>();
        for (File newFile : Objects.requireNonNull(file.listFiles())) {
            if (newFile.isDirectory()) {
                fileList.addAll(listFile(newFile));
            }
            fileList.add(newFile);
        }
        return fileList;
    }

    /**
     * フォルダ取得ファイルの取得
     *
     * @param allMap
     * @param folderPath
     * @param queryStr
     * @return
     */
    public static HashMap<String, Object> getFileMapForStartsWithName(HashMap<String, Object> allMap, String folderPath, String queryStr) {
        HashMap<String, Object> map = new HashMap<>();
        List<File> fileList = new ArrayList<>();
        File f = new File(folderPath);
        boolean isAll = queryStr.toUpperCase().equals("*ALL");
        queryStr = isAll ? queryStr : queryStr.replaceAll("\\*", "");
        if (f.exists()) {
            if (f.isDirectory()) {
                //路径为文件
                File[] fa = f.listFiles();
                for (int i = 0; i < Objects.requireNonNull(fa).length; i++) {
                    File fs = fa[i];

                    if (fs.isDirectory()) {
                        getFileMapForStartsWithName(allMap, fs.getPath(), queryStr);
                    } else if (isAll) {
                        fileList.add(f);
                        map.put(f.getPath(), fileList);
                    } else if (fs.getName().startsWith(queryStr)) {
                        System.out.println(fs.getName());
                        fileList.add(f);
                        map.put(f.getPath(), fileList);
                    }
                }
            } else if (isAll) {
                fileList.add(f);
                map.put(f.getPath(), fileList);
            } else if (f.getName().startsWith(queryStr)) {
                System.out.println(f.getName());
                fileList.add(f);
                map.put(f.getPath(), fileList);
            }
            if (fileList.size() > 0) {
                map.put(f.getPath(), fileList);
            }
            if (map.size() > 0) {
                allMap.put(folderPath, map);
            }
        }
        return allMap;
    }

    /**
     * フォルダ取得ファイルの取得
     *
     * @param fileList
     * @param folderPath
     * @param queryStr
     * @return
     */
    public static List<File> getFileListForStartsWithName(List<File> fileList, String folderPath, String queryStr) {
        List<File> newFileList = new ArrayList<>();
        File f = new File(folderPath);
        boolean isAll = queryStr.toUpperCase().equals("*ALL");
        queryStr = isAll ? queryStr : queryStr.replaceAll("\\*", "");
        if (f.exists()) {
            if (f.isDirectory()) {
                //路径为文件
                File[] fa = f.listFiles();
                for (int i = 0; i < Objects.requireNonNull(fa).length; i++) {
                    File fs = fa[i];
                    if (fs.isDirectory()) {
                        getFileListForStartsWithName(fileList, fs.getPath(), queryStr);
                    } else if (isAll) {
                        newFileList.add(fs);
                    } else if (fs.getName().startsWith(queryStr)) {
                        System.out.println(fs.getName());
                        newFileList.add(fs);
                    }
                }
            } else if (isAll) {
                newFileList.add(f);
            } else if (f.getName().startsWith(queryStr)) {
                System.out.println(f.getName());
                newFileList.add(f);
            }
            if (newFileList.size() > 0) {
                fileList.addAll(newFileList);
            }

        }
        return fileList;
    }

    /**
     * 获取文件夹下所有文件的名称 + 模糊查询（当不需要模糊查询时，queryStr传空或null即可）
     * 1.当路径不存在时，map返回retType值为1
     * 2.当路径为文件路径时，map返回retType值为2，文件名fileName值为文件名
     * 3.当路径下有文件夹时，map返回retType值为3，文件名列表fileNameList，文件夹名列表folderNameList
     *
     * @param folderPath 路径
     * @param queryStr   模糊查询字符串
     * @return
     */
    public static HashMap<String, Object> getFilesName2(String folderPath, String queryStr) {
        HashMap<String, Object> map = new HashMap<>();
        List<String> fileNameList = new ArrayList<>();//文件名列表
        List<String> folderNameList = new ArrayList<>();//文件夹名列表
        File f = new File(folderPath);
        if (!f.exists()) { //路径不存在
            map.put("retType", "1");
        } else {
            boolean flag = f.isDirectory();
            if (flag == false) { //路径为文件
                map.put("retType", "2");
                map.put("fileName", f.getName());
            } else { //路径为文件夹
                map.put("retType", "3");
                File fa[] = f.listFiles();
                queryStr = queryStr == null ? "" : queryStr;//若queryStr传入为null,则替换为空（indexOf匹配值不能为null）
                for (int i = 0; i < fa.length; i++) {
                    File fs = fa[i];
                    if (fs.getName().indexOf(queryStr) != -1) {
                        if (fs.isDirectory()) {
                            folderNameList.add(fs.getName());
                        } else {
                            fileNameList.add(fs.getName());
                        }
                    }
                }
                map.put("fileNameList", fileNameList);
                map.put("folderNameList", folderNameList);
            }
        }
        return map;
    }


    /**
     * 输出给定目录下的文件，包括子目录中的文件
     *
     * @param dirPath 给定的目录
     */
    public Map<String, Map> readFiles(String dirPath, Map<String, Map> fileAndDirMap) {
        // 建立当前目录中文件的File对象
        File curfile = new File(dirPath);
        // 取得代表目录中所有文件的File对象数组
        File[] list = curfile.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isDirectory()) {
                    Map<String, Map> map = new HashMap<>();
                    fileAndDirMap.put(file.getPath(), map);
                    readFiles(file.getPath(), map);
                } else {
                    fileAndDirMap.put(file.getPath(), null);
                }
            }
        }
        // 遍历file数组
        return fileAndDirMap;
    }


    /**
     * 获取文件内容
     *
     * @param fielname
     * @return
     */
    public String getResourcesByStream(String fielname) {
        String str = "";
        ClassPathResource resource = new ClassPathResource(fielname);
        try {
            InputStream inputStream = resource.getInputStream();
            // 将流转为字符串
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                byte[] b = new byte[10240];
                int n;
                while ((n = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, n);
                }
            } catch (Exception e) {
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (Exception e1) {
                }
            }
            str = outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 获取文件
     *
     * @param fielname
     * @return
     */
    public File getResourcesForFile(String fielname) {
        File file = null;
        ClassPathResource resource = new ClassPathResource(fielname);
        try {
            file= resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
