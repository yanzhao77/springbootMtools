package com.yz.oracle_inandoutdata.entity;

import lombok.Data;

@Data
public class AuMinInOut {
    private String jobPath = "";
    private String jobId = "";// �W���uID
    private String stepId = "";// �X�e�b�vID
    private String pgmId = "";// �v���O����ID
    private String ioMode = "";// IO���[�h
    private String fileType = "";// �t�@�C���^�C�v(DB,FILE)
    private String logicName = "";// ���W�b�N��
    private String realName = "";// �t�@�C����
    private Integer recSize = 0;// ���R�[�h�T�C�Y
    private String recForm = "";// ���R�[�h�t�H�[�}�b�g
    private String minInName = "";
    private String minOutName = "";
    private String dev = "";// ���u/�@�햼
    private String vol = "";// �{�����[���ԍ�
    private boolean report;
    private String dirName = "";
    private String swrealFile = "";
    private String exceptValue = "";
    private String diffNo = "";
}
