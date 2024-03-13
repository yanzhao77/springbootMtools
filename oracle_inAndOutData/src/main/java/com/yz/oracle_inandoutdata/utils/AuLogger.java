package com.yz.oracle_inandoutdata.utils;


import com.yz.oracle_inandoutdata.base.AuBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuLogger extends AuBase {

    private File logFile;
    private File timeLogFile;
    private String tformat;
    private String tmessage;

    public AuLogger() {
        logFile = Paths.get(AuPathUtil.logFilePath()).toFile();
        if (!logFile.getParentFile().exists()) {
            logFile.getParentFile().mkdirs();
        }

        timeLogFile = Paths.get(AuPathUtil.timeLogPath()).toFile();
        if (!timeLogFile.getParentFile().exists()) {
            timeLogFile.getParentFile().mkdirs();
        }
    }

    public void error(String msg, Object... params) {
        output("ERROR", msg, params);
    }

    public void warn(String msg, Object... params) {
        output("WARN", msg, params);
    }

    public void info(String msg, Object... params) {
        output("INFO", msg, params);
    }

    private void output(String level, String msg, Object... params) {
        output(level, true, msg, params);
    }

    public void timeOut(String msg, Object... params) {
        timeOutput(msg, params);
    }

    private void output(String level, boolean showJobInfo, String msg, Object... params) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        String format = String.format("[%-10s]- ", sdf.format(new Date()));
        String message = String.format(msg.replaceAll("\\{\\}", "%s") + "\n", params);

        message = "[" + level + "] " + format + message;

        try {
            Files.write(logFile.toPath(), message.getBytes(AuConstUtil.SYSTEM_ENCODING), StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE, StandardOpenOption.APPEND);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void timeOutput(String msg, Object... params) {
        if (gStepName == null) {
            gStepName = "";
        }

        tformat = String.format("[%-10s]\t [%-10s]\t [%-10s]", tJobId, tStepName,
                AuCommUtil.isBlank(tStepId) ? " " : tStepId);
        tmessage = String.format(msg.replaceAll("\\{\\}", "%s") + "\n", params);
        tmessage = tformat + "\t" + tmessage;
        try {
            Files.write(timeLogFile.toPath(), (tmessage.toString()).getBytes(AuConstUtil.ENCODING_SYSTEM),
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
