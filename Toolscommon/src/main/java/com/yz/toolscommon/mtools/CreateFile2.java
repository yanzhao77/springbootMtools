package com.yz.toolscommon.mtools;

import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateFile2 {

	/**
	 * 过滤文件中重复的字符,并返回写入原文件里
	 **/
	
	public static void main(String[] args) {

		String pathListFile = "D:\\HulftConf\\fileName.txt";
		
		filter(pathListFile);		//a过滤重复地址
		
		
	}
	 


	// a过滤重复地址
	public static void filter(String filePath) {
		Set<String> path = new HashSet<>();
		File file = new File(filePath);

		try {
			// a构造一个BufferedReader类来读取文件
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				if (!StringUtils.isEmpty(s.trim())) {
					path.add(s);
				}
			}
			br.close();
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath)),"SHIFT-JIS"));
//			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			for (String f : path) {
				out.write(f + "\r\n");
			}
			
			out.close();
			System.out.println("过滤完成！");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("过滤失败！");
		}
	}

	/**
	 * w文件 加入 文件List
	 */
	public static void readFileByChars(String fileName, List<File> fileList) {

		File file = new File(fileName);
		try {
			// a构造一个BufferedReader类来读取文件
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				File f = new File(s);
				fileList.add(f);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关键字 提取到 Set<String> 里
	 */
	public static void readFileString(File file, Set<String> fileIds, int[] arr) {

		String pattern = "ADD|UPDATE|COPY|BACKUP|RESTORE|PRINT|LISTDRTY|PUNCH|GENERATE|COMPARE|SCRATCH|RENAME|SETDRTY|UNLOAD|IF|GOTO|NOP|CALL|XCTL|MODE|ENDMODE";
		try {
//			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));// a构造一个BufferedReader类来读取文件
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// a构造一个BufferedReader类来读取文件

			String s = null;
			while ((s = br.readLine()) != null) { // a 使用readLine方法，一次读一行

				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(s);
				// a 提取匹配的FILE-ID
				if (m.find()) {
					String fileId = m.group();
					if (fileId.equals("ADD")) ++arr[0];
					if (fileId.equals("UPDATE")) ++arr[1];
					if (fileId.equals("COPY")) ++arr[2];
					if (fileId.equals("BACKUP")) ++arr[3];
					if (fileId.equals("RESTORE")) ++arr[4];
					if (fileId.equals("PRINT")) ++arr[5];
					if (fileId.equals("LISTDRTY")) ++arr[6];
					if (fileId.equals("PUNCH")) ++arr[7];
					if (fileId.equals("GENERATE")) ++arr[8];
					if (fileId.equals("COMPARE")) ++arr[9];
					if (fileId.equals("SCRATCH")) ++arr[10];
					if (fileId.equals("RENAME")) ++arr[11];
					if (fileId.equals("SETDRTY")) ++arr[12];
					if (fileId.equals("UNLOAD")) ++arr[13];
					if (fileId.equals("IF")) ++arr[14];
					if (fileId.equals("GOTO")) ++arr[15];
					if (fileId.equals("NOP")) ++arr[16];
					if (fileId.equals("CALL")) ++arr[17];
					if (fileId.equals("XCTL")) ++arr[18];
					if (fileId.equals("MODE")) ++arr[19];
					if (fileId.equals("ENDMODE")) ++arr[20];
						
					System.out.println(fileId+"==="+ file.getName());
					fileIds.add(fileId);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * FILE-ID 数据写入文件
	 */
	public static void writeFile(Set<String> fileId) {
		String writerPath = "D:\\exportKBKARCS1.txt";

		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(writerPath));

			fileId.forEach(f -> {
				try {
					out.write(f + "\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			out.close();
			System.out.println("文件创建成功！");
		} catch (IOException e) {
			System.out.println("文件创建Error！");
		}
	}

}
