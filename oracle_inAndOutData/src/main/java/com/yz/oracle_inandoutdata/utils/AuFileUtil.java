package com.yz.oracle_inandoutdata.utils;

import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.db.AuDbUtil;
import com.yz.oracle_inandoutdata.entity.AuJobStatus;
import com.yz.oracle_inandoutdata.entity.AuMinInOut;


import com.yz.oracle_inandoutdata.entity.ConstUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.aspectj.util.FileUtil;
import org.jsoup.helper.StringUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AuFileUtil extends AuBase {

    public static void jobMinInDataImp(String jobId, List<AuMinInOut> minInOutList) throws IOException {
        if (minInOutList != null && minInOutList.size() > 0) {
            logger.info("ジョブ({})の最小入力データ投入開始", jobId);
            String fromPath = null;
            String toPath = null;
            List<String> multiTableList = null;
            for (AuMinInOut auMinInOut : minInOutList) {
                if (StringUtil.isBlank(auMinInOut.getMinInName())) {
                    continue;
                }
                // データパス
                fromPath = Path.of(AuPathUtil.getInDataPath(gAuJobStatus),
                        AuFileUtil.getPickDataName(auMinInOut.getMinInName())).toString();

                // 目標パス
                if (AuCommUtil.isTable(auMinInOut)) {
                    multiTableList = multiTableMaps.get(auMinInOut.getRealName());
                    if (multiTableList == null) {
                        if (!new File(fromPath).exists()) {
                            logger.warn("{}が存在しないので、確認してください。", fromPath);
                            continue;
                        }
                    }
                    toPath = auMinInOut.getRealName();
                }

                // バックアップパス
                String backPath = Path.of(AuPathUtil.getMinInputBcakupPath()).resolve(auMinInOut.getRealName())
                        .toString();
                // String tFromPath = fromPath;
                // String tToPath = toPath;
                // ExecutorService poolfb = Executors.newFixedThreadPool(2);
                // poolfb.submit(() -> {
                // AA最小入力データ投入
                if (AuCommUtil.isTable(auMinInOut)) {
                    if (multiTableList == null) {
                        String newTableName = tableNameMappingMaps.get(toPath);
                        if (StringUtils.isBlank(newTableName)) {
                            newTableName = toPath;
                        }
                        AuDbCtrlFileUtil.ctrlFileCreate(fromPath, newTableName);
                        ScriptRunner runner = new ScriptRunner(connection);
                        boolean isTrigger = beforeTableSeili(newTableName);
                        if (isTrigger) {
                            AuCommUtil.fkdel(runner, AuPathUtil.getTriggerDelSqlPath(newTableName));
                        }
                        AuDbCtrlFileUtil.tableDataImport(username, password, schema, newTableName, resultPath);
                        if (isTrigger) {
                            AuCommUtil.fkdel(runner, AuPathUtil.getTriggerAddSqlPath(newTableName));
                        }
                        logger.info("fromPath：{},backPath：{}", fromPath, backPath);
                        backPath = Path.of(AuPathUtil.getMinInputBcakupPath()).resolve(newTableName)
                                .toString();
                        FileUtil.copyFile(Path.of(fromPath).toFile(), Path.of(backPath).toFile());
                    } else {
                        for (String tableName : multiTableList) {
                            String newTableName = tableNameMappingMaps.get(tableName);
                            if (StringUtils.isBlank(newTableName)) {
                                newTableName = tableName;
                            }
                            // データパス
                            fromPath = Path.of(AuPathUtil.getInDataPath(gAuJobStatus),
                                    AuFileUtil.getPickDataName(auMinInOut.getMinInName()) + "_" + tableName).toString();

                            AuDbCtrlFileUtil.ctrlFileCreate(fromPath, newTableName);
                            ScriptRunner runner = new ScriptRunner(connection);
                            beforeTableSeili(newTableName);
                            AuCommUtil.fkdel(runner, AuPathUtil.getTriggerDelSqlPath(newTableName));
                            AuDbCtrlFileUtil.tableDataImport(username, password, schema, newTableName, resultPath);
                            AuCommUtil.fkdel(runner, AuPathUtil.getTriggerAddSqlPath(newTableName));
                            logger.info("fromPath：{},backPath：{}", fromPath, backPath);
                            backPath = Path.of(AuPathUtil.getMinInputBcakupPath()).resolve(tableName)
                                    .toString();
                            FileUtil.copyFile(Path.of(fromPath).toFile(), Path.of(backPath).toFile());
                        }
                    }
                } else {
                    try {
                        File tToFile = new File(toPath) ;
                        if(tToFile.exists()) {
                            logger.info("該当ファイル({})が存在しているので、投入しない", toPath);
                            return ;
                        }
                        logger.info("fromPath：{},toPath：{}", fromPath, toPath);
                        FileUtil.copyFile(Path.of(fromPath).toFile(), Path.of(toPath).toFile());
                        logger.info("fromPath：{},backPath：{}", fromPath, backPath);
                        FileUtil.copyFile(Path.of(fromPath).toFile(), Path.of(backPath).toFile());
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                // });

                // poolfb.submit(() -> {
                // logger.info("fromPath：{},backPath：{}", tFromPath, backPath);
                // // AA最小入力データバックアップ
                // try {
                // FileUtil.copyFile(Path.of(tFromPath).toFile(),
                // Path.of(backPath).toFile());
                // } catch (IOException e) {
                // // TODO Auto-generated catch block
                // e.printStackTrace();
                // }
                // });

                // try {
                // poolfb.shutdown();
                // poolfb.awaitTermination(1, TimeUnit.HOURS);
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // logger.error("異常情報：", e.getMessage());
                // }
            }
            logger.info("ジョブ({})の最小入力データ投入終了", jobId);
        } else {
            logger.warn("ジョブ({})が最小入力データない", jobId);
        }
    }



    public static void jobMinOutDataExp(String jobId, List<AuMinInOut> minInOutList) {
        if (minInOutList != null && minInOutList.size() > 0) {
            logger.info("ジョブ({})の最小出力データ導出開始", jobId);
            File fromOldFile = null;
            File fromNewFile = null;
            File toOldFile = null;
            File toNewFile = null;
            boolean newExistFlg = true;
            boolean oldExistFlg = true;
            List<AuMinInOut> compDataList = new ArrayList<>();

            for (AuMinInOut auMinInOut : minInOutList) {
                if (StringUtil.isBlank(auMinInOut.getMinOutName())) {
                    continue;
                }
                newExistFlg = true;
                oldExistFlg = true;

                // データパス
                fromOldFile = Path.of(AuPathUtil.getInDataPath(gAuJobStatus),
                        AuFileUtil.getPickDataName(auMinInOut.getMinOutName())).toFile();

                if (AuCommUtil.isTable(auMinInOut)) {
                    if (!fromOldFile.exists()) {
                        logger.warn("既存データ({})が存在しない", fromOldFile.getAbsolutePath());
                        // continue;
                    } else {
                        String newTableName = tableNameMappingMaps.get(auMinInOut.getRealName());
                        if (StringUtils.isBlank(newTableName)) {
                            newTableName = auMinInOut.getRealName();
                        }
                        AuDbCtrlFileUtil.ctrlFileCreate(fromOldFile.getAbsolutePath(), newTableName + "_COMPARE");
                        AuDbCtrlFileUtil.tableDataImport(username, password, schema, newTableName + "_COMPARE",
                                resultPath);
                        auMinInOut.setRealName(newTableName);
                        coptCreate(auMinInOut);
                        compDataList.add(auMinInOut);
                    }
                }
            }
            logger.info("ジョブ({})の最小出力データ導出終了", jobId);


        }
    }

    private static void coptCreate(AuMinInOut auMinInOut) {
        StringBuilder coptLine = new StringBuilder();
        coptLine.append("dbConfig=" + System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH).replaceAll("\\\\", "/") + "\n");

        int size = 1024;
        coptLine.append("maxFileSize=" + String.valueOf(size).replaceAll("\\\\", "/") + "\n");
        if (!AuCommUtil.isTable(auMinInOut)) {
            coptLine.append("isTable=" + "0" + "\n");
            coptLine.append("file1="
                    + Path.of(AuPathUtil.compareNewPath(auMinInOut.getStepId()), auMinInOut.getRealName()).toString()
                            .replaceAll("\\\\", "/")
                    + "\n");
            coptLine.append("file2="
                    + Path.of(AuPathUtil.compareOldPath(auMinInOut.getStepId()), auMinInOut.getRealName()).toString()
                            .replaceAll("\\\\", "/")
                    + "\n");

            if (AuCommUtil.isBlank(auMinInOut.getRecForm()) && !auMinInOut.getRecForm().equals("-")) {
                coptLine.append("recForm=" + auMinInOut.getRecForm() + "\n");
            } else {
                coptLine.append("recForm=" + "" + "\n");
            }

            coptLine.append("recSize=" + auMinInOut.getRecSize() + "\n");

            if (AuCommUtil.isBlank(auMinInOut.getExceptValue())) {
                coptLine.append("exceptKey=" + auMinInOut.getExceptValue() + "\n");
            } else {
                coptLine.append("exceptKey=" + "" + "\n");
            }

            if (AuCommUtil.isBlank(auMinInOut.getDiffNo())) {
                coptLine.append("diffNo=" + auMinInOut.getDiffNo() + "\n");
            } else {
                coptLine.append("diffNo=" + "" + "\n");
            }

            coptLine.append("resultPath="
                    + resultPath.toString().replaceAll("\\\\", "/")
                    + "\n");
            coptLine.append("stepName=" + auMinInOut.getStepId() + "\n");
            File coptFile = Path
                    .of(AuPathUtil.compareNewPath(auMinInOut.getStepId()), auMinInOut.getRealName() + "_copt").toFile();
            fileOut(coptLine.toString(), coptFile);
        } else {
            coptLine.append("isTable=" + "1" + "\n");
            coptLine.append("view1=" + username + "." + auMinInOut.getRealName() + "\n");
            coptLine.append("view2=" + username + "." + auMinInOut.getRealName() + "_COMPARE" + "\n");
            coptLine.append("exceptKey=" + auMinInOut.getExceptValue() + "\n");
            coptLine.append("resultPath="
                    + resultPath.toString().replaceAll("\\\\", "/") + "\n");
            coptLine.append("stepName=" + auMinInOut.getStepId() + "\n");
            File coptFile = Path
                    .of(AuPathUtil.compareNewPath(auMinInOut.getStepId()), auMinInOut.getRealName() + "_copt").toFile();
            fileOut(coptLine.toString(), coptFile);
        }
    }

    private static void fileOut(String context, File coptFile) {
        BufferedWriter writer = null;
        try {
            if (coptFile.exists()) {
                coptFile.delete();
            }
            coptFile.createNewFile();
            writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(coptFile, true), ConstUtil.ENCODING_SYSTEM));
            writer.write(context + "\n");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // public static void dataCompare(List<AuMinInOut> compDataList) {
    // Process pro = null;
    // String sb = "";
    // if (compDataList == null || compDataList.size() == 0) {
    // logger.error("最小出力データがならないので、比較できない");
    // return;
    // }
    // logger.info("データ比較開始");
    // AuComareRes fileResult = null;
    // List<AuComareRes> csvlist = new ArrayList<>();
    // for (AuMinInOut auMinInOut : compDataList) {
    // logger.info("({})比較開始実行", auMinInOut.getRealName());
    // fileResult = new AuComareRes();
    // fileResult.setJobId(auMinInOut.getJobId());
    // fileResult.setStepId(auMinInOut.getStepId());
    // fileResult.setPgmId(auMinInOut.getPgmId());
    // fileResult.setFileName(auMinInOut.getRealName());
    // fileResult.setDataType(auMinInOut.getFileType());
    // fileResult.setExceptField(auMinInOut.getExceptValue());
    //
    // String cmd = "";
    // String path = "--opt="
    // + Path.of(AuPathUtil.compareNewPath(auMinInOut.getStepId()),
    // auMinInOut.getRealName() + "_copt");
    // cmd = "java -jar " + Path
    // .of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "lib",
    // "Compare-0.0.1-SNAPSHOT.jar")
    // .toString()
    // + " " + path;
    // try {
    // pro = Runtime.getRuntime().exec(cmd);
    // sb = AuCommUtil.inStreamRead(pro);
    // pro.waitFor();
    // pro.destroy();
    // String result = "";
    // sort = false;
    // result = AuFileUtil.cpResult(sb, auMinInOut.getRealName(),
    // auMinInOut.getJobId(),
    // auMinInOut.getStepId(), auMinInOut.getFileType());
    // if (AuCommUtil.isBlank(result)) {
    // System.out.println("result:" + result);
    // continue;
    // }
    // if (sb.contains("EXPECTKEY=")) {
    // String key = sb.substring(sb.lastIndexOf("EXPECTKEY="),
    // sb.lastIndexOf(";\n")).replace("EXPECTKEY=",
    // "");
    // if (key.equals("null")) {
    // fileResult.setExceptField("-");
    // } else {
    // fileResult.setExceptField(key.replace(",", "\t"));
    // }
    // }
    // if (result.equals("true")) {
    // if (sort) {
    // fileResult.setCompResult("SORTOK");
    // } else {
    // fileResult.setCompResult("OK");
    // }
    // } else if (result.equals("false")) {
    // if (sort) {
    // fileResult.setCompResult("SORTNG");
    // } else {
    // fileResult.setCompResult("NG");
    // }
    // // if (AuCommUtil.isTable(auMinInOut)) {
    // // tableDataOut(auMinInOut.getRealName(),
    // // auMinInOut.getStepId(), auMinInOut.getExceptValue());
    // // }
    // } else if (result.equals("error")) {
    // fileResult.setCompResult("NG(実行エラー)");
    // } else if (result.equals("-")) {
    // fileResult.setCompResult("NG(FILEのサイズ差異大)");
    // }
    // } catch (Exception e) {
    // logger.error("比較異常({})", e.getMessage());
    // }
    // csvlist.add(fileResult);
    // logger.info("比較実行完了");
    // }
    //
    // AuJobReport.create().printResult(csvlist);
    // }

    // private static String cpResult(String message, String filename, String
    // shellId, String stepId, String dataType) {
    // String res = null;
    //
    // if (message.toString().contains("SORTOK!!!") ||
    // message.toString().contains("SORTNG!!!")) {
    // if (message.toString().contains("SORTOK!!!")) {
    // res = "true";
    // sort = true;
    // compareResBackup("ok", filename, shellId, stepId, dataType);
    // } else if (message.toString().contains("SORTNG!!!")) {
    // res = "false";
    // sort = true;
    // compareResBackup("ng", filename, shellId, stepId, dataType);
    // }
    // } else {
    // if (message.toString().contains("OK!!!")) {
    // res = "true";
    // compareResBackup("ok", filename, shellId, stepId, dataType);
    // } else if (message.toString().contains("NG!!!")) {
    // res = "false";
    // compareResBackup("ng", filename, shellId, stepId, dataType);
    // } else if (message.toString().contains("result -!!!")) {
    // res = "-";
    // compareResBackup("ng", filename, shellId, stepId, dataType);
    // } else if (message.toString().contains("ERROR!!!")) {
    // res = "error";
    // compareResBackup("ng", filename, shellId, stepId, dataType);
    // }
    // }
    // return res;
    // }
    //
    // public static void compareResBackup(String result, String filename,
    // String shellId, String stepId,
    // String dataType) {
    // String fromPathnew;
    // String fromPathold;
    // String tonew = null;
    // String toold = null;
    // try {
    // if (!dataType.equals(AuConstUtil.DATA_TYPE_DB)) {
    // fromPathnew = Path.of(AuPathUtil.compareNewPath(stepId),
    // filename).toString();
    // fromPathold = Path.of(AuPathUtil.compareOldPath(stepId),
    // filename).toString();
    // if (result.equals("ok")) {
    // tonew = Path.of(AuPathUtil.compareOkNewPath(stepId),
    // filename).toString();
    // toold = Path.of(AuPathUtil.compareOkOldPath(stepId),
    // filename).toString();
    //
    // FileUtils.copyFile(new File(fromPathnew), new File(tonew));
    // FileUtils.copyFile(new File(fromPathold), new File(toold));
    // } else if (result.equals("ng")) {
    // tonew = Path.of(AuPathUtil.compareNgNewPath(stepId),
    // filename).toString();
    // toold = Path.of(AuPathUtil.compareNgOldPath(stepId),
    // filename).toString();
    //
    // FileUtils.copyFile(new File(fromPathnew), new File(tonew));
    // FileUtils.copyFile(new File(fromPathold), new File(toold));
    // }
    // String toBacknew = Path.of(AuPathUtil.minOutPath(stepId),
    // filename).toString();
    // FileUtils.copyFile(new File(fromPathnew), new File(toBacknew));
    // } else {
    // if (result.equals("ok")) {
    // tonew = Path.of(AuPathUtil.compareOkNewPath(stepId)).toString();
    // toold = Path.of(AuPathUtil.compareOkOldPath(stepId)).toString();
    // } else if (result.equals("ng")) {
    // tonew = Path.of(AuPathUtil.compareNgNewPath(stepId)).toString();
    // toold = Path.of(AuPathUtil.compareNgOldPath(stepId)).toString();
    // }
    // tableDataOut(filename, stepId, toold, tonew);
    // String toBacknew = Path.of(AuPathUtil.minOutPath(stepId),
    // filename).toString();
    // FileUtils.copyFile(Path.of(tonew, filename).toFile(), new
    // File(toBacknew));
    // }
    // } catch (Exception e) {
    // // TODO: handle exception
    // }
    // }

    public static void getTableMappings() {

        if (tableNameMappingMaps == null) {
            tableNameMappingMaps = new HashMap<>();

            Path inPath = Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "config", "TableNameMapping");
            try {
                List<String> lines = Files.readAllLines(inPath, AuConstUtil.ENCODING_SYSTEM);
                if (lines.size() > 0) {
                    String[] tArrs = null;
                    for (String line : lines) {
                        if (StringUtils.isNoneBlank(line.trim())) {
                            tArrs = line.split("\t");
                            tableNameMappingMaps.put(tArrs[0], tArrs[1]);
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void getJobDataPathMappings() {
        if (JobDataPathMappingMaps == null) {
            JobDataPathMappingMaps = new HashMap<>();
            try {
                Path inPath = Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "config",
                        "JobDataPathMapping");
                List<String> lines = Files.readAllLines(inPath, AuConstUtil.ENCODING_SYSTEM);

                if (lines.size() > 0) {
                    List<AuJobStatus> tAuJobStatusList = null;
                    AuJobStatus tAuJobStatus = null;
                    String[] tArrs = null;
                    for (String line : lines) {
                        if (StringUtils.isNoneBlank(line.trim())) {
                            tArrs = line.split("\t");
                            tAuJobStatusList = JobDataPathMappingMaps.get(tArrs[0]);
                            if (tAuJobStatusList == null) {
                                tAuJobStatusList = new ArrayList<>();
                                JobDataPathMappingMaps.put(tArrs[0], tAuJobStatusList);
                            }
                            tAuJobStatus = new AuJobStatus();
                            tAuJobStatus.setJobId(tArrs[0]);
                            tAuJobStatus.setStrongPoint(tArrs[1]);
                            tAuJobStatus.setGanmeName(tArrs[2]);
                            tAuJobStatus.setSubDir(tArrs[3]);
                            tAuJobStatusList.add(tAuJobStatus);
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void getTableMultiInfo() {
        if (multiTableMaps == null) {
            multiTableMaps = new HashMap<>();

            Path inPath = Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "config", "MultiTableInfo");
            try {
                List<String> lines = Files.readAllLines(inPath, AuConstUtil.ENCODING_SYSTEM);
                if (lines.size() > 0) {
                    String[] tArrs = null;
                    for (String line : lines) {
                        if (StringUtils.isNoneBlank(line.trim())) {
                            tArrs = line.split("\t");
                            multiTableMaps.put(tArrs[0], Arrays.asList(tArrs[1].split(",")));
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void getConsoleMappings() {
        if (consoleMappingMaps == null) {
            consoleMappingMaps = new HashMap<>();

            Path inPath = Path.of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "config", "ConsoleMapping");
            try {
                List<String> lines = Files.readAllLines(inPath, AuConstUtil.ENCODING_SYSTEM);
                if (lines.size() > 0) {
                    String[] tArrs = null;
                    for (String line : lines) {
                        if (StringUtils.isNoneBlank(line.trim())) {
                            tArrs = line.split("\t");
                            consoleMappingMaps.put(tArrs[0], tArrs[1]);
                        }
                    }
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * @param dataName
     * @return
     */
    public static String getPickDataName(String dataName) {
        return dataName + ".E";
    }

    private static boolean beforeTableSeili(String tableName) {
        try {
            String triggerName = AuDbUtil.getTableTrigger(tableName);
            if (AuCommUtil.isBlank(triggerName)) {
                return false;
            }
            String str01 = "ALTER TRIGGER " + triggerName + " ENABLE;";
            String str02 = "ALTER TRIGGER " + triggerName + " DISABLE;";
            Files.writeString(AuPathUtil.getTriggerAddSqlPath(tableName), str01, AuConstUtil.ENCODING_SYSTEM,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            Files.writeString(AuPathUtil.getTriggerDelSqlPath(tableName), str02, AuConstUtil.ENCODING_SYSTEM,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    // private static void tableDataOut(String tableName, String stepId, String
    // oldOutPath, String newOutPath) {
    // logger.info("DBデータ出力開始...");
    // String tableColumns = AuDbUtil.getColumnName(tableName);
    // if (!tableColumns.isEmpty()) {
    // // ExecutorService poolSeili = Executors.newFixedThreadPool(2);
    // //// 既存FILE処理
    // // poolSeili.submit(() -> {
    // try {
    // tableDataOutTxt(username, password, schema, tableName + "_COMPARE",
    // oldOutPath, tableColumns);
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // }
    // // });
    //
    // // 移行後FILE処理
    // // poolSeili.submit(() -> {
    // try {
    // tableDataOutTxt(username, password, schema, tableName, newOutPath,
    // tableColumns);
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // }
    // // });
    //
    // // try {
    // // poolSeili.shutdown();
    // // poolSeili.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    // // } catch (InterruptedException e) {
    // // e.printStackTrace();
    // // }
    // logger.info("DBデータ出力終了...");
    // }
    // }
    //
    // public static void tableDataOutTxt(String userName, String password,
    // String dbName, String tableName,
    // String outPath, String tableColumns) {
    // String expCommand = null;
    // Process process = null;
    // String tTableName = null;
    // try {
    // if(tableName.endsWith("_COMPARE")) {
    // tTableName= tableName.replace("_COMPARE", "");
    // } else {
    // tTableName = tableName;
    // }
    //
    // if (osName.startsWith("windows")) {
    // expCommand = "sqluldr264.exe " + userName + "/" + password + "@" + dbName
    // + " query=\"select " + "*"
    // + " from " + tableName + "\" field=\"&*&\" charset=JA16SJIS file="
    // + Path.of(outPath, tTableName).toString();
    // logger.info("expCommand:" + expCommand);
    // process = Runtime.getRuntime().exec(expCommand);
    // } else {
    // expCommand = "sqluldr2 " + userName + "/" + password + "@" + dbName + "
    // query=\"select " + "*"
    // + " from " + tableName + "\" field=\"&*&\" charset=JA16SJIS file="
    // + Path.of(outPath, tableName).toString();
    // logger.info("expCommand:" + expCommand);
    // String tpPath = AuCommUtil.dbScript2Sh(expCommand);
    // String keigenSet = "chmod 755 " + tpPath;
    // process = Runtime.getRuntime().exec(keigenSet);
    //
    // process.waitFor();
    // process = Runtime.getRuntime().exec(tpPath);
    // }
    // InputStream is1 = process.getInputStream();
    // BufferedReader br1 = new BufferedReader(new InputStreamReader(is1,
    // "MS932"));
    // String line1;
    // while ((line1 = br1.readLine()) != null) {
    // if (!line1.trim().equals("")) {
    // // System.out.println(line1);
    // }
    // }
    // InputStream is2 = process.getErrorStream();
    // BufferedReader br2 = new BufferedReader(new InputStreamReader(is2,
    // "MS932"));
    // String line2;
    // while ((line2 = br2.readLine()) != null) {
    // if (!line2.trim().contains("error")) {
    // logger.error(line2);
    // }
    // }
    // process.waitFor();
    // process.destroy();
    // logger.info("テーブル({})エクスポート", tableName);
    // } catch (Exception e) {
    // System.out.println("エクスポート失敗:" + e.getMessage());
    // e.printStackTrace();
    // }
    // }

    private static void afterTableSeili(String tableName) {

    }
    

    public static String getLibKey(AuMinInOut fileInfo) {
        String libVal = AuLibPropertyUtil.getLibList("LIBL_" + operatorUser);
        if (AuCommUtil.isBlank(libVal)) {
            return "";
        } else {
            return libVal;
        }
    }

    public static AuJobStatus getAuJobStatus(List<AuJobStatus> auJobStatusList) {
        if (auJobStatusList != null && auJobStatusList.size() > 0) {
            for (AuJobStatus auJobStatus : auJobStatusList) {
                if (AuCommUtil.isBlank(auJobStatus.getRunStatus())) {
                    auJobStatus.setRunStatus(AuConstUtil.JOB_STATUS_RUNNING);
                    return auJobStatus;
                }
            }
        }
        return null;
    }
    
    
    public static boolean delFies(String path) {

        File file = new File(path);
        if (!file.exists())
            return false;

        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                if (childFile == null)
                    continue;
                if (childFile.isFile()) {
                    childFile.delete();
                } else if (childFile.isDirectory()) {
                    delFolder(childFile.getAbsolutePath());
                }
            }
        }
        return true;
    }

    public static void delFolder(String path) {
        File file = new File(path);
        if (!file.exists())
            return;

        if (file.isDirectory()) {
            for (File childFile : file.listFiles()) {
                if (childFile == null)
                    continue;
                if (childFile.isDirectory()) {
                    delFolder(childFile.getAbsolutePath());
                } else {
                    childFile.delete();
                }
            }
        } else {
            file.delete();
        }
    }
}
