package com.softroad.filefortreedemo.service.impl;

import cn.hutool.core.io.FileUtil;
import com.softroad.filefortreedemo.config.ConfigTemp;
import com.softroad.filefortreedemo.data.Data;
import com.softroad.filefortreedemo.entity.TreeData;
import com.softroad.filefortreedemo.service.TreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * @classname TreeServiceImpl
 * @date 2022/8/16 11:44
 * @description TODO
 */
@Slf4j
public class TreeServiceImpl implements TreeService {

    @Autowired
    Data data;

    @Autowired
    private ConfigTemp configTemp;

    @Override
    public void readDirToTree(List<File> oldFileList, List<File> newFileList) throws Exception {

        List<TreeData> oldTreeDataList = new ArrayList<>();
        List<TreeData> newTreeDataList = new ArrayList<>();
        for (File oldFile : oldFileList) {
            TreeData treeData = new TreeData();
            treeData.add("root", oldFile.getName(), new String(FileUtil.readBytes(oldFile)));
            oldTreeDataList.add(treeData);
        }
        for (File newFile : newFileList) {
            TreeData treeData = new TreeData();
            treeData.add("root", newFile.getName(), new String(FileUtil.readBytes(newFile)));
            newTreeDataList.add(treeData);
        }
        data.insertOldData(oldTreeDataList);
        data.insertNewData(newTreeDataList);

        data.checkDifference();
    }

    @Override
    public void readFileToTree(String oldFilePath, String newFilePath) throws Exception {
        TreeData oldTreeData = new TreeData();
        File oldFile = new File(oldFilePath);
        oldTreeData.add("root", oldFile.getName(), new String(FileUtil.readBytes(oldFile)));
        data.insertOldData(new ArrayList<>(Collections.singletonList(oldTreeData)));

        TreeData newTreeData = new TreeData();
        File newFile = new File(oldFilePath);
        oldTreeData.add("root", newFile.getName(), new String(FileUtil.readBytes(newFile)));
        data.insertNewData(new ArrayList<>(Collections.singletonList(newTreeData)));

        data.checkDifference();
    }

    @Override
    public void merge() {
        data.merge();
        data.checkDifference();
    }

    @Override
    public void save() {
        log.info("保存文件路径 ： " + configTemp.getOut_dir_path());
        for (TreeData treeData : data.getOldData()) {
            treeData.getRoot().updateKey();
            log.info(treeData.getRoot().getId());
        }
    }

    @Override
    public void printDifferenceReport(String reportFilePath) {
        log.info("打印差异报表: " + reportFilePath);
    }
}
