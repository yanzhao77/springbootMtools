package com.yz.toolscommon.soft.sort;

import java.io.IOException;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/8 14:32
 */

public class MergeSort {

    /**
     * 归并排序
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if(null == arr) {
            return;
        }

        if(left < right) {
            //找中间位置进行划分
            int mid = (left+right)/2;
            //对左子序列进行递归归并排序
            mergeSort(arr, left, mid);
            //对右子序列进行递归归并排序
            mergeSort(arr, mid+1, right);
            //“合”。 进行归并
            merge(arr, left, mid, right);
        }
    }

    /**
     * 进行归并
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] tempArr = new int[arr.length];
        int leftStart = left;
        int rightStart = mid+1;
        int tempIndex = left;

        while(leftStart <= mid && rightStart <= right) {
            if(arr[leftStart] < arr[rightStart]) {
                tempArr[tempIndex++] = arr[leftStart++];
            } else {
                tempArr[tempIndex++] = arr[rightStart++];
            }
        }

        while(leftStart <= mid) {
            tempArr[tempIndex++] = arr[leftStart++];
        }

        while(rightStart <= right) {
            tempArr[tempIndex++] = arr[rightStart++];
        }

        while(left <= right) {
            arr[left] = tempArr[left++];
        }
    }

    private static void showArr(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    public static void main(String[] args) throws IOException {
        long stime2 = System.currentTimeMillis();

        int size = 10000000;
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = (int) (Math.random() * 1000000000);
        }
//        int[] a = {49, 38, 65, 97, 76, 13, 27, 50};
        mergeSort(a, 0, a.length - 1);
//        Quesort.quesort(a, 0, a.length - 1);
        System.out.println("排好序的数组：");
//        for (int e : a)
//            System.out.print(e + " ");
        System.out.println();
        long etime2 = System.currentTimeMillis();
        System.out.println("quesort 排序用时:" + (etime2 - stime2));
    }
}
