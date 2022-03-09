package com.yz.securitydemo;

import com.yz.securitydemo.entity.Message;
import com.yz.securitydemo.entity.Users;
import com.yz.securitydemo.mapper.MessageMapper;
import com.yz.securitydemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = {SecurityDemoApplication.class})
@RunWith(SpringRunner.class)
class SecurityDemoApplicationTests {

    @Autowired
    MessageMapper messageMapper;

    @Test
    public void test() {
        List<Message> message = messageMapper.getMessage(11);

        System.out.println(message);
    }

}
