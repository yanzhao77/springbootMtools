package com.yz.oracle_inandoutdata.utils;

import java.nio.charset.Charset;

public class AuConstUtil {

	public final static String SYSTEM_ENCODING = "MS932";
	public final static String ENCODING_SHIFTJIS = "shift_jis";
	public final static String ENCODING_ISO88591 = "iso-8859-1";
	public final static Charset ENCODING_SYSTEM = Charset.forName(SYSTEM_ENCODING);
	
    public final static String STEP_START_JOB = "startJob";
    public final static String STEP_END_JOB = "endJob";
    
    public final static String JOB_STATUS_RUNNING = "running";
    public final static String JOB_STATUS_FINISHED = "finished";
	public final static String JOB_BASEINFO_VIEW = "ä˘ë∂äÓëbèÓïÒÉrÉÖÅ[";
    public final static String ENV_BATCH_ROOT_PATH = "BATCH_ROOT_PATH";
    public final static String DBCTL="DBCTL";
    public final static String DBOKLOG="DBOKLOG";
    public final static String DBNGLOG="DBNGLOG";
    public final static String DBDATA="DBDATA";
    
    public final static String DATA_TYPE_FILE = "FILE";
    public final static String DATA_TYPE_DB = "DB";
    public final static String LIB_LIBL = "LIBL";
    
    public final static String DATA_MODE_IN="IN";
    public final static String DATA_MODE_OUT="OUT";
    
    public final static String EXCEPT_COLUMN="INSERT_SYS_DATE,INSERT_USER_ID,INSERT_JOB_ID,INSERT_PRO_ID,UPD_SYS_DATE,UPD_USER_ID,UPD_JOB_ID,UPD_PRO_ID";
	
}
