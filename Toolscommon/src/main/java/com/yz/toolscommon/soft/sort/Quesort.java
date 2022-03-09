package com.yz.toolscommon.soft.sort;/*
 *
 * 快速排序
 * */

public class Quesort {
    public static int[] quesort(int[] arr, int start, int end) {
        int tou = arr[start];
        int i = start;
        int j = end;
        while (i < j) {
            if (i < j && arr[i] < tou) {
                i++;
            }
            if (i < j && arr[j] > tou) {
                j--;
            }
            if (i < j && arr[i] == arr[j]) {
                i++;
            } else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        if (i - 1 > start) {
            arr = quesort(arr, start, i - 1);
        }
        if (j + 1 < end) {
            arr = quesort(arr, j + 1, end);
        }
        return arr;
    }
}
