package com.yz.oracle_inandoutdata.utils;



import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.entity.AuJobStatus;
import com.yz.oracle_inandoutdata.entity.ConstUtil;

import java.io.File;
import java.nio.file.Path;

public class AuPathUtil extends AuBase {

    public static String getResultPath(AuJobStatus auJobStatus) {
        resultPath = Path
                .of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "testResult", auJobStatus.getStrongPoint(),
                        auJobStatus.getGanmeName(),
                        auJobStatus.getSubDir(), auJobStatus.getJobId())
                .toString();
        return creteFile(resultPath);
    }

    public static String getInDataPath(AuJobStatus auJobStatus) {
        return Path
                .of(System.getenv(ConstUtil.ENV_BATCH_ROOT_PATH), "test", "testData", auJobStatus.getStrongPoint(),
                        auJobStatus.getGanmeName(),
                        auJobStatus.getSubDir(), auJobStatus.getJobId())
                .toString();
    }

    // public static String getResultPath(String busindayId, String jobId) {
    // resultPath = Path.of(basePath, "test", "testResult", busindayId,
    // jobId).toString();
    // return creteFile(resultPath);
    // }

    public static String getMinInputBcakupPath() {
        String filePath = Path.of(resultPath, "01_MININPUT").toString();
        return creteFile(filePath);
    }

    public static String getMinOutPutBcakupPath() {
        String filePath = Path.of(resultPath, "10_MINOUTPUT").toString();
        return creteFile(filePath);
    }

    public static String compareNewPath(String stepId) {
        String filePath = Path.of(resultPath, "08_COMPARE", "comparenew", stepId).toString();
        return creteFile(filePath);
    }

    public static String compareOldPath(String stepId) {
        String filePath = Path.of(resultPath, "08_COMPARE", "compareold", stepId).toString();
        return creteFile(filePath);
    }

    public static String compareOkOldPath(String stepId) {
        String filePath = Path.of(resultPath, "02_OUTPUT", "backupOk", "old", stepId).toString();
        return creteFile(filePath);
    }

    public static String compareOkNewPath(String stepId) {
        String filePath = Path.of(resultPath, "02_OUTPUT", "backupOk", "new", stepId).toString();
        return creteFile(filePath);
    }

    public static String compareNgNewPath(String stepId) {
        String filePath = Path.of(resultPath, "02_OUTPUT", "backupNg", "new", stepId).toString();
        return creteFile(filePath);
    }

    public static String compareNgOldPath(String stepId) {
        String filePath = Path.of(resultPath, "02_OUTPUT", "backupNg", "old", stepId).toString();
        return creteFile(filePath);
    }

    public static String newStepOutBackupPath() {
        String filepath = Path.of(resultPath, "09_STEPINPUT", jobNet + "_" + gStepId, "new").toString();
        return creteFile(filepath);
    }

    public static String oldStepOutBackupPath() {
        String filepath = Path.of(resultPath, "09_STEPINPUT", jobNet + "_" + gStepId, "old").toString();
        return creteFile(filepath);
    }

    public static String minOutPath(String stepId) {
        String filePath = Path.of(resultPath, "10_MINOUTPUT", stepId).toString();
        return creteFile(filePath);
    }

    public static String logFilePath() {
        String filePath = Path.of(resultPath, "auTester.log").toString();
        return filePath;
    }

    public static String timeLogPath() {
        String filePath = Path.of(resultPath, "auTime.log").toString();
        return filePath;
    }

    public static String resultFile() {
        String filePath = Path.of(resultPath, "compareResult.csv").toString();
        return filePath;
    }

    public static Path getTriggerAddSqlPath(String tableName) {
        Path filePath = Path.of(resultPath, "11_SQL", "TRIGGER_ADD_" + tableName);
        creteFile(filePath.getParent().toString());
        return filePath;
    }

    public static Path getTriggerDelSqlPath(String tableName) {
        Path filePath = Path.of(resultPath, "11_SQL", "TRIGGER_DEL_" + tableName);
        creteFile(filePath.getParent().toString());
        return filePath;
    }

    public static String getLogPath() {
        String filepath = "";
        if (!AuCommUtil.isBlank(resultPath)) {
            filepath = creteFile(Path.of(resultPath, "04_LOG").toString());
        }
        return filepath;
    }

    /**
     * @param filePath
     * @return
     */
    public static String creteFile(String filePath) {
        File f = new File(filePath);
        if (!f.exists())
            f.mkdirs();
        return filePath;
    }
}
