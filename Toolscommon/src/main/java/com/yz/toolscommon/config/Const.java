package com.yz.toolscommon.config;


public class Const {

	/*
	 * 配置文件路径定义区
	 */
	public static String configPatch_0 = "D:\\siphon\\config-1.txt";
	public static String configPatch_1 = "D:\\siphon\\config-1 -XRSNDGO.txt";
	public static String configPatch_4 = "D:\\siphon\\config-1 -JXHIXGEN.txt";
	public static String configPatch_5 = "D:/siphon/config-1 - SOUT.txt";
	public static String configPatch_6 = "D:/siphon/config-KJKMTCP-6.txt";
	public static String configPatch_7 = "D:/siphon/LIBE_Result.txt";
	public static String configPatch_8 = "D:/siphon/LIBE-FILE-1.txt";
	public static String configPatch_9 = "D:/siphon/PAUSE_1.txt";
	public static String configPatch_10 = "D:/siphon/PAUSE_path_1.txt";
	public static String configPatch_11 = "D:/siphon/sort-1.txt";
	public static String configPatch_12 = "D:/siphon/fcpy-1.txt";
	public static String configPatch_13 = "D:/siphon/JXHADINT-1.txt";
	public static String configPatch_14 = "D:/siphon/FCHK-1.6txt";
	public static String configPatch_15 = "D:/siphon/FDLT-1.txt";
	public static String configPatch_16 = "D:/siphon/JXHADINT-1.txt";
	public static String configPatch_17 = "D:/siphon/cmd.txt";
	public static String configPatch_18 = "D:/siphon/CVTDAT.txt";


	public static String staticConfigPatch= "D:\\itoutyuu\\02_移行後\\02_開発区\\10_データ変換";
	
	
	/* 正则定义区
	 * .+LIBE([\\s\\S]+?)SOUT.+
	 * .+JXKRELOD([\s\S]+?)END.+
	 * .+EX[ ]+XRSNDGO[\s\S]+?(?=\\\*\*)
	 */
	public static String regexXRSNDGO_1 = ".+EX[ ]+XRSNDGO[\\s\\S]+?(?=\\\\\\*\\*)";
	public static String regexJXKRELOD_2 = ".+JXKRELOD([\\s\\S]+?)END.+";
	public static String regexJXHIXGEN_4 = ".+EX[ ]+JXHIXGEN[\\s\\S]+?END";
	public static String regex_5 = "SOUT=(?:\\([\\s\\S]+?\\)|\\w+)";
	public static String regex_6 = ".*EX[ ]+KJKMTCP[\\s\\S]+?FIN.*";
	public static String regex_7 = "(?<=FILE=)[\\w.]+";
	public static String regex_8 = ".+EX[ ]+LIBE[\\s\\S]+?FIN.*";
	public static String regex_9 = "\\\\S\\d{1,3} +EX[\\s\\S]+?\\\\ +PAUSE.*";
	public static String regex_10 = "\\\\ +PAUSE.*";
	public static String regex_11 = ".*EX[ ]+SORT[\\s\\S]+?FIN.*";
	public static String regex_11_1 = "(?<=FD).*?(?=\\=)";
	public static String regex_13 = ".*EX[ ]+JXHADINT[\\s\\S]+?FIN.*";
	public static String regex_14 = ".*EX[ ]+FCHK[\\s\\S]+?FIN.*";
	public static String regex_15 = ".*EX[ ]+FDLT[\\s\\S]+?FIN.*";
	public static String regex_16 = ".+EX[ ]+JXHADINT[\\s\\S]+?END";
	public static String regex_18 = "CVTDAT[\\s\\S]+?\\r\\n.+?\\r\\n";


	/*
	 * 结果输出
	 */
	public static String result_txt = "D:/siphon/result.txt";
	public static String result_xml_txt = "E:\\jcl\\xml/result.txt";
	public static String result_xml_2_txt = "E:\\jcl\\xml/result_1.txt";
	public static String result_1_txt = "D:/siphon/result_1.txt";
	public static String result_2_txt = "D:/siphon/result_2.txt";
	public static String result_csv = "D:/siphon/result.csv";
	public static String result_16_txt = "D:/siphon/JXHADINT.txt";
	public static String result_pgm_sysin_txt = "E:\\jcl\\pgm_sysin_result.txt";
	public static String result_cmd_txt = "D:/siphon/cmd_result.csv";
	public static String result_18_txt = "D:/siphon/CVTDAT_result.csv";

	
	
	
	
	
	
	
	
	
	
	
}
