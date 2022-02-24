package com.yz.toolscommon.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileUtils {

    public static void main(String[] args) {
        FileUtils ctfeTasklet = new FileUtils();
        List<File> listFile = ctfeTasklet.listFile(new File("E:\\App\\Data\\DA\\100013"));
        for (File file : listFile) {
            System.out.println(file.getName());
        }
    }

    public List<File> listFile(File file) {
        List<File> fileList = new ArrayList<>();
        for (File newFile : Objects.requireNonNull(file.listFiles())) {
            if (newFile.isDirectory()) {
                fileList.addAll(listFile(newFile));
            }
                fileList.add(newFile);
        }
        return fileList;
    }
}
