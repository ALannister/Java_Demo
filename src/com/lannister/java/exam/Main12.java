package com.lannister.java.exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main12{
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
	    char[] chs = br.readLine().toCharArray();
	    int count = 0;
	    for(int i=0;i<chs.length;i++) {
	    	if(chs[i] == '"') count++;
	    }
	    if(count % 2 != 0) {
	    	System.out.println("ERROR");
	    }
        int yin = 0;
        for(int i=0;i<chs.length;i++){
            if(chs[i] == '"'){
                count++;
                continue;
            }
            if(chs[i] == ',' && count % 2 != 0){
                chs[i] = '-';
            }
        }
        ArrayList<String> list = new ArrayList<>();
        String res = new String(chs);
        String[] strs = res.split("\\,");
        for(int i=0;i<strs.length;i++) {
        	String tmp = strs[i].replace("-", ",");
        	if(strs.equals("")) {
        		list.add("--");
        		continue;
        	}
        	if(tmp.charAt(0) == '"' && tmp.charAt(tmp.length()-1) == '"') {
        		tmp = tmp.substring(1,tmp.length());
        	}
        	tmp.replace("\"\"","\"");
        	list.add(tmp);
        }
	    System.out.println(list.size());
	    for(String str : list) {
	    	System.out.println(str);
	    }
	    
	}
}