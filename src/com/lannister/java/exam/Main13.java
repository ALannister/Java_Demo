package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main13{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String str = null;
	    while((str=br.readLine()) != null) {
	    	System.out.println(str.replace(",,",","));
	    	System.out.println(str.replace("\"\"","\""));
	    	String[] strs = str.split("\"");
	    	for(String s:strs) {
	    		System.out.println(s);
	    	}
	    	
	    }
	}
}