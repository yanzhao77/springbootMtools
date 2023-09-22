package com.softroad.filefortreedemo.data;

import com.softroad.filefortreedemo.entity.TreeData;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yanzhao
 * @version 1.0
 * @classname Data
 * @date 2022/8/16 13:59
 * @description TODO
 */

@Component
public class Data {

    // 原文件树结构
    @Getter
    private List<TreeData> oldData = new ArrayList<>();

    // 合并文件树结构
    private List<TreeData> newData = new ArrayList<>();

    /**
     * 清空数据
     */
    public void clear() {
        oldData.clear();
        newData.clear();
    }

    public void insertOldData(List<TreeData> treeMapList) {
        oldData.addAll(treeMapList);
    }

    public void insertNewData(List<TreeData> treeMapList) {
        newData.addAll(treeMapList);

    }

    /**
     * 检索树 不同地方并标记
     */
    public void checkDifference() {
        for (TreeData oldDatum : oldData) {
            Optional<TreeData> newDatum = newData.stream().filter(newTreeData -> newTreeData.getRoot().getData().equals(oldDatum.getRoot().getData())).findFirst();
            oldDatum.checkNode(oldDatum.getRoot(), newDatum.get().getRoot());
        }
    }


    /**
     * 合并
     */
    public void merge() {
        for (TreeData oldDatum : oldData) {
            Optional<TreeData> newDatum = newData.stream().filter(newTreeData -> newTreeData.getRoot().getData().equals(oldDatum.getRoot().getData())).findFirst();
            oldDatum.merge(oldDatum.getRoot(), newDatum.get().getRoot());
        }

    }


}
