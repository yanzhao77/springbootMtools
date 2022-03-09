package com.yz.securitydemo.mapper;

import com.yz.securitydemo.SecurityDemoApplication;
import com.yz.securitydemo.entity.Message;
import com.yz.securitydemo.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/7 15:40
 */
@SpringBootTest(classes = {SecurityDemoApplication.class})
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void getAllUser() {
        List<Users> allUser = userMapper.getAllUser();

        System.out.println(allUser);
    }

    @Test
    void loadUserByUsername() {
        String name="zhangsan";
        Users users = userMapper.loadUserByUsername(name);
        System.out.println(users.toString());
    }
}