package com.yz.toolscommon.mtools;

import java.io.IOException;

/**
 *   跑  Main 
 *
 */
public class C3 {

	public static void main(String[] args) throws IOException {
		String path = "000000.xml";
		
		
		
		
		boolean b = path.matches("\\.");
		System.out.println(b);

	}

	
	
	
	
	
	
/*
 * String context = "MODE OUT=U50 BACKUP +,IN=U01,FILE=ISC3.JCL.MACLIB BACKUP +,IN=U02,FILE=ISC3.AIM.MACLIB BACKUP +,IN=U03,FILE=ISC3.JCL.SETUP ENDMODE FIN";
		
		List<String> itemStrArr = StringUtil.getStringByRegex("IN=[\\w,=\\.]+", context);
		
		itemStrArr.forEach(item -> {
			
			String in = StringUtil.getStringByRegex("(?<=IN=)\\w+", item).get(0);
			String file = StringUtil.getStringByRegex("(?<=FILE=)[\\w\\.]+", item).get(0);
			
			System.out.println(in + "\t" + file);
		});
 * 
 * 
 * 
 * */
	
	
	
	
	
	
}
