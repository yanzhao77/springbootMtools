package com.yz.toolscommon;

import com.yz.toolscommon.soft.sort.MergeSort2;
import com.yz.toolscommon.soft.sort.MergeSort3;
import com.yz.toolscommon.utils.DataUtils;
import com.yz.toolscommon.utils.NumberUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

/**
 * @author yanzhao
 * @version 1.0
 * @classname SortTests
 * @date 2023/03/27 14:41
 * @description TODO
 */
@SpringBootTest
public class SortTests
{
    @Test
    public void test2() {
        Integer[] arr3 = NumberUtils.getArr(100000000);
        int[] arr = Arrays.stream(arr3).mapToInt(Integer::valueOf).toArray();
        System.out.println("Given Array");
        Date startDate = DataUtils.getNowDate();

        MergeSort2 ob = new MergeSort2();
        ob.quickSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array");
        String distanceTime = DataUtils.getDistanceTime(startDate, DataUtils.getNowDate());
        System.out.println(distanceTime);
    }
}
