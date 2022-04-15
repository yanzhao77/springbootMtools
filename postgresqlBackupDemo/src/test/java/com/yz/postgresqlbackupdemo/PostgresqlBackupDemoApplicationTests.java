package com.yz.postgresqlbackupdemo;

import com.yz.postgresqlbackupdemo.Utils.DBUtils;
import com.yz.postgresqlbackupdemo.entity.Users;
import com.yz.postgresqlbackupdemo.mapper.PostgreMapper;
import com.yz.toolscommon.utils.StringUtil;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PostgresqlBackupDemoApplication.class) //AccountPlatApplication 为启动类
class PostgresqlBackupDemoApplicationTests {

    @Autowired
    PostgreMapper postgreService;

    @Autowired
    PostgreMapper postgreMapper;

    @Resource
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    DataSourceProperties dataSourceProperties;

    @Test
    void contextLoads() {
        /*Users allUser = postgreService.getAllUser();
        System.out.println(allUser);*/
        String url = "jdbc:postgresql://192.168.3.228:5432/test?characterEncodeing=utf8";
        String username = "postgres";
        String password = "root";
        String driver = "org.postgresql.Driver";
        String filename = "E:\\App\\Data\\sql\\_MenuInfo__202203221938.sql";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        try {

            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, username, password);
            if (conn instanceof PgConnection pgConnection) {
//                Map<String, String> parameterStatuses = pgConnection.getParameterStatuses();
//                String userName = pgConnection.getUserName();
//                String url1 = pgConnection.getURL();
                ScriptRunner runner = new ScriptRunner(connection);
                runner.setStopOnError(true);
                runner.runScript(new FileReader(filename));
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void fileTest() {
        String filePath = "E:\\App\\Data\\sql";

        List<File> fileList = new ArrayList<>();
        DBUtils.readFiles(new File(filePath), fileList);
        String rex = "(?<=\\.)\\w+(?=\\.backup)";
        for (File file : fileList) {
            List<String> stringRex = StringUtil.getStringRex(file.getName(), rex);
            if (stringRex.size() > 0) {
                postgreMapper.truncateTable(stringRex.get(0));
            }
        }
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Connection connection = sqlSession.getConnection();
        try {
            ScriptRunner runner = new ScriptRunner(connection);
            runner.setStopOnError(true);
            for (File file : fileList) {
                runner.runScript(new FileReader(file));
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    void mapperTest() {
//        Users allUser = postgreMapper.getAllUser();
//        System.out.println(allUser);
        try {

            SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection connection = sqlSession.getConnection();

            Statement statement = connection.createStatement();
            String sql = "select * from users  ORDER BY id";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void dbUrlTest() {

        try {

            SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection connection = sqlSession.getConnection();

            Statement statement = connection.createStatement();
            String sql = "select * from users  ORDER BY id";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println(resultSet);

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void dbtest() {
        System.out.println(111);
    }
}
