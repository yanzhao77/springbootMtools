package com.yz.oracle_inandoutdata.entity;

import lombok.Data;

@Data
public class AuMinInOut {
    private String jobPath = "";
    private String jobId = "";// ジョブID
    private String stepId = "";// ステップID
    private String pgmId = "";// プログラムID
    private String ioMode = "";// IOモード
    private String fileType = "";// ファイルタイプ(DB,FILE)
    private String logicName = "";// ロジック名
    private String realName = "";// ファイル名
    private Integer recSize = 0;// レコードサイズ
    private String recForm = "";// レコードフォーマット
    private String minInName = "";
    private String minOutName = "";
    private String dev = "";// 装置/機種名
    private String vol = "";// ボリューム番号
    private boolean report;
    private String dirName = "";
    private String swrealFile = "";
    private String exceptValue = "";
    private String diffNo = "";
}
