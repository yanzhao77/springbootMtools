package com.yz.toolscommon;

import com.yz.toolscommon.soft.sort.SortUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class ToolscommonApplicationTests {

    //    @Test
//    void contextLoads() {
//        String dtr = "8.20 pDh:/ “春天太浪漫了，我想请它吃顿饭，如果你有空也可以一起来”%来自春天的日常碎片   https://v.douyin.com/NjUxywq/ 复制此链接，打开Dou音搜索，直接观看视频！";
//        String rex = "https([^}]*).+?\\s";
//        List<String> stringRex = StringUtil.getStringRex(dtr, rex);
//
//        System.out.println(stringRex);
//    }
    @Test
    public void test2() {
        String[] arr1 = new String[]{"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] arr2 = new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};

        // Creating list1
        List<String> list1 = new ArrayList<>(Arrays.stream(arr1).toList());

        // Creating list2
        List<String> list2 = new ArrayList<>(Arrays.stream(arr2).toList());
        List<String> stringList = SortUtils.find(list1, list2);

        System.out.println(stringList);

    }

    @Test
    public void test3() {
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < 50000; i++) {
            integerList.add((int) (Math.random() * 10000000));
        }
        Integer[] arr1=new Integer[integerList.size()];
        integerList.toArray(arr1);
        SortUtils.bubbleSort(arr1);

        for (Integer integer : arr1) {
            System.out.println(integer);
        }
    }

    @Test
    public void test4(){
        int i = (int) (Math.random() * 10000000);
        System.out.println(i);
    }
}
