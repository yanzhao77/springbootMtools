package com.yz.oracle_inandoutdata.utils;

import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.db.AuDbUtil;
import com.yz.oracle_inandoutdata.entity.ConstUtil;


import java.io.*;
import java.nio.file.Path;

public class AuDbCtrlFileUtil extends AuBase {

    /**
     * CTRLファイルを作成する
     * 
     * @param path
     * @param tableName
     */
    public static void ctrlFileCreate(String dataPath, String tableName) {
        OutputStreamWriter write = null;
        File ctlFile = null;
        Path ctlPath = null;
        try {
            ctlPath = Path.of(resultPath, AuConstUtil.DBCTL, tableName + ".ctl");

            ctlFile = ctlPath.toFile();
            if (!ctlFile.getParentFile().exists()) {
                ctlFile.getParentFile().mkdirs();
            } else {
                if (ctlFile.exists()) {
                    ctlFile.delete();
                }
            }

            write = new OutputStreamWriter(new FileOutputStream(ctlFile, true), ConstUtil.ENCODING_SYSTEM);
            write.write("load data\n" + "characterset JA16SJIS\ninfile '" + dataPath.replaceAll("\\\\", "/") + "'\n" + "truncate\n"
                    + "into table " + tableName + "\nfields terminated by '&*&'" + "\ntrailing nullcols\n"
                    + AuDbUtil.getColumnName(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (write != null) {
                    write.close();
                    write = null;
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     * @param userName
     * @param password
     * @param dbName
     * @param tableName
     * @param outPath
     */
    public static void tableDataExport(String userName, String password, String dbName, String tableName,
            String outPath) {
        try {
            Process process = null;
            String executeCommand = null;

//          logger.info("expCommand:" + executeCommand);

            if (osName.startsWith("windows")) {
                executeCommand = "sqluldr264.exe " + userName + "/" + password + "@" + dbName + " query=\"select * from " + tableName
                        + "\" field=\"&*&\" charset=JA16SJIS file=" + Path.of(outPath,tableName+".csv").toString();
                process = Runtime.getRuntime().exec(executeCommand);
            } else {
                executeCommand = "sqluldr2 " + userName + "/" + password + "@" + dbName + " query=\"select "
                        + AuDbUtil.getColumnName(tableName) + " from " + tableName
                        + "\" field=\"&*&\" charset=JA16SJIS file=" + outPath;
                String tpPath = AuCommUtil.dbScript2Sh(executeCommand);
                String keigenSet = "chmod 755 " + tpPath;
                process = Runtime.getRuntime().exec(keigenSet);
                process.waitFor();
                process = Runtime.getRuntime().exec(tpPath);
            }

            InputStream is1 = process.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1, "MS932"));
            String line1;
            while ((line1 = br1.readLine()) != null) {
                if (!line1.trim().equals("")) {

                }
            }

            int exitValue = process.waitFor();
            if (exitValue == 0) {
                logger.info("テーブル({})にデータエクスポート成功。", tableName);
            } else {
                logger.info("テーブル({})にデータエクスポート失敗。", tableName);
            }

            InputStream is2 = process.getErrorStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2, "MS932"));
            String line2;
            while ((line2 = br2.readLine()) != null) {
                if (!line2.trim().contains("error")) {

                }
            }
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            logger.error("エクスポート失敗:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void tableDataImport(String userName, String userpass, String dbName,
            String tableName, String ctlFilePath) {
        try {
            Process process = null;
            String executeCommand = "sqlldr " + userName + "/" + userpass + "@" + dbName + " control=" + ctlFilePath
                    + File.separator + AuConstUtil.DBCTL + File.separator + tableName + ".ctl log=" + ctlFilePath
                    + File.separator + AuConstUtil.DBCTL + File.separator + tableName + ".log bad=" + ctlFilePath
                    + File.separator + AuConstUtil.DBCTL + File.separator + tableName
                    + ".bad errors=5000 rows=50000 parallel=true";
            logger.info("expCommand:" + executeCommand);

            if (osName.startsWith("windows")) {
                process = Runtime.getRuntime().exec(executeCommand);
            } else {
                String tpPath = AuCommUtil.dbScript2Sh(executeCommand);
                String keigenSet = "chmod 755 " + tpPath;
                process = Runtime.getRuntime().exec(keigenSet);
                process.waitFor();
                process = Runtime.getRuntime().exec(tpPath);
            }

            InputStream is1 = process.getInputStream();
            BufferedReader br1 = new BufferedReader(new InputStreamReader(is1, "MS932"));
            String line1;
            while ((line1 = br1.readLine()) != null) {
                if (!line1.trim().equals("")) {

                }
            }

            int exitValue = process.waitFor();
            if (exitValue == 0) {
                logger.info("テーブル({})にデータインポート成功。", tableName);
            } else {
                logger.info("テーブル({})にデータインポート失敗。", tableName);
            }

            InputStream is2 = process.getErrorStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is2, "MS932"));
            String line2;
            while ((line2 = br2.readLine()) != null) {
                if (!line2.trim().contains("error")) {
                }
            }
            process.waitFor();
            process.destroy();
        } catch (Exception e) {
            logger.error("インポート失敗:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
