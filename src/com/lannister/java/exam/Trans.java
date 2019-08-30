package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Trans {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		while((str = br.readLine()) != null) {
			String res = parse(str);
			System.out.println(res);
		}
	}
	public static String parse(String str) {
		while(str.length() > 1) {
			if(str.contains("!0")) {
				str = str.replace("!0", "1");
			}
			if(str.contains("!1")) {
				str = str.replace("!1", "0");
			}
			if(str.contains("1&0")) {
				str = str.replace("1&0","0");
				System.out.println(str);
			}
			if(str.contains("1&1")) {
				str = str.replace("1&1","1");
			}
			if(str.contains("0&0")) {
				str = str.replace("0&0","0");
				
			}
			if(str.contains("0&1")) {
				str = str.replace("0&1","0");
			}
			if(str.contains("1|0")) {
				str = str.replace("1|0","1");
			}
			if(str.contains("1|1")) {
				str = str.replace("1|1","1");
			}
			if(str.contains("0|0")) {
				str = str.replace("0|0","0");
				System.out.println(str);
			}
			if(str.contains("0|1")) {
				str = str.replace("0|1","1");
			}
			if(str.contains("(1)")) {
				str = str.replace("(1)","1");
			}
			if(str.contains("(0)")) {
				str = str.replace("(0)","0");
			}
			//System.out.println(str);
		}
		
		return str;
	}
}
