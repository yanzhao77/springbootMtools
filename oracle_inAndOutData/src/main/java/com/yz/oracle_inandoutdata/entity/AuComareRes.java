package com.yz.oracle_inandoutdata.entity;

import lombok.Data;

@Data
public class AuComareRes {
    private String dataPath;
    private String jobId;
    private String stepId;
    private String pgmId;
    private String fileName;
    private String dataType;
    private String dataMode;
    private String exceptField;
    private String compResult;
}
