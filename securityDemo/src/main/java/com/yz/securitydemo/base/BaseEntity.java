package com.yz.securitydemo.base;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    public boolean hasChild() {
        return false;
    }

    public byte[] getRootVarBytes() {
        return null;
    }

    public void setRootVarBytes(byte[] b) {

    }

    public byte[] keyToByte() {
        return null;
    }

    /**
     * シーケンスNO
     */
    private long seqNo;

    /**
     * 登録システム時間
     */
    private Timestamp insertSysDate;

    /**
     * 登録ユーザID
     */
    private String insertUserId;

    /**
     * 登録ジョブID
     */
    private String insertJobId;

    /**
     * 登録プログラムID
     */
    private String insertProId;

    /**
     * 更新システム時間
     */
    private Timestamp updSysDate;

    /**
     * 更新ユーザID
     */
    private String updUserId;

    /**
     * 更新ジョブID
     */
    private String updJobId;

    /**
     * 更新プログラムID
     */
    private String updProId;

    /**
     * パーティションフラグ
     */
    private String partitionFlag;
}
