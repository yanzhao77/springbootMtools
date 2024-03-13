package com.yz.oracle_inandoutdata.db;

import com.yz.oracle_inandoutdata.base.AuBase;
import com.yz.oracle_inandoutdata.entity.AuMinInOut;
import com.yz.oracle_inandoutdata.utils.AuCommUtil;
import com.yz.oracle_inandoutdata.utils.AuConstUtil;
import com.yz.oracle_inandoutdata.utils.AuPropertyUtil;
import io.micrometer.common.util.StringUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuDbUtil extends AuBase {

    public static Connection getDbConnection() {
        try {
            if (connection != null) {
                return connection;
            }
            dirver = AuPropertyUtil.getString("spring.datasource.driver-class-name");
            url = AuPropertyUtil.getString("spring.datasource.url");
//            username = AuPropertyUtil.getString("spring.datasource.username");
            password = AuPropertyUtil.getString("spring.datasource.password");
            schema = url.substring(url.lastIndexOf("@") + 1);

            Class.forName(dirver);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    
    /**
     * 
     * @param jobId
     * @return
     */
    public static List<AuMinInOut> getMinInOutFileInfos(String jobId, String stepId, String inOutFlg) {
        List<AuMinInOut> tMinInList = new ArrayList<>();
        Statement stat = null;
        ResultSet resu = null;
        String sql = null;
        boolean isStart = false;

        try {
            if (inOutFlg.equals(AuConstUtil.DATA_MODE_IN)) {
                sql = "select * from " + AuConstUtil.JOB_BASEINFO_VIEW + " where JOBID='" + jobId
                        + "' and NEWSTEPID='" + stepId + "' and MinIn='��'";
                isStart = true;
            } else {
                sql = "select * from " + AuConstUtil.JOB_BASEINFO_VIEW + " where JOBID='" + jobId
                        + "' and NEWSTEPID='" + stepId + "'  and (MIDDLEOUT='��' or MINOUT='��')";
            }

            connection = getDbConnection();
            stat = connection.createStatement();
            resu = stat.executeQuery(sql);
            AuMinInOut auMinInOut = null;
            String recLen = "";
            while (resu.next()) {
                auMinInOut = new AuMinInOut();
                auMinInOut.setJobPath(resu.getString("JOBPATH"));
                auMinInOut.setJobId(resu.getString("JOBID"));
                auMinInOut.setStepId(resu.getString("NEWSTEPID"));
                auMinInOut.setPgmId(resu.getString("PGMID"));
                auMinInOut.setIoMode(resu.getString("SSKB"));
                auMinInOut.setFileType(resu.getString("FILEKB"));
                auMinInOut.setLogicName(resu.getString("LOGICFILE"));
                auMinInOut.setRealName(resu.getString("REALFILE"));
                auMinInOut.setRecForm(resu.getString("RECFORM"));
                auMinInOut.setVol(resu.getString("VOL"));
                recLen = resu.getString("RECLEN_JCL");

                if (StringUtils.isNotBlank(recLen)) {
                    auMinInOut.setRecSize(Integer.parseInt(recLen));
                }

                if (isStart) {
                    auMinInOut.setMinInName(resu.getString("MININNAME"));
                } else {
                    String tOutData = resu.getString("MIDDLEOUTNAME");
                    if (!AuCommUtil.isBlank(tOutData)) {
                        auMinInOut.setMinOutName(resu.getString("MIDDLEOUTNAME"));
                    } else {
                        auMinInOut.setMinOutName(resu.getString("MINOUTNAME"));
                    }
                }

                tMinInList.add(auMinInOut);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resu != null) {
                    resu.close();
                    resu = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return tMinInList;
    }

    public static List<AuMinInOut> getOutFileInfos(String jobId, String stepId) {
        List<AuMinInOut> tMinInList = new ArrayList<>();
        Statement stat = null;
        ResultSet resu = null;
        String sql = null;
        try {
            sql = "select * from " + AuConstUtil.JOB_BASEINFO_VIEW + " where JOBID='" + jobId
                    + "' and NEWSTEPID='" + stepId + "' and (MIDDLEOUT='��' or MINOUT='��') and FILEKB='FILE'";
            connection = getDbConnection();
            stat = connection.createStatement();
            resu = stat.executeQuery(sql);
            AuMinInOut auMinInOut = null;
            String recLen = "";

            while (resu.next()) {
                auMinInOut = new AuMinInOut();
                auMinInOut.setJobPath(resu.getString("JOBPATH"));
                auMinInOut.setJobId(resu.getString("JOBID"));
                auMinInOut.setStepId(resu.getString("NEWSTEPID"));
                auMinInOut.setPgmId(resu.getString("PGMID"));
                auMinInOut.setIoMode(resu.getString("SSKB"));
                auMinInOut.setFileType(resu.getString("FILEKB"));
                auMinInOut.setLogicName(resu.getString("LOGICFILE"));
                auMinInOut.setRealName(resu.getString("REALFILE"));
                auMinInOut.setRecForm(resu.getString("RECFORM"));
                auMinInOut.setVol(resu.getString("VOL"));
                recLen = resu.getString("RECLEN_JCL");

                if (StringUtils.isNotBlank(recLen)) {
                    auMinInOut.setRecSize(Integer.parseInt(recLen));
                }

                String tOutData = resu.getString("MIDDLEOUTNAME");
                if (!AuCommUtil.isBlank(tOutData)) {
                    auMinInOut.setMinOutName(resu.getString("MIDDLEOUTNAME"));
                } else {
                    auMinInOut.setMinOutName(resu.getString("MINOUTNAME"));
                }

                tMinInList.add(auMinInOut);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (resu != null) {
                    resu.close();
                    resu = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return tMinInList;
    }

    public static String getColumnName(String tableName) {
        Statement statement = null;
        ResultSet rs = null;
        String resColNames = "";
        StringBuilder columnName = new StringBuilder();
        try {
            connection = getDbConnection();
            columnName.append("(");
            String sql = "select COLUMN_NAME,DATA_TYPE,DATA_LENGTH from user_tab_columns WHERE table_name ='"
                    + tableName.toUpperCase() + "' order by column_id";
            
            statement = connection.createStatement();
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                columnName.append("\"").append(rs.getString("COLUMN_NAME")).append("\"").append(",");
            }
            if(columnName.toString().endsWith(",")) {
                resColNames = columnName.toString().substring(0, columnName.toString().length()-1); 
            }
            
            resColNames = resColNames + ")";

        } catch (Exception e) {
            logger.error("�e�[�u��({})�̗���擾����ُ킪���������̂ŁA�m�F���������B(Error:{})", tableName, e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                    statement = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return resColNames.toString();
    }

    public static String getTableTrigger(String tableName) {
        Statement stat = null;
        ResultSet rs = null;
        String tableTrigger = null;
        try {
            String sql = "select trigger_name from user_triggers where table_name='" + tableName + "'";
            connection = getDbConnection();
            stat = connection.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                tableTrigger = rs.getString("trigger_name");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                    stat = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return tableTrigger;
    }
}
