package com.yz.toolscommon;


import com.yz.toolscommon.FileUtil.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class FileTest {

    @Test
    public void test2() throws IOException {
        String outFilePath = "D:\\开发一部\\023801.asp\\02_移行後\\02_開発区\\05_DB設計\\DDL\\FileToDB\\";

        String sequenceFilePath = "D:\\开发一部\\023801.asp\\02_移行後\\02_開発区\\05_DB設計\\DDL\\FileToDB\\CreatTable\\SEQUENCE";
        String tableFilePath = "D:\\开发一部\\023801.asp\\02_移行後\\02_開発区\\05_DB設計\\DDL\\FileToDB\\CreatTable\\TABLE";
        String triggerFilePath = "D:\\开发一部\\023801.asp\\02_移行後\\02_開発区\\05_DB設計\\DDL\\FileToDB\\CreatTable\\TRIGGER";

        FileUtils.readAllFileToFile(sequenceFilePath, outFilePath + "sequence.sql");
        FileUtils.readAllFileToFile(tableFilePath, outFilePath + "table.sql");
        FileUtils.readAllFileToFile(triggerFilePath, outFilePath + "trigger.sql");
    }

    @Test
    public void test3() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("D:\\workspace1\\AscoOut\\FileToDB\\CreatTable\\INDEX", "index.sql");
        stringMap.put("D:\\workspace1\\AscoOut\\FileToDB\\CreatTable\\SEQUENCE", "sequence.sql");
        stringMap.put("D:\\workspace1\\AscoOut\\FileToDB\\CreatTable\\TABLE", "table.sql");
        stringMap.put("D:\\workspace1\\AscoOut\\FileToDB\\CreatTable\\TRIGGER", "trigger.sql");
        stringMap.forEach((dirPath, sqlFileName) -> {
            try {
                FileUtils.readAllFileToFile(dirPath, new File(dirPath).getParent() + "\\" + sqlFileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Integer [] 小刚=new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12};
        Integer [] 弟弟=new Integer[]{5,10};

    }
}
