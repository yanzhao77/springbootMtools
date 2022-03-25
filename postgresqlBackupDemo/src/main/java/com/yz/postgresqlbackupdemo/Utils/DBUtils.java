package com.yz.postgresqlbackupdemo.Utils;

import com.yz.postgresqlbackupdemo.mapper.PostgreMapper;
import com.yz.toolscommon.utils.StringUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.PushBuilder;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author yanzhao
 * @version 1.0
 * TODO
 * @date 2022/3/22 16:41
 */
public class DBUtils {
    @Autowired
    PostgreMapper postgreMapper;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    static String HOST = "";
    static String USERNAME = "";
    static String DBNAME = "";

    /**
     * 执行sql脚本文件 使用Spring工具类
     */
    public void runSqlBySpringUtils() throws Exception {
       /* try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection conn = sqlSession.getConnection();
            ClassPathResource rc = new ClassPathResource("脚本.Sql", PostgreMapper.class);
            EncodedResource er = new EncodedResource(rc, "utf-8");
            ScriptUtils.executeSqlScript(conn, er);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }*/
    }

    private static void mybatisExec() throws ClassNotFoundException, SQLException {
    /*    Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        ScriptRunner runner = new ScriptRunner(conn);
        try {
            runner.setStopOnError(true);
            runner.runScript(new FileReader(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();*/
    }

    public void clearTable(String tableName) {
        postgreMapper.truncateTable(tableName);
    }

    /**
     * @param dirFile 需要恢复的sql脚本文件夹
     *                kpp3.alkdonl.xcoc.backup
     */
    public void restoreForSqlFile(String dirFile) {
        List<File> fileList = new ArrayList<>();
        readFiles(new File(dirFile), fileList);
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

    public static List<File> readFiles(File dirFile, List<File> fileList) {
        if (dirFile.isDirectory()) {
            for (File newFile : Objects.requireNonNull(dirFile.listFiles())) {
                readFiles(newFile, fileList);
            }
        } else if (dirFile.isFile()) {
            fileList.add(dirFile);
        }
        return fileList;
    }


    /**
     * @param objName    备份对象
     * @param dbpath     对象所在的library
     * @param backuppath 对象要备份的savf文件
     * @param isClear
     * @param isDtaCpr
     * @return
     */
    public static boolean backupObjAct(String objName, String dbpath, String backuppath) {
        StringBuffer cmdbuf = new StringBuffer();
        cmdbuf.append("cmd /c pg_dump");
        cmdbuf.append(" -h " + HOST); // 主机
        cmdbuf.append(" -U " + USERNAME); // 用户
        cmdbuf.append(" -d " + DBNAME); // 数据库
        cmdbuf.append(" -Ft ");
        cmdbuf.append(" --column-inserts "); // --column-inserts:以INSERT命令转存数据
//		cmdbuf.append(" -a "); // 只输出数据
        cmdbuf.append(" -c "); // 输出数据包含删表语句 drop table
        cmdbuf.append(" -b "); // --blobs 在转储中包括大对象
        cmdbuf.append(" -t " + dbpath + "." + objName); // 输出文件或目录名 -t指定表
        cmdbuf.append(" -f " + backuppath + "\\" + objName + ".tar");
        //cmdbuf.append("-E utf8 "); // 编码
//        backupAct(cmdbuf.toString());

        return true;
    }

}
