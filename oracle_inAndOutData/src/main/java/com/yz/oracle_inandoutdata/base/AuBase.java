package com.yz.oracle_inandoutdata.base;

import com.yz.oracle_inandoutdata.db.AuAssistDBUtil;
import com.yz.oracle_inandoutdata.entity.AuJobStatus;
import com.yz.oracle_inandoutdata.utils.AuLogger;

import java.sql.Connection;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class AuBase {
    protected static String gJobName;
    protected static String gStepId;
    protected static String gStepName;
    protected static String jobNet = "";
    protected static boolean sort = false;
    protected static String resultPath;
    protected static AuLogger logger;
    protected static Connection connection;
    protected static String osName;
    protected static String tJobId;
    protected static String tStepName;
    protected static String tStepId;
    protected static String dirver;
    protected static String url;
    protected static String username;
    protected static String password;
    protected static String schema;
    protected Instant startTime;
    protected Instant endTime;
    protected static boolean isCompData;
    protected static Map<String, Integer> compFileInfoMaps;
    protected static AuAssistDBUtil auAssistDBUtil = null;
    protected static AuJobStatus gAuJobStatus = null;
    protected static List<AuJobStatus> gAuJobStatusList = null;
    protected static Map<String,String> tAuJobStatus = null;
    protected static Map<String,String> tableNameMappingMaps = null;
    protected static Map<String,List<AuJobStatus>> JobDataPathMappingMaps = null;
    protected static Map<String,List<String>> multiTableMaps = null;
    protected static String operatorUser = null;
    protected static int stepMaxWaitTime = 0;
    protected static Map<String,String> consoleMappingMaps = null;
}
