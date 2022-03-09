package com.yz.toolscommon.soft.sort;

import java.io.IOException;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/8 14:32
 */

public class MergeSort {

    //1
    //两路归并算法，两个排好序的子序列合并为一个子序列
//    public static void merge(int[] a, int left, int mid, int right,int[] tmp) {
//        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针
//        while (p1 <= mid && p2 <= right) {
//            if (a[p1] <= a[p2])
//                tmp[k++] = a[p1++];
//            else
//                tmp[k++] = a[p2++];
//        }
//        while (p1 <= mid) tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
//        while (p2 <= right) tmp[k++] = a[p2++];//同上
//
//        //复制回原素组
//        for (int i = left; i <= right; i++)
//            a[i] = tmp[i];
//    }
//
//    public static int[] mergeSort(int[] a, int start, int end) {
//        if (start < end) {//当子序列中只有一个元素时结束递归
//            int[] tmp = new int[a.length];//辅助数组
//            int mid = (start + end) / 2;//划分子序列
//            mergeSort(a, start, mid);//对左侧子序列进行递归排序
//            mergeSort(a, mid + 1, end);//对右侧子序列进行递归排序
//            merge(a, start, mid, end,tmp);//合并
//        }
//        return a;
//    }

    //2
//    public static int[] mergeSort(int[] arr, int low, int high) {
//        int mid = (high + low) / 2;
//        if (low < high) {
//            mergeSort(arr, low, mid);
//            mergeSort(arr, mid + 1, high);
//            merge(arr, low, mid, high);
//        }
//        return arr;
//    }
//
//    public static void merge(int[] arr, int low, int mid, int high) {
//        int i = low, j = mid + 1, k = low;
//        int index = low;
//        int[] temp = new int[arr.length];
//        while (i <= mid && j <= high) {
//            if (arr[i] <= arr[j]) {
//                temp[k++] = arr[i++];
//            } else {
//                temp[k++] = arr[j++];
//            }
//        }
//        while (i <= mid) {
//            temp[k++] = arr[i++];
//        }
//        while (j <= high) {
//            temp[k++] = arr[j++];
//        }
//        while (index <= high) {
//            arr[index] = temp[index];
//            index++;
//        }
//    }
    // 3
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
