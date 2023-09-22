package com.yz.toolscommon.config;

/**
 * Const class of job date format.
 * 
 * @author SoftRoad
 */
public class CommonConst {

	/**
	 * -------------------------- 環境変数 ---------------------------
	 */
	// パス名
	public final static String ENV_BATCH_ROOT_PATH = "BATCH_ROOT_PATH";
	public final static String ENV_AJS_JOB_NAME = "AJSJOBNAME";
	public final static String ENV_AJS_HOST = "AJSHOST";
	public final static String ENV_AJS_EX_DATE = "AJSEXDATE";
	public final static String ENV_JP1_JOB_NAME = "JP1JOBNAME";
	public final static String ENV_JP1_JOB_ID = "JP1JOBID";
	public final static String ENV_JP1_UNC_NAME = "JP1UNCNAME";
	public final static String FILE = "file";
	public final static String QTEMP = "QTEMP";
	public final static String DB = "db";
	/**
	 * -------------------------- 配置ファイル名 ---------------------------
	 */
	public final static String CONFIG_BATCH = "KppBatchApplication.properties";

	/**
	 * --------------------------RETURN_CODE ---------------------------
	 */
	public final static int RETURN_CODE_NORMAL = 0;
	public final static int RETURN_CODE_ABNORMAL = -1;

	public static final String BLANK = " ";
	public static final String ZERO = "0";
	public static final String BACKSLASH = "/";
	public static final String DOT = ".";
	public static final String COMMA = ",";
	public static final String LINE = "-";
	public static final String FORMAT_MONEY = ",###.#######";
	public static final String DATE_YYYYMMDD = "yyyymmdd";
	public static final String DATE_YYMMDD = "yymmdd";
	public static final String YEAR_YYYY = "yyyy";
	public static final String YEAR_YY = "yy";
	public static final String MONTH_MM = "mm";
	public static final String DAY_DD = "dd";

	public static final String TIME_TYPE_6 = "HHmmss";
	public static final String TIME_TYPE_12 = "HHmmssMMddYY";
	public static final String TIME_TYPE_14 = "HHmmssMMddYYYY";
	public static final String TIME_TYPE_YYYYMMDD = "YYYYMMdd";
	public static final String TIME_TYPE_MM = "MM";
	public static final String TIME_TYPE_YYYY = "YYYY";
	public static final String TIME_TYPE_DD = "dd";

	public static final String RESERVE_ALL = "ALL";
	public static final boolean RESERVE_YES = true;
	public static final boolean RESERVE_NO = false;

//  Date format
	public static final String DATE_YMD = "*YMD";
	public static final String DATE_YMD_FORMAT = "yy/MM/dd";
	public static final String DATE_DMY = "*DMY";
	public static final String DATE_DMY_FORMAT = "dd/MM/yy";
	public static final String DATE_MDY = "*MDY";
	public static final String DATE_MDY_FORMAT = "MM/dd/yy";
	public static final String DATE_JUL = "*JUL";
	public static final String DATE_JUL_FORMAT = "yy/ddd";
	public static final String DATE_CYMD = "*CYMD";
	public static final String DATE_CYMD_FORMAT = "";
	public static final String DATE_CDMY = "*CDMY";
	public static final String DATE_CDMY_FORMAT = "";
	public static final String DATE_CMDY = "*CMDY";
	public static final String DATE_CMDY_FORMAT = "";
	public static final String DATE_ISO = "*ISO";
	public static final String DATE_ISO_FORMAT = "yyyy-MM-dd";
	public static final String DATE_USA = "*USA";
	public static final String DATE_USA_FORMAT = "MM/dd/yyyy";
	public static final String DATE_EUR = "*EUR";
	public static final String DATE_EUR_FORMAT = "dd.MM.yyyy";
	public static final String DATE_JIS = "*JIS";
	public static final String DATE_JIS_FORMAT = "yyyy-MM-dd";
	public static final String DATE_LONGJUL = "*LONGJUL";
	public static final String DATE_LONGJUL_FORMAT = "yyyy/ddd";
//  Time format	
	public static final String TIME_HMS = "*HMS";
	public static final String TIME_HMS_FORMAT = "HH:mm:ss";
	public static final String TIME_ISO = "*ISO";
	public static final String TIME_ISO_FORMAT = "HH.mm.ss";
	public static final String TIME_USA = "*USA";
	public static final String TIME_USA_FORMAT = "";
	public static final String TIME_EUR = "*EUR";
	public static final String TIME_EUR_FORMAT = "HH.mm.ss";
	public static final String TIME_JIS = "*JIS";
	public static final String TIME_JIS_FORMAT = "HH:mm:ss";
//  TimeStamp format
	public static final String TIMESTAMP_ISO = "*ISO";
	public static final String TIMESTAMP_ISO_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String TIMESTAMP_ISO0 = "*ISO0";
	public static final String DATE_FORMAT = "yyyyMMdd";
	public static final String TIME_FORMAT = "HHmmss";
	public static final String TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSSSSS";
//	Date Code
	public static final String DATE_YEARS = "*YEARS";
	public static final String DATE_Y = "*Y";
	public static final String DATE_MONTHS = "*MONTHS";
	public static final String DATE_M = "*M";
	public static final String DATE_DAYS = "*DAYS";
	public static final String DATE_D = "*D";
	public static final String DATE_HOURS = "*HOURS";
	public static final String DATE_H = "*H";
	public static final String DATE_MINUTES = "*MINUTES";
	public static final String DATE_MN = "*MN";
	public static final String DATE_SECONDS = "*SECONDS";
	public static final String DATE_S = "*S";
	public static final String DATE_MSECONDS = "*MSECONDS";
	public static final String DATE_MS = "*MS";
//	Encode format
	public static final String UTF_8 = "UTF-8";
	public static final String EBCDIC = "EBCDIC";
//	Order 
	public static final String ASCEND = "ASCEND";
	public static final String DESCEND = "DESCEND";

	public static final String SYS_BLANK = "*BLANK";

	public static final String STR_LOW_VAL = "↡";
	public static final String STR_HIGHT_VAL = "↟";
	public static final String OP_GT = "GT";
	public static final String OP_GE = "GE";
	public static final String OP_LT = "LT";
	public static final String OP_LE = "LE";
	public static final String OP_EQ = "EQ";
	public static final String OP_NE = "NE";

	public static final String CP_GT = "*GT";
	public static final String CP_GE = "*GE";
	public static final String CP_LT = "*LT";
	public static final String CP_LE = "*LE";
	public static final String CP_EQ = "*EQ";
	public static final String CP_NE = "*NE";
	public static final String CP_NL = "*NL";
	public static final String CP_NG = "*NG";
	public static final String CP_CT = "*CT";

	public static final String CP_IF = "*IF";
	public static final String CP_AND = "*AND";
	public static final String CP_OR = "*OR";
	public static final String OUTTYPE_DB = "データベース・ファイル";
	public static final String OUTTYPE_PRTF = "印刷装置";
	public static final String DBLQUOTE = "\"";

	/**
	 * --------------------------BUSINESS TYPE ---------------------------
	 */
	public static final String ONLINE = "online";
	public static final String BATCH = "batch";

//  HULFT
	public static final String HULFT_YES = "*YES";
	public static final String HULFT_SYNC = "sync";
	public static final String HULFT_UTLSEND = "utlsend";
	public static final String HULFT_HULSNDD = "hulsndd";
	public static final String HULFT_HULRCVD = "hulrcvd";
	public static final String HULFT_HULOBSD = "hulobsd";

	/**
	 * ------------------------DSPFD start------------------------------
	 */
	public static final String ALL = "*ALL";
	public static final String PF = "*PF";
	public static final String MBRLISTTYPE = "*MBRLIST";
	public static final String MBRTYPE = "*MBR";
	public static final String BASATRTYPE = "*BASATR";
	public static final String REPLACE = "*REPLACE";
	public static final String OUTFILE = "*OUTFILE";
	public static final String QRY = "QRY";
	/**
	 * ------------------------DSPFD end------------------------------
	 */
	
	/**
	 * --------------------------Printf TYPE ---------------------------
	 */
	public static final String PRTF = "PRTF";
	public static final String O = "O";
}
