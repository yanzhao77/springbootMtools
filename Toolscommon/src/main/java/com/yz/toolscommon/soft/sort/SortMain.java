package com.yz.toolscommon.soft.sort;

import com.yz.toolscommon.FileUtil.DataUtil;
import com.yz.toolscommon.utils.DataUtils;
import com.yz.toolscommon.utils.NumberUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * @author yanzhao
 * @version 1.0
 * @classname SortMain
 * @date 2023/03/27 15:32
 * @description TODO
 */
public class SortMain
{

    public static void main(String[] args) {
        Integer[] arr3 = NumberUtils.getArr(100000000);
        int[] arr =Arrays.stream(arr3).mapToInt(Integer::valueOf).toArray();
        System.out.println("Given Array");
        Date startDate = DataUtils.getNowDate();

        MergeSort3 ob = new MergeSort3();
        ob.mergeSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        String distanceTime = DataUtils.getDistanceTime(startDate, DataUtils.getNowDate());
        System.out.println(distanceTime);

    }
}
