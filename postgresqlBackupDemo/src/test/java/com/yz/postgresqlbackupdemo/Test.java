package com.yz.postgresqlbackupdemo;

import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Test {
 
	public static void main(String[] args) {
		Date d = new Date(); // 备份文件名称
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filename = sdf.format(d) + ".tar";
 
		String dbname = "ipam";
		String username = "postgres";
		String dbpath = "d:\\Program Files\\PostgreSQL\\9.1\\bin\\";
		String backuppath = "d:\\" + filename;
 
//		new Test().backupDB(dbname, username, backuppath, dbpath);
		backuppath = "d:\\20120826095554.tar";
		 new Test().restoreDB(dbname, username, backuppath, dbpath);
	}
 
	public boolean backupDB(String dbname, String username, String backuppath,
			String dbpath) {
		// 本地测试用
		// username = "postgres";
		// String dbpath = "d:\\Program Files\\PostgreSQL\\9.1\\bin\\";
 
		boolean flag = true;// 备份是否成功
		Runtime rt = Runtime.getRuntime();// 得到jvm的运行环境
		Process process;
		StringBuffer cmdbuf = new StringBuffer();
		cmdbuf.append(dbpath);
		cmdbuf.append("pg_dump -U ");// 用户名
		cmdbuf.append(username);
		cmdbuf.append(" -f ");
		cmdbuf.append(backuppath);
		cmdbuf.append(" -E utf8 ");// 编码
		cmdbuf.append(" -Ft ");
		cmdbuf.append(dbname);
		try {
			// 调用 cmd:
			System.out.println(cmdbuf);
			process = rt.exec(cmdbuf.toString());
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
 
	public boolean restoreDB(String dbname, String username, String backuppath,
			String dbpath) {
 
		boolean flag = true;// 恢复是否成功
		Runtime rt = Runtime.getRuntime();// 得到jvm的运行环境
		Process process;
		StringBuffer cmdTemp = new StringBuffer();// 命令模版
		cmdTemp.append(dbpath);
		cmdTemp.append("pg_restore -U ");
		cmdTemp.append(username);
		cmdTemp.append(" -c -d ");
		cmdTemp.append(" " + dbname + " ");
		cmdTemp.append(backuppath);
		try {
			process = rt.exec(cmdTemp.toString());// 还原数据库
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}