package com.yz.toolscommon.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * @classname NumberUtils
 * @date 2023/03/27 14:42
 * @description TODO
 */
public class NumberUtils {
    public static int[] generateRandomArray(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 10000000);
        }
        return arr;
    }

    public static Integer[] getArr(int length) {
        List<Integer> numList = new ArrayList<>();
        while (numList.size() != length) {
            int num = (int) (Math.random() * 10000000);
            if (num != 0) {
                numList.add(num);
            }
        }

        return numList.toArray(numList.toArray(new Integer[length]));
    }
}
