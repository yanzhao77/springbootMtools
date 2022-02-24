package com.yz.toolscommon.mtools;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateFile {

	
	/**
	 * 
	 * a读取1个文件,统计出现的关键字,和数量
	 */
	
	
	public static void main(String[] args) {
		
		List<File> fileList = new ArrayList<File>();
		String pathListFile = "D:\\exportResult.txt";
		
//		readFileByChars(pathListFile,fileList);
		
		Set<String> fileIds = new HashSet<String>();
		
		/*
		 * for (File f : fileList) { readFileString(f,fileIds); }
		 */
		
		fileList.forEach(f -> {
			readFileString(f,fileIds);	
		});
		
		writeFile(fileIds);
	} 
	
	
	
	/**
           * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
	public static void readFileByChars(String fileName, List<File> fileList) {

		File file = new File(fileName);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// 构造一个BufferedReader类来读取文件

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
	 * FILE-ID 提取到 Set<String> 里
	 */
	public static void readFileString(File file,Set<String> fileIds) {
		String pattern = "(?<=FILE\\-ID\\=)[A-z0-9]+";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "SHIFT-JIS"));// 构造一个BufferedReader类来读取文件

			String s = null;
			while ((s = br.readLine()) != null) {	//a 使用readLine方法，一次读一行
				
				Pattern r = Pattern.compile(pattern);
				Matcher m = r.matcher(s);
				//a 提取匹配的FILE-ID
				if (m.find()) {
					String fileId = m.group();
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
		String writerPath = "D:\\exportFileId.txt";
		
		 try {
	            BufferedWriter out = new BufferedWriter(new FileWriter(writerPath));
			/*
			 * for (String f : fileId) { out.write(f + "\r\n"); }
			 */
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
