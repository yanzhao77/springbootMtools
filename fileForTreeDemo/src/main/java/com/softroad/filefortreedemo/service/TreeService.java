package com.softroad.filefortreedemo.service;

import java.io.File;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * @classname XmlService
 * @date 2022/8/16 11:42
 * @description TODO
 */
public interface TreeService {

    /**
     * 读取 文件夹下所有文件
     *
     * @param oldFileList
     * @param newFileList
     * @throws Exception
     */
    void readDirToTree(List<File> oldFileList, List<File> newFileList) throws Exception;

    /**
     * 读取文件
     *
     * @param oldFilePath
     * @param newFilePath
     * @throws Exception
     */
    void readFileToTree(String oldFilePath, String newFilePath) throws Exception;

    /**
     * 合并数据
     */
    void merge();

    /**
     * 保存
     */
    void save();

    /**
     * 打印差异报表
     *
     * @param reportFilePath
     */
    void printDifferenceReport(String reportFilePath);
}
