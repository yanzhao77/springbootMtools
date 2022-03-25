package com.yz.postgresqlbackupdemo.Utils;

import com.yz.postgresqlbackupdemo.mapper.PostgreMapper;
import com.yz.toolscommon.FileUtil.FileUtils;
import com.yz.toolscommon.soft.bigfileSort.FileUtil;
import com.yz.toolscommon.utils.StringUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.postgresql.jdbc.PgConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

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
        try {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Connection conn = sqlSession.getConnection();
            ClassPathResource rc = new ClassPathResource("脚本.Sql", PostgreMapper.class);
            EncodedResource er = new EncodedResource(rc, "utf-8");
            ScriptUtils.executeSqlScript(conn, er);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
     * バックアップテーブル
     *
     * @param dbpath
     * @param dbname
     * @param dbUrl
     * @param username
     * @param tableName
     * @param backuppath
     * @param typeFile
     * @param fromUrl
     */
    @SneakyThrows
    public static void cpyToTap(String dbpath, String dbname, String dbUrl, String username, String tableName,
                                String backuppath, String typeFile, String fromUrl) {
        if ("0".equals(typeFile)) {
            FileUtil.copyFile(fromUrl, backuppath, true);
        } else {
            File file = new File(backuppath);
            if (!file.exists()) {
                file.mkdirs();
            }
            backuppath = backuppath + dbname + "." + tableName + ".backup";// バックアップを生成するファイル名

            Runtime rt = Runtime.getRuntime();// JVMの運用環境を得る
            StringBuffer cmdbuf = new StringBuffer();
            cmdbuf.append(dbpath); // データベース実行EXE
            cmdbuf.append(" --dbname=" + dbname);// データベース名
            cmdbuf.append(" --host=" + dbUrl);
            cmdbuf.append(" --username=" + username); // ユーザー名
            cmdbuf.append(" --no-password --data-only"); // テーブルのみのバックアップ
            cmdbuf.append(" --table=" + tableName);// バックアップされたテーブル名
            cmdbuf.append(" --inserts --column-inserts --encoding=UTF8 --disable-dollar-quoting"); // COPYコマンドではなくINSERTコマンドとしてデータをダンプし、列名付きINSERTコマンドとしてデータをダンプする
            cmdbuf.append(" --file=" + backuppath); // バックアップのパス

            try {
                // CMD呼び出し
                System.out.println(cmdbuf);
                Process process = rt.exec(cmdbuf.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * バックアップテーブル
     *
     * @param dbpath     psql.exe
     * @param dbname
     * @param dbUrl
     * @param username
     * @param tableName
     * @param backuppath scriptDir
     * @param typeFile
     * @param fromUrl
     */
    @SneakyThrows
    public static void fileRestoreTable(String dbpath, String dbname, String dbUrl, String username,
                                        String backupDirPath) {
        File backupDirfile = new File(backupDirPath);

        if (backupDirfile.isDirectory()) {
            List<File> fileList = new ArrayList<>();
            fileList = FileUtils.listFile(backupDirfile);

            try {
                for (File scriptFile : fileList) {
                    Runtime rt = Runtime.getRuntime();// JVMの運用環境を得る
                    StringBuffer cmdbuf = new StringBuffer();
                    cmdbuf.append(dbpath); // データベース実行EXE
                    cmdbuf.append(" --dbname=" + dbname);// データベース名
                    cmdbuf.append(" --host=" + dbUrl);
                    cmdbuf.append(" --username=" + username); // ユーザー名
                    cmdbuf.append(" --no-password"); // テーブルのみのバックアップ
                    cmdbuf.append(" --file=" + scriptFile.getPath()); // バックアップのパス
                    // CMD呼び出し
                    System.out.println(cmdbuf);
                    Process process = rt.exec(cmdbuf.toString());
                    byte[] readInputStream = readInputStream(process.getErrorStream());
                    System.out.println("Error コンテンツを印刷する：" + new String(readInputStream));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

}
