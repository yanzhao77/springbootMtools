package com.yz.postgresqlbackupdemo.controller;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.sql.Connection;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/22 19:19
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    SqlSession session;

    @RequestMapping("/test")
    public String test() {
        if (null != session) {
            Connection connection = session.getConnection();
            System.out.println(connection);
            ScriptRunner runner = new ScriptRunner(connection);

            String filename = "E:\\App\\Data\\sql\\_MenuInfo__202203221938.sql";
            try {
                connection.commit();
                runner.setStopOnError(true);
                runner.runScript(new FileReader((filename)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "hello";
    }
}
