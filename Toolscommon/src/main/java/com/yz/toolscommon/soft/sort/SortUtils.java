package com.yz.toolscommon.soft.sort;

import org.apache.commons.lang3.builder.DiffResult;

import java.util.*;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/14 16:47
 */
public class SortUtils {

    /**
     * Function to print common Strings with minimum index sum
     *
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> find(List<String> list1, List<String> list2) {
        // Function to print common Strings with minimum index sum
        // mapping Strings to their indices
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.size(); i++)
            map.put(list1.get(i), i);

        Vector<String> res = new Vector<String>(); // resultant list
        int minsum = Integer.MAX_VALUE;
        for (int j = 0; j < list2.size(); j++) {
            if (map.containsKey(list2.get(j))) {
                // If current sum is smaller than minsum
                int sum = j + map.get(list2.get(j));
                if (sum < minsum) {
                    minsum = sum;
                    res.clear();
                    res.add(list2.get(j));
                }

                // if index sum is same then put this
                // String in resultant list as well
                else if (sum == minsum) res.add(list2.get(j));
            }
        }

        return res;
    }

    /**
     * 冒泡
     *
     * @param arr
     * @return
     */
    public static Integer[] bubbleSort(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int num = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = num;
                }
            }
        }
        return arr;
    }

    /**
     * 选择
     * @param arr
     * @return
     */
    public static Integer[] selectionSort(Integer[] arr) {
        int minIndex, temp;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }
}
