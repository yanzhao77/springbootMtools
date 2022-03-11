package com.yz.toolscommon;

import com.yz.toolscommon.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ToolscommonApplicationTests {

    @Test
    void contextLoads() {
        String dtr = "8.20 pDh:/ “春天太浪漫了，我想请它吃顿饭，如果你有空也可以一起来”%来自春天的日常碎片   https://v.douyin.com/NjUxywq/ 复制此链接，打开Dou音搜索，直接观看视频！";
        String rex = "https([^}]*).+?\\s";
        List<String> stringRex = StringUtil.getStringRex(dtr, rex);

        System.out.println(stringRex);
    }

}
