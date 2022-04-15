package com.yz.toolscommon;

import com.yz.toolscommon.soft.sort.SortUtils;
import com.yz.toolscommon.utils.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;
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
        Integer[] arr1 = new Integer[integerList.size()];
        integerList.toArray(arr1);
        SortUtils.bubbleSort(arr1);

        for (Integer integer : arr1) {
            System.out.println(integer);
        }
    }

    @Test
    public void test4() {
        int i = (int) (Math.random() * 10000000);
        System.out.println(i);

    }

    @Test
    public void test5() {

        String rex = "[^/]+(?!.*/)";

        String str = "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\オンライン\\CLP\\TKKSRCL\\QCLPSRC\\XRCVRLD.CLP(33):    \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\オンライン\\CLP\\TKKSRCL\\QCLPSRC\\XSNDRLD.CLP(33):    \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLP\\TKKSRCL\\QCLPSRC\\DC3$M540.CLP(512):      \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLP\\TKKSRCL\\QCLPSRC\\DD3$K100.CLP(56):       \n" +
                "D:\\kpp\\01_移行前\\06_ソース一式\\棚卸結果_20210906\\正味資産_0926\\バッチ\\CLP\\TKKSRCL\\QCLPSRC\\FE3$R020.CLP(26):       \n";

        List<String> stringRex = StringUtil.getStringRex(str, rex);

        System.out.println(stringRex.get(0));
    }


    @Test
    public void test6() {

//        Map<String, Integer> posMap = new LinkedHashMap<String, Integer>();
//        posMap.put("key1", 10);
//        posMap.put("key2", 25);
//        posMap.put("key3", 40);
//
//
//        Object maxValue = StringUtil.getMaxValue(posMap);
        String maxValue = StringUtil.getLenStr(40);
        String value = "1313";
        int index = 21;
        String str = StringUtil.replaceStrForEndIndex(maxValue, index, value);
        System.out.println(maxValue.length());
        System.out.println(str);
    }
}
